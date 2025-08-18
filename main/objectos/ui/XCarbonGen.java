/*
 * This file is part of Objectos UI.
 * Copyright (C) 2025 Objectos Software LTDA.
 *
 * Objectos UI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Objectos UI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Objectos UI.  If not, see <https://www.gnu.org/licenses/>.
 */
package objectos.ui;

import static java.lang.System.Logger.Level.ERROR;
import static java.lang.System.Logger.Level.INFO;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
final class XCarbonGen {

  private final Path basedir;

  private Clock clock;

  private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  private Appendable logger;

  private Object object;

  private Options options;

  private String pathCss;

  private String pathJs;

  private byte state;

  @SuppressWarnings("unused")
  private String version;

  XCarbonGen(Path basedir) {
    this.basedir = basedir.toAbsolutePath();
  }

  public static void main(String[] args) {
    final String userDir;
    userDir = System.getProperty("user.dir", "");

    final Path basedir;
    basedir = Path.of(userDir);

    final XCarbonGen gen;
    gen = new XCarbonGen(basedir);

    gen.start(args);
  }

  final void start(String... args) {
    object = args;

    execute($OPTIONS, $DONE);
  }

  // ##################################################################
  // # BEGIN: State Machine
  // ##################################################################

  static final byte $OPTIONS = 0;
  static final byte $OPTIONS_PARSE = 1;

  static final byte $INIT = 2;

  static final byte $HTML = 3;
  static final byte $HTML_IMPORT = 4;
  static final byte $HTML_BRACKET = 5;
  static final byte $HTML_LINK = 6;
  static final byte $HTML_HREF = 7;

  static final byte $JS = 8;
  static final byte $JS_VERSION = 9;

  static final byte $CSS = 10;
  static final byte $CSS_SEL = 11;
  static final byte $CSS_TARGET = 12;
  static final byte $CSS_SKIP_RULE = 13;
  static final byte $CSS_THEME = 14;
  static final byte $CSS_THEME_PROPERTY = 15;
  static final byte $CSS_THEME_VALUE = 16;

  static final byte $DONE = 17;

  final void execute(byte from, byte to) {
    state = from;

    while (state < to) {
      execute();
    }
  }

  private void execute() {
    state = switch (state) {
      case $OPTIONS -> executeOptions();
      case $OPTIONS_PARSE -> executeOptionsParse();

      case $INIT -> executeInit();

      case $HTML -> executeHtml();
      case $HTML_IMPORT -> executeHtmlImport();
      case $HTML_BRACKET -> executeHtmlBracket();
      case $HTML_LINK -> executeHtmlLink();
      case $HTML_HREF -> executeHtmlHref();

      case $JS -> executeJs();
      case $JS_VERSION -> executeJsVersion();

      case $CSS -> executeCss();
      case $CSS_SEL -> executeCssSel();
      case $CSS_TARGET -> executeCssTarget();
      case $CSS_SKIP_RULE -> executeCssSkipRule();
      case $CSS_THEME -> executeCssTheme();
      case $CSS_THEME_PROPERTY -> executeCssThemeProperty();
      case $CSS_THEME_VALUE -> executeCssThemeValue();

      default -> throw new AssertionError("Unexpected state=" + state);
    };
  }

  // ##################################################################
  // # END: State Machine
  // ##################################################################

  // ##################################################################
  // # BEGIN: Options
  // ##################################################################

  private static final class Option {

    enum Kind {

      DURATION,

      PATH,

      STRING;

    }

    final Kind kind;

    final String name;

    // DEF = Default
    // CLI = Command Line
    // SYS = System
    String source;

    final Consumer<Option> validator;

    Object value;

    Option(Kind kind, String name, Consumer<Option> validator) {
      this.kind = kind;
      this.name = name;
      this.validator = validator;
    }

    final void parse(String source, String rawValue) {
      value = switch (kind) {
        case DURATION -> Duration.parse(rawValue);

        case PATH -> Path.of(rawValue);

        case STRING -> rawValue;
      };

      this.source = source;
    }

    final void validate() {
      if (validator != null) {
        validator.accept(this);
      }
    }

    final Duration duration() {
      return value(Kind.DURATION);
    }

    final Path path() {
      return value(Kind.PATH);
    }

    final String string() {
      return value(Kind.STRING);
    }

    @SuppressWarnings("unchecked")
    private <T> T value(Kind expected) {
      if (kind != expected) {
        throw new UnsupportedOperationException("Operation is only allowed for kind=" + expected + " but kind=" + kind);
      }

      return (T) value;
    }

    final boolean unset() {
      return value == null;
    }

    final void set(Object v) {
      source = "DEF";

      value = v;
    }

  }

  // ad-hoc enum so instances can be GC'ed after use.
  private class Options {

    // these must come first
    final Map<String, Option> byName = new LinkedHashMap<>();
    int maxLength = 0;

    // options: order is significant
    final Option html = string("--html", opt -> {
      if (opt.unset()) {
        throw new IllegalArgumentException("The option --html is required");
      }
    });

    final Option httpConnectTimout = duration("--http-connect-timeout", opt -> {
      if (opt.unset()) {
        opt.set(Duration.ofSeconds(10));
      }
    });

    final Option httpRequestTimout = duration("--http-request-timeout", opt -> {
      if (opt.unset()) {
        opt.set(Duration.ofMinutes(1));
      }
    });

    final Option workdir = path("--workdir", opt -> {
      if (opt.unset()) {
        opt.set(
            basedir.resolve("work/carbon-gen")
        );
      }
    });

    final Iterable<Option> values() {
      return byName.values();
    }

    private Option duration(String name, Consumer<Option> validator) {
      return opt(Option.Kind.DURATION, name, validator);
    }

    private Option path(String name, Consumer<Option> validator) {
      return opt(Option.Kind.PATH, name, validator);
    }

    private Option string(String name, Consumer<Option> validator) {
      return opt(Option.Kind.STRING, name, validator);
    }

    private Option opt(Option.Kind kind, String name, Consumer<Option> validator) {
      final Option option;
      option = new Option(kind, name, validator);

      byName.put(name, option);

      maxLength = Math.max(maxLength, name.length());

      return option;
    }

  }

  private final class OptionsCtx {
    private final String[] args;

    private int index;

    OptionsCtx(String[] args) {
      this.args = args;
    }

    final boolean hasNext() {
      return index < args.length;
    }

    final String next() {
      return args[index++];
    }
  }

  private byte executeOptions() {
    options = new Options();

    final String[] args;
    args = (String[]) object;

    object = new OptionsCtx(args);

    return $OPTIONS_PARSE;
  }

  private byte executeOptionsParse() {
    final OptionsCtx ctx;
    ctx = (OptionsCtx) object;

    if (!ctx.hasNext()) {
      // no more command line args
      return $INIT;
    }

    final String arg;
    arg = ctx.next();

    final Map<String, Option> byName;
    byName = options.byName;

    final Option option;
    option = byName.get(arg);

    if (option == null) {
      throw new IllegalArgumentException("Unknown option " + arg);
    }

    if (!ctx.hasNext()) {
      throw new UnsupportedOperationException("Implement me :: no value");
    }

    try {
      final String rawValue;
      rawValue = ctx.next();

      option.parse("CLI", rawValue);

      return $OPTIONS_PARSE;
    } catch (RuntimeException parseException) {
      throw new UnsupportedOperationException("Implement me", parseException);
    }
  }

  // ##################################################################
  // # END: Options
  // ##################################################################

  // ##################################################################
  // # BEGIN: Init
  // ##################################################################

  private byte executeInit() {
    for (Option opt : options.values()) {
      opt.validate();
    }

    if (clock == null) {
      clock = Clock.systemDefaultZone();
    }

    if (logger == null) {
      logger = System.out;
    }

    logInfo("Objectos UI carbon-gen");

    final String format;
    format = "(%3s) %-" + options.maxLength + "s %s";

    logInfo(format, "SYS", "basedir", basedir);

    for (Option option : options.values()) {
      logInfo(format, option.source, option.name, option.value);
    }

    return $HTML;
  }

  // ##################################################################
  // # END: Init
  // ##################################################################

  private static abstract class Req {
    String text;

    void set(String text) {
      this.text = text;
    }
  }

  // ##################################################################
  // # BEGIN: HTML
  // ##################################################################

  private static class HtmlCtx extends Req {
    int start;
    int end;

    @Override
    final void set(String text) {
      super.set(text);

      end = text.length() - 1;
    }
  }

  private byte executeHtml() {
    try {
      object = new HtmlCtx();

      final String location;
      location = options.html.string();

      final URI uri;
      uri = URI.create(location);

      final URL url;
      url = uri.toURL();

      final Path html;
      html = resolveWork("carbon.html");

      return reqDownload(url, html, $HTML_IMPORT);
    } catch (MalformedURLException e) {
      return toError("Bad HTML URL?", e);
    }
  }

  private byte executeHtmlImport() {
    final HtmlCtx ctx;
    ctx = (HtmlCtx) object;

    ctx.end = ctx.text.lastIndexOf('\'', ctx.end);

    if (ctx.end < 0) {
      return toError("Could not find closing ' character");
    }

    int strStart;
    strStart = ctx.text.lastIndexOf('\'', ctx.end - 1);

    if (strStart < 0) {
      return toError("Could not find opening ' character");
    }

    String value;
    value = ctx.text.substring(strStart + 1, ctx.end);

    if (value.startsWith("./main") && value.endsWith(".js")) {
      // skip './'
      value = value.substring(2);

      logInfo("Found  JS: %s", value);

      pathJs = value;

      return $HTML_BRACKET;
    } else {
      ctx.end = strStart - 1;

      return $HTML_IMPORT;
    }
  }

  private byte executeHtmlBracket() {
    final HtmlCtx ctx;
    ctx = (HtmlCtx) object;

    ctx.start = ctx.text.indexOf('<', ctx.start);

    if (ctx.start < 0) {
      return toError("Failed to find CSS link in the HTML file.");
    }

    final int endIndex;
    endIndex = ctx.text.indexOf(' ', ctx.start);

    if (endIndex < 0) {
      return toError("Failed to find CSS link in the HTML file.");
    }

    // skip '<'
    ctx.start += 1;

    final String link;
    link = "link";

    final int len;
    len = endIndex - ctx.start;

    if (len != link.length()) {
      // tag length differs
      return $HTML_BRACKET;
    }

    final int startIndex;
    startIndex = ctx.start;

    ctx.start = endIndex;

    if (ctx.text.regionMatches(startIndex, link, 0, link.length())) {
      ctx.end = ctx.text.indexOf('>', ctx.start);

      if (ctx.end < 0) {
        return toError("Failed to find end of <link> tag");
      }

      return $HTML_LINK;
    } else {
      return $HTML_BRACKET;
    }
  }

  private byte executeHtmlLink() {
    final HtmlCtx ctx;
    ctx = (HtmlCtx) object;

    final int equals;
    equals = ctx.text.indexOf('=', ctx.start);

    if (equals < 0) {
      return toError("Failed to find the attribute '=' character");
    }

    if (equals > ctx.end) {
      // equals not of this tag
      // search next tag
      ctx.end = ctx.start;

      return $HTML_BRACKET;
    }

    String attrName;
    attrName = ctx.text.substring(ctx.start, equals);

    attrName = attrName.trim();

    ctx.start = equals + 1;

    if ("href".equals(attrName)) {
      return $HTML_HREF;
    } else {
      return $HTML_LINK;
    }
  }

  private byte executeHtmlHref() {
    final HtmlCtx ctx;
    ctx = (HtmlCtx) object;

    final char quote;
    quote = ctx.text.charAt(ctx.start);

    if (quote != '"') {
      return toError("href value is not quoted");
    }

    final int valueStart;
    valueStart = ctx.start + 1;

    final int valueEnd;
    valueEnd = ctx.text.indexOf(quote, valueStart);

    if (valueEnd < 0) {
      return toError("Failed to find the closing '\"' character");
    }

    if (valueEnd > ctx.end) {
      return toError("The closing '\"' character seems to be outside the current tag");
    }

    final String value;
    value = ctx.text.substring(valueStart, valueEnd);

    if (value.startsWith("main") && value.endsWith(".css")) {
      logInfo("Found CSS: %s", value);

      pathCss = value;

      return $JS;
    } else {
      logInfo("Ignored HREF attribute: %s", value);

      // search next tag?
      ctx.start = ctx.end;

      return $HTML_BRACKET;
    }
  }

  // ##################################################################
  // # END: HTML
  // ##################################################################

  // ##################################################################
  // # BEGIN: JS
  // ##################################################################

  private static final class JsCtx extends Req {
    int end;

    @Override
    final void set(String text) {
      super.set(text);

      end = text.length() - 1;
    }
  }

  private byte executeJs() {
    try {
      object = new JsCtx();

      final URI uri;
      uri = resolveUri(pathJs);

      final URL url;
      url = uri.toURL();

      final Path target;
      target = resolveWork("carbon.js");

      return reqDownload(url, target, $JS_VERSION);
    } catch (MalformedURLException e) {
      return toError("Bad JS URL?", e);
    }
  }

  private byte executeJsVersion() {
    final JsCtx ctx;
    ctx = (JsCtx) object;

    ctx.end = ctx.text.lastIndexOf(':', ctx.end);

    if (ctx.end < 0) {
      return toError("Could not find JSON ':' character");
    }

    final int nameEnd;
    nameEnd = ctx.text.lastIndexOf('"', ctx.end);

    if (nameEnd < 0) {
      return toError("Could not find JSON property name closing '\"' character");
    }

    int nameStart;
    nameStart = ctx.text.lastIndexOf('"', nameEnd - 1);

    if (nameStart < 0) {
      return toError("Could not find JSON property name opening '\"' character");
    }

    nameStart += 1; // skip quote itself

    final String propName;
    propName = "rE";

    final int len;
    len = nameEnd - nameStart;

    if (len != propName.length() ||
        !ctx.text.regionMatches(nameStart, propName, 0, len)) {
      ctx.end -= 1;

      return $JS_VERSION;
    }

    int valueStart;
    valueStart = ctx.text.indexOf('"', ctx.end);

    if (valueStart < 0) {
      return toError("Could not find JSON property value opening '\"' character");
    }

    valueStart += 1; // skip quote itself

    final int valueEnd;
    valueEnd = ctx.text.indexOf('"', valueStart);

    if (valueEnd < 0) {
      return toError("Could not find JSON property value closing '\"' character");
    }

    final String value;
    value = ctx.text.substring(valueStart, valueEnd);

    logInfo("Found VER: %s", value);

    version = value;

    return $CSS;
  }

  // ##################################################################
  // # END: JS
  // ##################################################################

  // ##################################################################
  // # BEGIN: CSS
  // ##################################################################

  private enum CssAction {
    THEME;

    final CssTarget of(CssMatch match, String selector) {
      return new CssTarget(this, match, selector);
    }
  }

  private enum CssMatch {
    EXACT;
  }

  private record CssTarget(CssAction action, CssMatch match, String selector) {}

  private static final class CssCtx extends Req {
    int start;

    List<String> names = new ArrayList<>();

    String propName;

    CssTarget target;

    final List<CssTarget> targets;

    List<ThemeValue> theme;

    CssCtx() {
      final List<CssTarget> list;
      list = new ArrayList<>();

      list.add(CssAction.THEME.of(CssMatch.EXACT, ":root"));
      list.add(CssAction.THEME.of(CssMatch.EXACT, ".cds--white"));
      list.add(CssAction.THEME.of(CssMatch.EXACT, ".cds--g10"));
      list.add(CssAction.THEME.of(CssMatch.EXACT, ".cds--g90"));
      list.add(CssAction.THEME.of(CssMatch.EXACT, ".cds--g100"));

      targets = list;
    }

    final void nameAdd(String name) {
      names.add(name);
    }

    final String namePeek() {
      return names.getLast();
    }

    final void nameRemove() {
      names.removeLast();
    }

    final ThemeValue themeValue(String value) {
      final ThemeValue tv;
      tv = new ThemeValue(propName, value);

      theme.add(tv);

      return tv;
    }
  }

  private byte executeCss() {
    try {
      object = new CssCtx();

      final URI uri;
      uri = resolveUri(pathCss);

      final URL url;
      url = uri.toURL();

      final Path target;
      target = resolveWork("carbon.css");

      return reqDownload(url, target, $CSS_SEL);
    } catch (MalformedURLException e) {
      return toError("Bad CSS URL?", e);
    }
  }

  private byte executeCssSel() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    final String s;
    s = ctx.text;

    int idx;
    idx = ctx.start;

    int bracket;
    bracket = idx;

    boolean media;
    media = false;

    while (idx < s.length()) {
      final char c;
      c = s.charAt(idx);

      if (c == '@') {
        media = true;
      }

      else if (c == '}') {
        ctx.nameRemove();

        ctx.start = idx + 1;

        return $CSS_SEL;
      }

      else if (c == '{') {
        bracket = idx;

        break;
      }

      idx++;
    }

    if (bracket == ctx.start) {
      // no '{' found, assume we're done
      return $DONE;
    }

    final String name;
    name = s.substring(ctx.start, bracket);

    ctx.nameAdd(name);

    ctx.start = bracket + 1; // skip '{'

    if (media) {
      return $CSS_SEL;
    } else {
      return $CSS_TARGET;
    }
  }

  private byte executeCssTarget() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    ctx.target = null;

    final String name;
    name = ctx.namePeek();

    final List<CssTarget> targets;
    targets = ctx.targets;

    outer: for (CssTarget target : targets) {
      switch (target.match) {
        case EXACT -> {
          if (name.equals(target.selector)) {
            ctx.target = target;

            break outer;
          }
        }
      }
    }

    final CssTarget t;
    t = ctx.target;

    if (t == null) {
      return $CSS_SKIP_RULE;
    }

    return switch (t.action) {
      case THEME -> $CSS_THEME;
    };
  }

  private byte executeCssSkipRule() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    final String s;
    s = ctx.text;

    int idx;
    idx = ctx.start;

    int bracket;
    bracket = idx;

    while (idx < s.length()) {
      final char c;
      c = s.charAt(idx);

      if (c == '}') {
        ctx.nameRemove();

        bracket = idx;

        break;
      }

      idx++;
    }

    if (bracket == ctx.start) {
      return toError("Could not find rule '}' character");
    }

    ctx.start = bracket + 1;

    return $CSS_SEL;
  }

  private static final class Theme {

    final String name;

    final List<ThemeValue> values = new ArrayList<>();

    final Map<String, Theme> themes = new HashMap<>();

    Theme(String name) {
      this.name = name;
    }

    final List<ThemeValue> values(List<String> names) {
      return switch (names.size()) {
        case 0 -> throw new AssertionError("stack should not have been empty");

        case 1 -> { checkName(names); yield values; }

        default -> {
          checkName(names);

          final List<String> sub;
          sub = names.subList(0, names.size() - 1);

          final String name;
          name = sub.getLast();

          final Theme theme;
          theme = themes.computeIfAbsent(name, Theme::new);

          yield theme.values(sub);
        }
      };
    }

    private void checkName(List<String> names) {
      final String peek;
      peek = names.getLast();

      if (!name.equals(peek)) {
        throw new IllegalArgumentException("name mismatch: actual=%s, queries=%s".formatted(name, peek));
      }
    }

  }

  private record ThemeValue(String name, String value) {}

  private final Map<String, Theme> themes = new HashMap<>();

  private byte executeCssTheme() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    final String name;
    name = ctx.namePeek();

    logInfo("Theme %s", ctx.names.toString());

    final Theme theme;
    theme = themes.computeIfAbsent(name, Theme::new);

    ctx.theme = theme.values(ctx.names);

    return $CSS_THEME_PROPERTY;

  }

  private byte executeCssThemeProperty() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    final String s;
    s = ctx.text;

    int idx;
    idx = ctx.start;

    int colon;
    colon = ctx.start;

    final int limit;
    limit = s.length();

    while (idx < limit) {
      final char c = s.charAt(idx);

      if (c == ':') {
        colon = idx;

        break;
      }

      if (c == '}' || c == ';') {
        return toError("Unexpected end of CSS declaration");
      }

      idx++;
    }

    if (colon < ctx.start) {
      return toError("Could not find CSS declaration ':' character");
    }

    ctx.propName = s.substring(ctx.start, colon);

    ctx.start = colon + 1;

    return $CSS_THEME_VALUE;
  }

  private byte executeCssThemeValue() {
    final CssCtx ctx;
    ctx = (CssCtx) object;

    final String s;
    s = ctx.text;

    int idx;
    idx = ctx.start;

    int declEnd;
    declEnd = ctx.start;

    boolean ruleEnd;
    ruleEnd = false;

    final int limit;
    limit = s.length();

    while (idx < limit) {
      final char c = s.charAt(idx);

      if (c == ';') {
        declEnd = idx;

        break;
      }

      if (c == '}') {
        declEnd = idx;

        ruleEnd = true;

        break;
      }

      idx++;
    }

    if (declEnd < ctx.start) {
      return toError("Could not find CSS declaration end");
    }

    final String value;
    value = s.substring(ctx.start, declEnd);

    final ThemeValue tv;
    tv = ctx.themeValue(value);

    logInfo("%s: %s", tv.name, tv.value);

    ctx.start = declEnd + 1;

    if (ruleEnd) {
      ctx.nameRemove();

      return $CSS_SEL;
    } else {
      return $CSS_THEME_PROPERTY;
    }
  }

  // ##################################################################
  // # END: CSS
  // ##################################################################

  // ##################################################################
  // # BEGIN: I/O
  // ##################################################################

  private byte reqDownload(URL url, Path target, byte onSuccess) {
    try {
      final URLConnection conn;
      conn = url.openConnection();

      final Duration connectTimeout;
      connectTimeout = options.httpConnectTimout.duration();

      conn.setConnectTimeout((int) connectTimeout.toMillis());

      final Duration readTimeout;
      readTimeout = options.httpRequestTimout.duration();

      conn.setReadTimeout((int) readTimeout.toMillis());

      conn.connect();

      final Path parent;
      parent = target.getParent();

      Files.createDirectories(parent);

      try (InputStream in = conn.getInputStream()) {
        Files.copy(in, target);
      }

      final Req req;
      req = (Req) object;

      final String s;
      s = Files.readString(target, StandardCharsets.UTF_8);

      req.set(s);

      return onSuccess;
    } catch (IOException e) {
      return toError("Failed to download file", e);
    }
  }

  private URI resolveUri(String name) {
    final String location;
    location = options.html.string();

    final URI uri;
    uri = URI.create(location);

    return uri.resolve(name);
  }

  private Path resolveWork(String name) {
    final Path workdir;
    workdir = options.workdir.path();

    return workdir.resolve(name);
  }

  // ##################################################################
  // # END: I/O
  // ##################################################################

  // ##################################################################
  // # BEGIN: Logging
  // ##################################################################

  private void logInfo(String message) {
    log0(INFO, message);
  }

  private void logInfo(String format, Object... args) {
    logInfo(
        String.format(format, args)
    );
  }

  private void logError(String message) {
    log0(ERROR, message);
  }

  private void log0(System.Logger.Level level, String message) {
    try {
      final LocalDateTime now;
      now = LocalDateTime.now(clock);

      final String time;
      time = dateFormat.format(now);

      final String markerName;
      markerName = level.getName();

      final String log;
      log = String.format("%s %-5s %s%n", time, markerName, message);

      logger.append(log);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to log message", e);
    }
  }

  private byte toError(String message) {
    logError(message);

    return $DONE;
  }

  private byte toError(String message, Throwable t) {
    logError(message);

    t.printStackTrace();

    return $DONE;
  }

  // ##################################################################
  // # END: Logging
  // ##################################################################

}
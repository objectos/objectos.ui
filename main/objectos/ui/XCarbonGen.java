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
package objectos.ui; // SED_REMOVE

import static java.lang.System.Logger.Level.INFO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
final class XCarbonGen {

  private final Path basedir;

  private Clock clock;

  private final Set<String> colors = new HashSet<>();

  private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  private Appendable logger;

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

    gen.execute(args);
  }

  // ##################################################################
  // # BEGIN: Main
  // ##################################################################

  static abstract class Ctx {

    int cursor;

    int idx;

    final String s;

    Ctx(String s) {
      this.s = s;
    }

    final char charNext() {
      idx = cursor++;

      return s.charAt(idx);
    }

    final boolean charTest() {
      return cursor < s.length();
    }

  }

  final void execute(String[] args) {
    final Options options;
    options = options(args);

    init(options);

    if (!options.cdsSkip.bool()) {
      cds(options);
    }

    if (!options.c4pSkip.bool()) {
      c4p(options);
    }
  }

  // ##################################################################
  // # END: Main
  // ##################################################################

  // ##################################################################
  // # BEGIN: Options
  // ##################################################################

  private static final class Option {

    enum Kind {

      BOOLEAN,

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
        case BOOLEAN -> Boolean.parseBoolean(rawValue);

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

    final boolean bool() {
      return value(Kind.BOOLEAN);
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
    final Option cdsSkip = bool("--cds-skip", opt -> {
      if (opt.unset()) {
        opt.set(Boolean.FALSE);
      }
    });

    final Option cdsHtml = string("--cds-html", opt -> {
      if (opt.unset()) {
        if (!cdsSkip.bool()) {
          throw new IllegalArgumentException("The option --cds-html is required");
        } else {
          opt.set("");
        }
      }
    });

    final Option c4pSkip = bool("--c4p-skip", opt -> {
      if (opt.unset()) {
        opt.set(Boolean.FALSE);
      }
    });

    final Option c4pHtml = string("--c4p-html", opt -> {
      if (opt.unset()) {
        if (!c4pSkip.bool()) {
          throw new IllegalArgumentException("The option --c4p-html is required");
        } else {
          opt.set("");
        }
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

    private Option bool(String name, Consumer<Option> validator) {
      return opt(Option.Kind.BOOLEAN, name, validator);
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

  private Options options(String[] args) {
    final Options options;
    options = new Options();

    int idx;
    idx = 0;

    while (idx < args.length) {
      final String name;
      name = args[idx++];

      final Map<String, Option> byName;
      byName = options.byName;

      final Option option;
      option = byName.get(name);

      if (option == null) {
        throw new IllegalArgumentException("Unknown option " + name);
      }

      if (idx >= args.length) {
        throw new IllegalArgumentException("No value for option " + name);
      }

      try {
        final String rawValue;
        rawValue = args[idx++];

        option.parse("CLI", rawValue);
      } catch (RuntimeException parseException) {
        throw new UnsupportedOperationException("Implement me", parseException);
      }
    }

    return options;
  }

  // ##################################################################
  // # END: Options
  // ##################################################################

  // ##################################################################
  // # BEGIN: Init
  // ##################################################################

  private void init(Options options) {
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
  }

  // ##################################################################
  // # END: Init
  // ##################################################################

  // ##################################################################
  // # BEGIN: CDS
  // ##################################################################

  private void cds(Options options) {
    final String htmlLocation;
    htmlLocation = options.cdsHtml.string();

    final URI htmlUri;
    htmlUri = URI.create(htmlLocation);

    final String html;
    html = read(options, htmlUri, "carbon.html");

    final String cssPath;
    cssPath = cdsCssPath(html);

    cdsCssParse(options, cssPath);

    final String jsPath;
    jsPath = cdsJsPath(html);

    final String cdsVersion;
    cdsVersion = cdsJsVersion(options, jsPath);

    cdsWrite(cdsVersion);
  }

  // ##################################################################
  // # BEGIN: CDS: JS
  // ##################################################################

  private String cdsJsPath(String s) {
    int end;
    end = s.length() - 1;

    while (end >= 0) {
      end = s.lastIndexOf('\'', end);

      if (end < 0) {
        throw error("Could not find closing ' character");
      }

      final int strStart;
      strStart = s.lastIndexOf('\'', end - 1);

      if (strStart < 0) {
        throw error("Could not find opening ' character");
      }

      String value;
      value = s.substring(strStart + 1, end);

      if (value.startsWith("./main") && value.endsWith(".js")) {
        // skip './'
        value = value.substring(2);

        logInfo("Found  JS: %s", value);

        return value;
      }

      end = strStart - 1;
    }

    throw error("Could not find path for CDS JS file");
  }

  private String cdsJsVersion(Options options, String pathJs) {
    final URI uri;
    uri = resolveUri(options.cdsHtml, pathJs);

    final String s;
    s = read(options, uri, "carbon.js");

    int mark;
    mark = s.length() - 1;

    while (mark >= 0) {
      final int colon;
      colon = s.lastIndexOf(':', mark);

      if (colon < 0) {
        throw error("Could not find JSON ':' character");
      }

      final int nameEnd;
      nameEnd = s.lastIndexOf('"', colon);

      if (nameEnd < 0) {
        throw error("Could not find JSON property name closing '\"' character");
      }

      int nameStart;
      nameStart = s.lastIndexOf('"', nameEnd - 1);

      if (nameStart < 0) {
        throw error("Could not find JSON property name opening '\"' character");
      }

      nameStart += 1; // skip quote itself

      final String propName;
      propName = "rE";

      final int len;
      len = nameEnd - nameStart;

      if (len != propName.length() ||
          !s.regionMatches(nameStart, propName, 0, len)) {
        mark = nameStart;

        continue;
      }

      int valueStart;
      valueStart = s.indexOf('"', colon);

      if (valueStart < 0) {
        throw error("Could not find JSON property value opening '\"' character");
      }

      valueStart += 1; // skip quote itself

      final int valueEnd;
      valueEnd = s.indexOf('"', valueStart);

      if (valueEnd < 0) {
        throw error("Could not find JSON property value closing '\"' character");
      }

      final String value;
      value = s.substring(valueStart, valueEnd);

      logInfo("Found VER: %s", value);

      return value;
    }

    throw error("Could not find JS version");
  }

  // ##################################################################
  // # END: CDS: JS
  // ##################################################################

  // ##################################################################
  // # BEGIN: CDS: CSS
  // ##################################################################

  private static final String LINK = "link";

  private String cdsCssPath(String s) {
    int mark;
    mark = 0;

    final int max;
    max = s.length();

    outer: while (mark < max) {
      final int left;
      left = s.indexOf('<', mark);

      if (left < 0) {
        throw error("Failed to find CSS link in the HTML file.");
      }

      final int space;
      space = s.indexOf(' ', mark);

      if (space < 0) {
        throw error("Failed to find CSS link in the HTML file.");
      }

      // skip '<'
      mark += 1;

      final int len;
      len = space - mark;

      if (len != LINK.length()) {
        // tag length differs
        continue;
      }

      if (!s.regionMatches(mark, LINK, 0, LINK.length())) {
        // not a <link> tag
        continue;
      }

      final int right;
      right = s.indexOf('>', space);

      if (right < 0) {
        throw error("Failed to find end of <link> tag");
      }

      mark = space;

      while (mark < right) {
        final int equals;
        equals = s.indexOf('=', mark, right);

        if (equals < 0) {
          continue outer;
        }

        String attrName;
        attrName = s.substring(mark, equals);

        attrName = attrName.trim();

        mark = equals + 1;

        if (!"href".equals(attrName)) {
          continue;
        }

        final char leftQuote;
        leftQuote = s.charAt(mark);

        if (leftQuote != '"') {
          throw error("href value is not quoted");
        }

        mark += 1;

        final int rightQuote;
        rightQuote = s.indexOf(leftQuote, mark, right);

        if (rightQuote < 0) {
          throw error("Failed to find the closing '\"' character");
        }

        final String value;
        value = s.substring(mark, rightQuote);

        if (!value.startsWith("main") || !value.endsWith(".css")) {
          logInfo("Ignored HREF attribute: %s", value);

          // search next tag?
          mark = right;

          continue outer;
        }

        logInfo("Found CSS: %s", value);

        return value;
      }

    }

    throw error("Failed to find CDS CSS file path");
  }

  private enum CssAction {
    THEME;

    final CssTarget of(CssMatch match, String selector) {
      return new CssTarget(this, match, selector, null);
    }

    final CssTarget of(CssMatch match, String selector, String name) {
      return new CssTarget(this, match, selector, name);
    }
  }

  private enum CssMatch {
    EXACT,

    STARTS_WITH;
  }

  private record CssTarget(CssAction action, CssMatch match, String selector, String name) {}

  private class CssCtx extends Ctx {
    int mark;

    boolean media;

    final List<String> names = new ArrayList<>();

    final List<CssTarget> targets = List.of(
        CssAction.THEME.of(CssMatch.EXACT, ":root"),
        CssAction.THEME.of(CssMatch.EXACT, ".cds--white", ".carbon-white"),
        CssAction.THEME.of(CssMatch.EXACT, ".cds--g10", ".carbon-g10"),
        CssAction.THEME.of(CssMatch.EXACT, ".cds--g90", ".carbon-g90"),
        CssAction.THEME.of(CssMatch.EXACT, ".cds--g100", ".carbon-g100"),
        CssAction.THEME.of(CssMatch.STARTS_WITH, "[data-carbon-theme=")
    );

    CssCtx(String s) {
      super(s);
    }

    final void markCursor() {
      mark = cursor;
    }

    final void markIdx() {
      mark = idx;
    }

    final String name() {
      return s.substring(mark, idx);
    }

    final void nameAdd(String name) {
      names.add(name);
    }

    final void nameRemove() {
      names.removeLast();
    }

    final CssTarget target(String name) {
      for (CssTarget target : targets) {
        final boolean res;
        res = switch (target.match) {
          case EXACT -> name.equals(target.selector);

          case STARTS_WITH -> name.startsWith(target.selector);

          default -> false;
        };

        if (res) {
          return target;
        }
      }

      return null;
    }
  }

  private void cdsCssParse(Options options, String cssPath) {
    final URI uri;
    uri = resolveUri(options.cdsHtml, cssPath);

    final String s;
    s = read(options, uri, "carbon.css");

    final CssCtx ctx;
    ctx = new CssCtx(s);

    while (ctx.charTest()) {
      final char c;
      c = ctx.charNext();

      if (c == '@') {
        ctx.media = true;
      }

      else if (c == '}') {
        ctx.nameRemove();

        ctx.markCursor();
      }

      else if (c == '{') {
        final String name;
        name = ctx.name();

        ctx.nameAdd(name);

        ctx.markCursor();

        if (ctx.media) {
          ctx.media = false;

          continue;
        }

        final CssTarget target;
        target = ctx.target(name);

        if (target == null) {
          continue;
        }

        switch (target.action) {
          case THEME -> {
            cdsCssParseTheme(ctx);

            ctx.nameRemove();

            ctx.markCursor();
          }
        }
      }
    }
  }

  private static final class Theme {

    final List<String> names;

    final List<Declaration> declarations = new ArrayList<>();

    Theme(List<String> names) {
      this.names = names;
    }

    final void add(DeclarationKind kind, String name, String val0, String val1) {
      declarations.add(
          new Declaration(kind, name, val0, val1)
      );
    }

  }

  private enum DeclarationKind {
    CUSTOM,

    CUSTOM_COLOR_VALUE,

    CUSTOM_COLOR_VAR2,

    CUSTOM_DIMENSION,

    CUSTOM_VAR,

    REGULAR,

    REGULAR_VAR;
  }

  private record Declaration(DeclarationKind kind, String name, String val0, String val1) {}

  private final Map<List<String>, Theme> themes = new LinkedHashMap<>();

  private void cdsCssParseTheme(CssCtx ctx) {
    final List<String> names;
    names = List.copyOf(ctx.names);

    final Theme theme;
    theme = themes.computeIfAbsent(names, Theme::new);

    enum Parser {
      START,
      PREFIX,
      PROPERTY,
      VALUE,
      VALUE_VAR,
      VALUE_VAR_PREFIX,
      VALUE_VAR_VAL0,
      VALUE_VAR_VAL1,
      VALUE_VAR_VAL1_COLOR,
      VALUE_VAR_END,
      VALUE_END;
    }

    Parser parser;
    parser = Parser.START;

    boolean custom = false;

    int dash = 0;

    DeclarationKind kind = null;

    String name = null, val0 = null, val1 = null;

    loop: while (ctx.charTest()) {
      final char c;
      c = ctx.charNext();

      switch (parser) {
        case START -> {
          name = val0 = val1 = null;

          if (c == '-') {
            // assume custom property
            parser = Parser.PREFIX;

            custom = true;

            dash = 1;
          }

          else {
            parser = Parser.PROPERTY;

            custom = false;

            ctx.markIdx();
          }
        }

        case PREFIX -> {
          if (c == 'c' || c == 'd' || c == 's') {
            parser = Parser.PREFIX;
          }

          else if (c == '-') {
            dash += 1;

            if (dash == 3) {
              parser = Parser.PROPERTY;

              ctx.markCursor();
            } else {
              parser = Parser.PREFIX;
            }
          }

          else if (c == ':') {
            throw error("Unexpected end of custom property name");
          }
        }

        case PROPERTY -> {
          if (c == ':') {
            parser = Parser.VALUE;

            name = ctx.name();
          }

          else if (c == ';') {
            throw error("Unexpected end of custom property name");
          }
        }

        case VALUE -> {
          ctx.markIdx();

          if (c == ' ') {
            parser = Parser.VALUE;
          }

          else if (c == '#' || c == 'r') {
            if (!custom) {
              throw error("Unexpected value: regular prop + color value");
            }

            colors.add(name);

            parser = Parser.VALUE_END;

            kind = DeclarationKind.CUSTOM_COLOR_VALUE;
          }

          else if ('0' <= c && c <= '9') {
            if (!custom) {
              throw error("Unexpected value: regular prop + dimension value");
            }

            parser = Parser.VALUE_END;

            kind = DeclarationKind.CUSTOM_DIMENSION;
          }

          else if (c == 'v') {
            parser = Parser.VALUE_VAR;

            dash = 0;
          }

          else {
            parser = Parser.VALUE_END;

            kind = custom ? DeclarationKind.CUSTOM : DeclarationKind.REGULAR;
          }
        }

        case VALUE_VAR -> {
          if (c == 'a' || c == 'r' || c == '(') {
            parser = Parser.VALUE_VAR;
          }

          else if (c == '-') {
            parser = Parser.VALUE_VAR_PREFIX;

            dash = 1;
          }

          else {
            throw error("Unexpected char in var() c=" + c);
          }
        }

        case VALUE_VAR_PREFIX -> {
          if (c == 'c' || c == 'd' || c == 's') {
            parser = Parser.VALUE_VAR_PREFIX;
          }

          else if (c == '-') {
            dash += 1;

            if (dash == 3) {
              ctx.markCursor();

              parser = Parser.VALUE_VAR_VAL0;
            }
          }
        }

        case VALUE_VAR_VAL0 -> {
          if (c == ')') {
            parser = Parser.VALUE_VAR_END;

            kind = custom ? DeclarationKind.CUSTOM_VAR : DeclarationKind.REGULAR_VAR;

            val0 = ctx.name();
          }

          else if (c == ',') {
            parser = Parser.VALUE_VAR_VAL1;

            val0 = ctx.name();
          }
        }

        case VALUE_VAR_VAL1 -> {
          if (c == ' ') {
            parser = Parser.VALUE_VAR_VAL1;
          }

          else if (c == '#') {
            parser = Parser.VALUE_VAR_VAL1_COLOR;

            kind = DeclarationKind.CUSTOM_COLOR_VAR2;

            ctx.markIdx();
          }

          else {
            throw error("Unexpected fallback value for var(): only #aabbcc style colors supported");
          }
        }

        case VALUE_VAR_VAL1_COLOR -> {
          if ('0' <= c && c <= '9') {
            parser = Parser.VALUE_VAR_VAL1_COLOR;
          }

          else if ('a' <= c && c <= 'f') {
            parser = Parser.VALUE_VAR_VAL1_COLOR;
          }

          else if (c == ')') {
            parser = Parser.VALUE_VAR_END;

            val1 = ctx.name();

            theme.add(kind, name, val0, val1);
          }

          else {
            throw error("Unexpected char in hex color: c=" + c);
          }
        }

        case VALUE_VAR_END -> {
          if (c == ';') {
            parser = Parser.START;

            theme.add(kind, name, val0, val1);
          }

          else if (c == '}') {
            theme.add(kind, name, val0, val1);

            break loop;
          }

          else {
            throw error("Unexpected char in var() end: c=" + c);
          }
        }

        case VALUE_END -> {
          if (c == ';') {
            parser = Parser.START;

            val0 = ctx.name();

            theme.add(kind, name, val0, val1);
          }

          else if (c == '}') {
            val0 = ctx.name();

            theme.add(kind, name, val0, val1);

            break loop;
          }
        }
      }
    }
  }

  // ##################################################################
  // # END: CDS: CSS
  // ##################################################################

  // ##################################################################
  // # BEGIN: CDS: Write
  // ##################################################################

  private void cdsWrite(String version) {
    final Path path;
    path = Path.of("main", "objectos", "ui", "CarbonStyles.java");

    final Path file;
    file = basedir.resolve(path);

    final Path parent;
    parent = file.getParent();

    try {
      Files.createDirectories(parent);
    } catch (IOException e) {
      throw error("Failed to create directory for CarbonStyles.java", e);
    }

    try (BufferedWriter w = Files.newBufferedWriter(
        file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
    )) {
      w.write("""
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

import java.util.function.Consumer;
import objectos.way.Css;

final class CarbonStyles implements Consumer<Css.StyleSheet.Options> {

  static final String VERSION = "%s";

  @Override
  public final void accept(Css.StyleSheet.Options opts) {
""".formatted(version));

      int idx;
      idx = 0;

      for (Theme theme : themes.values()) {
        if (idx++ != 0) {
          w.newLine();
        }

        executeStyles(w, theme);
      }

      w.write("""
  }

}
      """);

    } catch (IOException e) {
      throw error("Failed to generate CarbonStyleSheet.java", e);
    }
  }

  private void executeStyles(BufferedWriter w, Theme theme) throws IOException {
    w.write("    opts.theme(");

    for (String name : theme.names) {
      w.write('"');
      w.write(name);
      w.write('"');
      w.write(',');
      w.write(' ');
    }

    w.write("\"\"\"\n");

    for (Declaration decl : theme.declarations) {
      w.write("    ");

      String n = null, v = null;

      switch (decl.kind) {
        case CUSTOM -> {
          if (colors.contains(decl.name)) {
            n = "--color-" + decl.name;
          } else {
            n = "--carbon-" + decl.name;
          }

          v = decl.val0;
        }

        case CUSTOM_COLOR_VALUE -> { n = "--color-" + decl.name; v = decl.val0; }

        case CUSTOM_COLOR_VAR2 -> { n = "--color-" + decl.name; v = "var(--color-" + decl.val0 + ", " + decl.val1 + ")"; }

        case CUSTOM_DIMENSION -> { n = "--carbon-" + decl.name; v = decl.val0; }

        case CUSTOM_VAR -> {
          if (colors.contains(decl.name)) {
            n = "--color-" + decl.name;

            v = "var(--color-" + decl.val0 + ")";
          } else {
            throw new UnsupportedOperationException("Implement me");
          }
        }

        case REGULAR -> throw new UnsupportedOperationException("Implement me :: decl=" + decl);

        case REGULAR_VAR -> {
          n = decl.name;

          if (colors.contains(decl.val0)) {
            v = "var(--color-" + decl.val0 + ")";
          } else {
            throw new UnsupportedOperationException("Implement me :: val0=" + decl.val0);
          }
        }
      }

      w.write(n);

      w.write(": ");

      w.write(v);

      w.write(";\n");
    }

    w.write("""
    \""");
""");
  }

  // ##################################################################
  // # END: CDS: Write
  // ##################################################################

  // ##################################################################
  // # END: CDS
  // ##################################################################

  // ##################################################################
  // # BEGIN: C4P
  // ##################################################################

  private void c4p(Options options) {
    final String htmlLocation;
    htmlLocation = options.c4pHtml.string();

    final URI htmlUri;
    htmlUri = URI.create(htmlLocation);

    final String html;
    html = read(options, htmlUri, "c4p.html");

    final String jsPath;
    jsPath = c4pJsPath(html);

    final String version;
    version = c4pJsVersion(options, jsPath);

    c4pWrite(version);
  }

  /// We're looking for:
  ///
  /// ```html
  /// <script type="module" crossorigin src="./assets/iframe-CnhrW--F.js"></script>
  /// ```
  private String c4pJsPath(String s) {
    for (int idx = 0, len = s.length(); idx < len; idx++) {
      final char c;
      c = s.charAt(idx);

      if (c != '=') {
        continue;
      }

      // ensure we're in a 'src' attribute
      final int src;
      src = idx - 3;

      if (!s.regionMatches(src, "src", 0, 3)) {
        continue;
      }

      final int left;
      left = idx + 1;

      if (s.charAt(left) != '"') {
        continue;
      }

      final int start;
      start = left + 1;

      final int end;
      end = s.indexOf('"', start);

      if (end < 0) {
        throw error("Failed to find string ending quote");
      }

      final String path;
      path = s.substring(start, end);

      if (!path.contains("ifram") && !path.endsWith(".js")) {
        continue;
      }

      logInfo("Found  JS: %s", path);

      return path;
    }

    throw error("Failed to find path for C4P JS file");
  }

  private String c4pJsVersion(Options options, String jsPath) {
    throw new UnsupportedOperationException("Implement me");
  }

  private void c4pWrite(String version) {

  }

  //
  // const y$e="Carbon for IBM Products",w$e="2.73.0-rc.0",x$e={description:y$e,version:w$e}
  //

  // ##################################################################
  // # END: C4P
  // ##################################################################

  // ##################################################################
  // # BEGIN: I/O
  // ##################################################################

  private String read(Options options, URI uri, String dest) {
    final Path target;
    target = resolveWork(options, dest);

    return read(options, uri, target);
  }

  private String read(Options options, URI uri, Path target) {
    try {
      final URL url;
      url = uri.toURL();

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
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
      }

      return Files.readString(target, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private URI resolveUri(Option option, String name) {
    final String location;
    location = option.string();

    final URI uri;
    uri = URI.create(location);

    return uri.resolve(name);
  }

  private Path resolveWork(Options options, String name) {
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

  private RuntimeException error(String message) {
    return new RuntimeException(message);
  }

  private RuntimeException error(String message, IOException e) {
    return new UncheckedIOException(message, e);
  }

  // ##################################################################
  // # END: Logging
  // ##################################################################

}
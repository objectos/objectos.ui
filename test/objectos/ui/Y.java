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

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;
import objectos.way.App;
import objectos.way.Html;
import objectos.way.Io;
import objectos.way.Note;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public final class Y implements ISuiteListener {

  /// Sole constructor. Required by TestNG.
  public Y() {}

  @SuppressWarnings("exports")
  @Override
  public final void onStart(ISuite suite) {
    final App.Bootstrap bootstrap;
    bootstrap = new DevStart();

    bootstrap.start(new String[0]);
  }

  // ##################################################################
  // # BEGIN: Next
  // ##################################################################

  private static final class NextPath implements Closeable {

    private final Path root;

    private NextPath(Path root) {
      this.root = root;
    }

    private static NextPath create() {
      try {
        final Path root;
        root = Files.createTempDirectory("objectos-ui-testing-");

        final NextPath nextPath;
        nextPath = new NextPath(root);

        shutdownHook(nextPath);

        return nextPath;
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

    @Override
    public final void close() throws IOException {
      Io.deleteRecursively(root);
    }

    final Path nextTempDir() {
      try {
        return Files.createTempDirectory(root, "next-temp-dir");
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

    final Path nextTempFile() {
      try {
        return Files.createTempFile(root, "next-temp-file", ".tmp");
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

  }

  private static final class NextPathHolder {

    static final NextPath INSTANCE = NextPath.create();

  }

  public static Path nextTempDir() {
    return NextPathHolder.INSTANCE.nextTempDir();
  }

  public static Path nextTempFile() {
    return NextPathHolder.INSTANCE.nextTempFile();
  }

  public static Path nextTempFile(String contents) {
    return nextTempFile(contents, StandardCharsets.UTF_8);
  }

  public static Path nextTempFile(String contents, Charset charset) {
    try {
      final Path file;
      file = NextPathHolder.INSTANCE.nextTempFile();

      Files.writeString(file, contents, charset);

      return file;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  // ##################################################################
  // # END: Next
  // ##################################################################

  // ##################################################################
  // # BEGIN: Note.Sink
  // ##################################################################

  private static final App.NoteSink INSTANCE = App.NoteSink.ofAppendable(System.out, opts -> {
    opts.filter(note -> {
      final String source;
      source = note.source();

      if (source.startsWith("objectos.ui")) {
        return true;
      }

      return note.hasAny(Note.ERROR, Note.WARN, Note.INFO);
    });
  });

  public static Note.Sink noteSink() {
    return INSTANCE;
  }

  // ##################################################################
  // # END: Note.Sink
  // ##################################################################

  // ##################################################################
  // # BEGIN: Playwright
  // ##################################################################

  static final class BrowserHolder {
    static final Browser BROWSER = init();

    private static Browser init() {
      final Playwright playwright;
      playwright = Playwright.create();

      shutdownHook(playwright);

      final BrowserType chromium;
      chromium = playwright.chromium();

      final boolean headless;
      headless = Boolean.getBoolean("playwright.headless");

      final LaunchOptions launchOptions;
      launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);

      return chromium.launch(launchOptions);
    }
  }

  public enum ScreenSize {
    XS(360, 640),

    SM(640, 1136),

    MD(768, 1024),

    LG(1024, 768),

    XL(1280, 720),

    X2(1536, 864);

    final int width;
    final int height;

    private ScreenSize(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }

  public static final Set<ScreenSize> SCREEN_SIZES = EnumSet.allOf(ScreenSize.class);

  static Page page() {
    return BrowserHolder.BROWSER.newPage();
  }

  public sealed interface Tab extends AutoCloseable permits YTab {

    TabElem byId(Html.Id id);

    @Override
    void close();

    void dev();

    void mouseDown();

    void mouseUp();

    void mouseTo(double x, double y);

    void navigate(String path);

    void navigate(String path, Theme theme);

    void press(String key);

    void screenshot(String... suffixes);

    String title();

    void waitForFunction(String expression, Object arg);

  }

  public sealed interface TabElem permits YTab.ThisElem {

    void blur();

    void focus();

    void hover();

  }

  public static Y.Tab tabDev() {
    return tabDev(ScreenSize.XL);
  }

  public static Y.Tab tabDev(ScreenSize size) {
    final String baseUrl;
    baseUrl = "http://objectos.ui.localhost:" + DevStart.TESTING_HTTP_PORT;

    Browser.NewPageOptions options;
    options = new Browser.NewPageOptions().setBaseURL(baseUrl).setViewportSize(size.width, size.height);

    final Page page;
    page = BrowserHolder.BROWSER.newPage(options);

    return new YTab(baseUrl, page);
  }

  // ##################################################################
  // # END: Playwright
  // ##################################################################

  // ##################################################################
  // # BEGIN: Project
  // ##################################################################

  public sealed interface Project extends AutoCloseable permits YProject {

    sealed interface Options permits YProjectBuilder {

      void addFile(String relativePath, String contents);

      void webdir(Path value);

    }

    @Override
    void close();

    default boolean exists(String relativePath) {
      final Path maybe;
      maybe = resolve(relativePath);

      return Files.exists(maybe);
    }

    Path basedir();

    Path resolve(String relativePath);

    URI resolveWeb(String relativePath);

    void carbonGen(String... args);

  }

  public static Project project(Consumer<? super Y.Project.Options> opts) {
    final YProjectBuilder builder;
    builder = new YProjectBuilder();

    opts.accept(builder);

    return new YProject(builder);
  }

  // ##################################################################
  // # END: Project
  // ##################################################################

  // ##################################################################
  // # BEGIN: ShutdownHook
  // ##################################################################

  private static final class ShutdownHookHolder {

    static final App.ShutdownHook INSTANCE = App.ShutdownHook.create(config -> config.noteSink(noteSink()));

  }

  public static App.ShutdownHook shutdownHook() {
    return ShutdownHookHolder.INSTANCE;
  }

  public static void shutdownHook(AutoCloseable closeable) {
    ShutdownHookHolder.INSTANCE.register(closeable);
  }

  // ##################################################################
  // # END: ShutdownHook
  // ##################################################################

  // ##################################################################
  // # BEGIN: Tab
  // ##################################################################

  // ##################################################################
  // # END: Tab
  // ##################################################################

}

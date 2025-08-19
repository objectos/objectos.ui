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
import com.microsoft.playwright.Page;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import objectos.ui.Carbon.Theme;
import objectos.way.App;
import objectos.way.Io;
import objectos.way.Note;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public final class Y implements ISuiteListener {

  @SuppressWarnings("exports")
  public static Browser BROWSER;

  /// Sole constructor. Required by TestNG.
  public Y() {}

  @SuppressWarnings("exports")
  @Override
  public final void onStart(ISuite suite) {
    final App.Bootstrap bootstrap;
    bootstrap = new YStart();

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
        root = Files.createTempDirectory("objectos-start-testing-");

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

  private static final App.NoteSink INSTANCE = App.NoteSink.sysout();

  public static Note.Sink noteSink() {
    return INSTANCE;
  }

  // ##################################################################
  // # END: Note.Sink
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

  public static void shutdownHook(AutoCloseable closeable) {
    ShutdownHookHolder.INSTANCE.register(closeable);
  }

  // ##################################################################
  // # END: ShutdownHook
  // ##################################################################

  // ##################################################################
  // # BEGIN: Tab
  // ##################################################################

  public sealed interface Tab extends AutoCloseable permits YTab {

    @Override
    void close();

    void navigate(String path);

    void navigate(String path, Theme theme);

    String title();

    void dev();

    void screenshot();

  }

  public static Y.Tab newTab() {
    final String baseUrl;
    baseUrl = "http://objectos.ui.localhost:" + YStart.TESTING_HTTP_PORT;

    Browser.NewPageOptions options;
    options = new Browser.NewPageOptions().setBaseURL(baseUrl);

    final Page page;
    page = BROWSER.newPage(options);

    return new YTab(baseUrl, page);
  }

  // ##################################################################
  // # END: Tab
  // ##################################################################

}

/*
 * Objectos Start
 * Copyright (C) 2025 Objectos Software LTDA.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package objectos.ui;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import objectos.way.Http;
import objectos.way.Note;
import objectos.way.Web;

final class YProjectBuilder implements Y.Project.Options {

  private static final AtomicInteger PORTS = new AtomicInteger(4000);

  final Path basedir;

  URI baseUrl;

  final Note.Sink noteSink = Y.noteSink();

  private int port;

  Path webdir;

  YProjectBuilder() {
    final Path root;
    root = Y.nextTempDir();

    basedir = root;
  }

  @Override
  public final void addFile(String relativePath, String contents) {
    createFile(basedir, relativePath, contents);
  }

  @Override
  public final void webdir(Path value) {
    if (port == 0) {
      port = PORTS.getAndIncrement();

      baseUrl = URI.create("http://localhost:" + port + "/");
    }

    webdir = Objects.requireNonNull(value, "value == null");
  }

  private void createFile(Path dir, String relativePath, String contents) {
    try {
      final Path file;
      file = dir.resolve(relativePath);

      final Path parent;
      parent = file.getParent();

      Files.createDirectories(parent);

      Files.writeString(file, contents, StandardOpenOption.CREATE_NEW);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  final Closeable server() {
    if (webdir == null) {
      return () -> {};
    }

    try {
      final Web.Resources webResources;
      webResources = Web.Resources.create(opts -> {
        opts.addDirectory(webdir);

        opts.contentTypes("""
        .css: text/css; charset=utf-8
        .html: text/html; charset=utf-8
        .js: text/javascript
        .json: application/json
        """);

        opts.noteSink(noteSink);
      });

      Y.shutdownHook(webResources);

      final Http.Server server;
      server = Http.Server.create(opts -> {
        opts.bufferSize(8192, 8192);

        opts.handler(Http.Handler.of(routing -> {
          routing.handler(webResources);

          routing.handler(Http.Handler.notFound());
        }));

        opts.noteSink(noteSink);

        opts.port(port);
      });

      server.start();

      return server;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}

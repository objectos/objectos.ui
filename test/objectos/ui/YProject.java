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
import java.net.URI;
import java.nio.file.Path;
import objectos.ui.gen.XUi;
import objectos.way.Note;

final class YProject implements Y.Project {

  private record Notes(
      Note.Ref1<String> stderr,
      Note.Ref1<String> stdout,
      Note.Ref1<IOException> ioException
  ) {

    static Notes get() {
      Class<?> s;
      s = Y.Project.class;

      return new Notes(
          Note.Ref1.create(s, "STE", Note.INFO),
          Note.Ref1.create(s, "STO", Note.INFO),
          Note.Ref1.create(s, "IOX", Note.ERROR)
      );
    }

  }

  private final Path basedir;

  private final URI baseUrl;

  private final Notes notes = Notes.get();

  private final Note.Sink noteSink;

  private final Closeable server;

  YProject(YProjectBuilder builder) {
    basedir = builder.basedir;

    baseUrl = builder.baseUrl;

    noteSink = builder.noteSink;

    server = builder.server();
  }

  @Override
  public final Path basedir() {
    return basedir;
  }

  @Override
  public final void close() {
    if (server != null) {
      try {
        server.close();
      } catch (IOException e) {
        noteSink.send(notes.ioException, e);
      }
    }
  }

  @Override
  public final Path resolve(String relativePath) {
    final Path path;
    path = Path.of(relativePath);

    if (path.isAbsolute()) {
      throw new IllegalArgumentException("Path must not be absolute: " + path);
    }

    return basedir.resolve(path);
  }

  @Override
  public final URI resolveWeb(String relativePath) {
    if (baseUrl == null) {
      throw new UnsupportedOperationException("not a web project");
    }

    return baseUrl.resolve(relativePath);
  }

  @Override
  public final void gen(String... args) {
    XUi.gen(basedir, Y.BrowserHolder.BROWSER, args);
  }

}

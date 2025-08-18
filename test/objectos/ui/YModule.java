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

import static objectos.way.Http.Method.GET;

import java.nio.file.Path;
import objectos.way.App;
import objectos.way.Css;
import objectos.way.Http;
import objectos.way.Media;
import objectos.way.Note;
import objectos.way.Script;

public class YModule implements Http.Routing.Module {

  private final App.Injector injector;

  public YModule(App.Injector injector, Module original) {
    this.injector = injector;

    final Class<? extends YModule> self;
    self = getClass();

    final Module module;
    module = self.getModule();

    module.addReads(original);
  }

  @Override
  public final void configure(Http.Routing routing) {
    routing.install(new CarbonModule());

    routing.path("/script.js", path -> {
      path.allow(GET, http -> http.ok(Script.Library.of()));
    });

    routing.path("/styles.css", path -> {
      path.allow(GET, this::styles);
    });

    routing.path("/dev-stop", path -> {
      path.allow(Http.Method.GET, http -> http.ok(Media.Bytes.textPlain("ok\n")));
    });

    routing.handler(Http.Handler.notFound());
  }

  private void styles(Http.Exchange http) {
    http.ok(Css.StyleSheet.create(opts -> {
      final Note.Sink noteSink;
      noteSink = injector.getInstance(Note.Sink.class);

      opts.noteSink(noteSink);

      final Path classOutput;
      classOutput = Path.of("work", "dev");

      opts.scanDirectory(classOutput);

      opts.theme("""
          --color-bg: var(--color-gray-100);
          --color-fg: var(--color-gray-900);
          """);

      opts.theme("@media (prefers-color-scheme: dark)", """
          --color-bg: var(--color-gray-900);
          --color-fg: var(--color-gray-100);
          """);
    }));
  }

}
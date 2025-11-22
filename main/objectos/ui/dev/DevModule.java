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
package objectos.ui.dev;

import static objectos.way.Http.Method.GET;

import module java.base;
import module objectos.ui;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
public class DevModule implements Http.Routing.Module {

  private final App.Injector injector;

  public DevModule(App.Injector injector) {
    this.injector = injector;
  }

  @Override
  public final void configure(Http.Routing routing) {
    routing.install(new DevButton());
    routing.install(new DevGrid());
    routing.install(new DevHeader());
    routing.install(new DevUi());

    routing.path("/dev-stop", Http.Method.GET, http -> http.ok(Media.Bytes.textPlain("ok\n")));

    routing.path("/script.js", GET, http -> http.ok(Script.Library.of()));

    routing.path("/styles.css", GET, this::styles);

    final Web.Resources webResources;
    webResources = injector.getInstance(Web.Resources.class);

    routing.handler(webResources);

    routing.handler(Http.Handler.notFound());
  }

  private void styles(Http.Exchange http) {
    http.ok(Css.StyleSheet.create(opts -> {
      final Note.Sink noteSink;
      noteSink = injector.getInstance(Note.Sink.class);

      opts.noteSink(noteSink);

      final Path classOutput;
      classOutput = Path.of("work", "main");

      opts.scanDirectory(classOutput);

      final Css.Library styles;
      styles = injector.getInstance(DevStart.CARBON_STYLES);

      opts.include(styles);

      final Css.Library plex;
      plex = injector.getInstance(DevStart.CARBON_PLEX);

      opts.include(plex);
    }));
  }

}
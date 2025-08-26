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
import java.util.function.Consumer;
import objectos.way.App;
import objectos.way.Css;
import objectos.way.Html;
import objectos.way.Http;
import objectos.way.Note;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
public final class DevCarbon implements Http.RoutingPath.Module {

  private static final Html.Id MODAL = Html.Id.of("carbon-modal");

  private final App.Injector injector;

  public DevCarbon(App.Injector injector) {
    this.injector = injector;
  }

  @Override
  public final void configure(Http.RoutingPath carbon) {
    carbon.subpath("/page/{theme}", GET, this::page);
    carbon.subpath("/tearsheet/{id}/{theme}", GET, this::tearsheet);

    carbon.subpath("/styles.css", GET, this::styles);

    carbon.handler(Http.Handler.notFound());
  }

  private void page(Http.Exchange http) {
    http.ok(Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.title("Objectos Carbon");
    }));
  }

  private Html.Component page(Http.Exchange http, Consumer<? super Carbon.Page> more) {
    return Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  private void tearsheet(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Tearsheet - Default");

        page.add(Carbon.tearsheet(t -> {
          t.id(MODAL);

          t.open(true);
        }));
      }));
    }
  }

  private void styles(Http.Exchange http) {
    http.ok(Css.StyleSheet.create(opts -> {
      final Note.Sink noteSink;
      noteSink = injector.getInstance(Note.Sink.class);

      opts.noteSink(noteSink);

      final Path classOutput;
      classOutput = Path.of("work", "main");

      opts.scanDirectory(classOutput);

      Carbon.configureStyleSheet(opts);
    }));
  }

  private Carbon.Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Carbon.Theme.of(themeName);
  }

}

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
import objectos.way.Note;

final class CarbonModule implements Http.Routing.Module {

  private final App.Injector injector;

  CarbonModule(App.Injector injector) {
    this.injector = injector;
  }

  @Override
  public final void configure(Http.Routing carbon) {
    carbon.path("/carbon/page/{theme}", GET, this::page);

    CarbonTearsheetTest.module(carbon);

    carbon.path("/carbon/styles.css", GET, this::styles);
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

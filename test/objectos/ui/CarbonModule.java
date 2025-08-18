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

import objectos.way.Http;

final class CarbonModule implements Http.Routing.Module {

  @Override
  public final void configure(Http.Routing carbon) {
    carbon.path("/carbon/page/{theme}", GET, this::page);
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

  private Carbon.Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Carbon.Theme.of(themeName);
  }

}

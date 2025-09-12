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

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import objectos.ui.carbon.CarbonTheme;
import objectos.way.Html;
import objectos.way.Http;
import org.testng.annotations.DataProvider;

public abstract class CarbonTest {

  CarbonTest() {}

  @DataProvider
  public final Iterator<Object[]> all() {
    return THEMES.stream().flatMap(t -> Y.SCREEN_SIZES.stream().map(s -> new Object[] {t, s})).iterator();
  }

  private static final Set<CarbonTheme> THEMES = EnumSet.allOf(CarbonTheme.class);

  @DataProvider
  public final Iterator<Carbon.Theme> themes() {
    return THEMES.stream().map(Carbon.Theme.class::cast).iterator();
  }

  static Html.Component page(Http.Exchange http, Consumer<? super Carbon.Page> more) {
    return Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  private static Carbon.Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Carbon.Theme.of(themeName);
  }

}

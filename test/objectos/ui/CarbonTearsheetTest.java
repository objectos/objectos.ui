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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Y.class)
public class CarbonTearsheetTest extends CarbonTest {

  static void module(Http.Routing carbon) {
    carbon.path("/carbon/tearsheet/tc01/{theme}", GET, CarbonTearsheetTest::testCase01);
  }

  static void testCase01(Http.Exchange http) {
    http.ok(page(http, page -> {
      page.title("Objectos Carbon");

      page.add(Carbon.tearsheet(t -> {
        t.visible(true);
      }));
    }));
  }

  @Test(enabled = false)
  public void testCase01(Carbon.Theme theme, Y.ScreenSize screen) {
    try (Y.Tab tab = Y.tabDev(screen)) {
      tab.navigate("/carbon/tearsheet/tc01", theme);

      tab.dev();
    }
  }

}

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

import java.util.List;
import objectos.way.Html;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Y.class)
public class CarbonButtonTest extends CarbonTest {

  private final List<Html.Id> buttons = List.of(
      DevCarbon.BTN,
      DevCarbon.BTN_SM,
      DevCarbon.BTN_MD,
      DevCarbon.BTN_XL,
      DevCarbon.BTN_X2L
  );

  @Test(dataProvider = "themes")
  public void defaultTest(Carbon.Theme theme) {
    test("default", theme);
  }

  @Test(dataProvider = "themes")
  public void secondary(Carbon.Theme theme) {
    test("secondary", theme);
  }

  private void test(String kind, Carbon.Theme theme) {
    try (Y.Tab tab = Y.tabDev()) {
      tab.navigate("/carbon/button/" + kind, theme);

      tab.screenshot();

      for (Html.Id id : buttons) {
        final Y.TabElem btn;
        btn = tab.byId(id);

        btn.focus();

        tab.screenshot(id.value(), "focus");

        btn.blur();

        btn.hover();

        tab.screenshot(id.value(), "hover");

        tab.mouseDown();

        tab.screenshot(id.value(), "active");

        tab.mouseUp();

        tab.mouseTo(0, 0);
      }
    }

  }

}

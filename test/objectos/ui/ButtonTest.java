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
import objectos.ui.dev.DevButton;
import objectos.way.Html;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Y.class)
public class ButtonTest extends UiTest {

  private final List<Html.Id> buttons = List.of(
      DevButton.BTN,
      DevButton.BTN_SM,
      DevButton.BTN_MD,
      DevButton.BTN_XL,
      DevButton.BTN_X2L
  );

  @Test(dataProvider = "themes")
  public void defaultTest(Theme theme) {
    test("default", theme);
  }

  @Test(dataProvider = "themes")
  public void secondary(Theme theme) {
    test("secondary", theme);
  }

  @Test(dataProvider = "themes")
  public void ghost(Theme theme) {
    test("ghost", theme);
  }

  private void test(String kind, Theme theme) {
    try (Y.Tab tab = Y.tabDev()) {
      tab.navigate("/button/" + kind, theme);

      tab.screenshot();

      for (Html.Id id : buttons) {
        final Y.TabElem btn;
        btn = tab.byId(id);

        btn.focus();

        tab.screenshot(id.attrValue(), "focus");

        btn.blur();

        btn.hover();

        tab.screenshot(id.attrValue(), "hover");

        tab.mouseDown();

        tab.screenshot(id.attrValue(), "active");

        tab.mouseUp();

        tab.mouseTo(0, 0);
      }
    }

  }

}

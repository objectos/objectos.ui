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

import module java.base;
import module objectos.ui;
import objectos.ui.impl.UiTabs;
import objectos.ui.impl.UiTabsTab;

/// Tabs are used to organize related content.
public sealed interface Tabs extends Html.Component permits UiTabs {

  /// Configures the creation of an individual tab in a tab list.
  sealed interface Tab permits UiTabsTab {

    /// Sets the `data-on-click` attribute value to the specified script.
    /// @param value the Objectos Script to execute
    void dataOnClick(Consumer<? super Script> value);

    /// Renders this tab as an `<a>` element with the specified `href` attribute value.
    /// @param value the `href` attribute value
    void href(String value);

    /// Sets the text value of this tab to the specified value.
    /// @param value the text value
    void text(String value);

  }

  /// Configures the creation of a `Tabs` component.
  sealed interface Options permits UiTabs {

    void selectedIndex(int value);

    /// Adds a tab with the specified options to this component.
    /// @param tab allows for setting the options
    void tab(Consumer<? super Tab> tab);

  }

  /// Creates a new tab list with the specified options.
  /// @param tabs allows for setting the options
  /// @return a newly created tab list with the specified options
  static Tabs create(Consumer<? super Options> tabs) {
    final UiTabs pojo;
    pojo = new UiTabs();

    tabs.accept(pojo);

    return pojo;
  }

}

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
import objectos.ui.impl.UiContainedListItem;

/// Renders an item in a contained list.
public sealed interface ContainedListItem extends Html.Component permits UiContainedListItem {

  /// Configures the creation of a contained list item.
  sealed interface Options permits UiContainedListItem {

    /// Sets the child component to be rendered in this list item.
    /// @param value the child component
    void set(Html.Component value);

  }

  /// Creates a new list item with the specified options.
  ///
  /// @param item allows for setting the options
  ///
  /// @return a newly created list item with the specified options
  static ContainedListItem create(Consumer<? super Options> item) {
    final UiContainedListItem pojo;
    pojo = new UiContainedListItem();

    item.accept(pojo);

    return pojo;
  }

}

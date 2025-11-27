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
import objectos.ui.impl.UiContainedList;

/// Contained lists group content that is similar or related
/// and can contain read-only or interactive information.
public sealed interface ContainedList extends Html.Component permits UiContainedList {

  /// Configures the creation of a contained list.
  sealed interface Options permits UiContainedList {

    /// Adds a child component to the end of this contained list.
    /// @param value the child component
    void add(Html.Component value);

    /// A descriptive name for the group of list items.
    /// @param value the list title
    void label(String value);

  }

  /// Creates a new list with the specified options.
  ///
  /// @param list allows for setting the options
  ///
  /// @return a newly created list with the specified options
  static ContainedList create(Consumer<? super Options> list) {
    final UiContainedList pojo;
    pojo = new UiContainedList();

    list.accept(pojo);

    return pojo;
  }

}

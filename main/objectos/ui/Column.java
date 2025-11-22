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
import module objectos.way;
import objectos.ui.impl.UiColumn;

/// A column in a grid component.
public sealed interface Column extends Html.Component permits UiColumn {

  /// Configures the creation of a column.
  sealed interface Options permits UiColumn {

    /// Applies the specified Objectos CSS to the HTML element of the column.
    /// @param value the Objectos CSS to apply
    void css(String value);

    void span(int value);

    void span(Breakpoint breakpoint, int value);

  }

  /// Creates a new column with the specified options.
  ///
  /// @param col allows for setting the options
  ///
  /// @return a newly created column with the specified options
  static Column create(Consumer<? super Options> col) {
    final UiColumn pojo;
    pojo = new UiColumn();

    col.accept(pojo);

    return pojo;
  }

}

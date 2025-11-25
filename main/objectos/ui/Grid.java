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
import objectos.ui.impl.UiGrid;

/// The `Grid` component provides a foundation for building layouts.
public sealed interface Grid extends Html.Component permits UiGrid {

  /// Configures the creation of a grid.
  sealed interface Options permits UiGrid {

    /// Adds a child component to the end of this grid.
    /// @param value the child component
    void add(Html.Component value);

    /// Applies the specified Objectos CSS to the HTML element of the grid.
    /// @param value the Objectos CSS to apply
    void css(String value);

    /// Causes the grid to span the full width of its container.
    void fullWidth();

  }

  /// Creates a new grid with the specified options.
  ///
  /// @param grid allows for setting the options
  ///
  /// @return a newly created grid with the specified options
  static Grid create(Consumer<? super Options> grid) {
    final UiGrid pojo;
    pojo = new UiGrid();

    grid.accept(pojo);

    return pojo;
  }

}

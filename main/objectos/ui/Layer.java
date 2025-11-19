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
import objectos.ui.impl.CarbonLayer;

/// The `Layer` component is used to render components on different layers.
public sealed interface Layer extends Html.Component permits CarbonLayer {

  /// Configures the creation of a layer.
  sealed interface Options permits CarbonLayer {

    /// Adds a child component to the end of this layer.
    ///
    /// @param value the child component
    void add(Html.Component value);

  }

  /// Creates a new layer with the specified options.
  ///
  /// @param layer allows for setting the options
  ///
  /// @return a newly created layer with the specified options
  static Layer create(Consumer<? super Options> layer) {
    final CarbonLayer pojo;
    pojo = new CarbonLayer();

    layer.accept(pojo);

    return pojo;
  }

}

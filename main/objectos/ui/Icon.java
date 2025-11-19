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

import objectos.ui.impl.CarbonIcon;
import module java.base;
import module objectos.way;

/// Provides a predefined set of icons.
public sealed interface Icon extends Html.Component permits CarbonIcon {

  /// Configures the creation of an icon.
  sealed interface Options permits CarbonIcon {

    /// Applies the specified Objectos CSS to the `<svg>` element.
    /// @param value the Objectos CSS to apply
    void css(String value);

    /// Sets the size to 16x16 pixels.
    void size16();

    /// Sets the size to 20x20 pixels.
    void size20();

    /// Sets the size to 24x24 pixels.
    void size24();

    /// Sets the size to 32x32 pixels.
    void size32();

    // START generated code

    void iconWarningAlt();

    void iconWarningFilled();

    // END generated code

  }

  /// Creates a new icon with the specified options.
  ///
  /// @param icon allows for setting the options
  ///
  /// @return a newly created icon with the specified options
  static Icon create(Consumer<? super Options> icon) {
    final CarbonIcon pojo;
    pojo = new CarbonIcon();

    icon.accept(pojo);

    return pojo;
  }

}
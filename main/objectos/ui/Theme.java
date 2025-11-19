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

import java.util.Locale;
import objectos.ui.impl.CarbonTheme;

/// A theme provides a set of pre-defined colors and styles.
public sealed interface Theme permits CarbonTheme {

  /// The White theme.
  Theme WHITE = CarbonTheme.WHITE;

  /// The Gray 10 theme.
  Theme G10 = CarbonTheme.G10;

  /// The Gray 90 Theme.
  Theme G90 = CarbonTheme.G90;

  /// The Gray 100 Theme.
  Theme G100 = CarbonTheme.G100;

  /// Returns the `Theme` instance with the specified name.
  ///
  /// @param name the theme short name, e.g., `g90`.
  ///
  /// @return the `Theme` instance
  /// @throws IllegalArgumentException if there's no `Theme` instance with the specified name
  static Theme of(String name) {
    final String upper;
    upper = name.toUpperCase(Locale.US);

    return CarbonTheme.valueOf(upper);
  }

}
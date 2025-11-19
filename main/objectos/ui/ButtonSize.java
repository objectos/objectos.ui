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

import objectos.ui.impl.CarbonButtonSize;

/// Represents the size of a button.
public sealed interface ButtonSize permits CarbonButtonSize {

  /// The Small (`sm`) size.
  ButtonSize SM = CarbonButtonSize.SM;

  /// The Medium (`md`) size.
  ButtonSize MD = CarbonButtonSize.MD;

  /// The Large (`lg`) size.
  ButtonSize LG = CarbonButtonSize.LG;

  /// The Extra-Large (`xl`) size.
  ButtonSize XL = CarbonButtonSize.XL;

  /// The Extra-Extra-Large (`x2`) size.
  ButtonSize X2 = CarbonButtonSize.X2L;

}

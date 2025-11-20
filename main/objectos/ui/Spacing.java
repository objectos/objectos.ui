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

import objectos.ui.impl.CarbonSpacing;

/// Represents a spacing token.
public sealed interface Spacing permits CarbonSpacing {

  /// The zero value spacing.
  Spacing SPACING_00 = CarbonSpacing.SPACING_00;

  /// The `$spacing-01` token.
  Spacing SPACING_01 = CarbonSpacing.SPACING_01;

  /// The `$spacing-02` token.
  Spacing SPACING_02 = CarbonSpacing.SPACING_02;

  /// The `$spacing-03` token.
  Spacing SPACING_03 = CarbonSpacing.SPACING_03;

  /// The `$spacing-04` token.
  Spacing SPACING_04 = CarbonSpacing.SPACING_04;

  /// The `$spacing-05` token.
  Spacing SPACING_05 = CarbonSpacing.SPACING_05;

  /// The `$spacing-06` token.
  Spacing SPACING_06 = CarbonSpacing.SPACING_06;

  /// The `$spacing-07` token.
  Spacing SPACING_07 = CarbonSpacing.SPACING_07;

  /// The `$spacing-08` token.
  Spacing SPACING_08 = CarbonSpacing.SPACING_08;

  /// The `$spacing-09` token.
  Spacing SPACING_09 = CarbonSpacing.SPACING_09;

  /// The `$spacing-10` token.
  Spacing SPACING_10 = CarbonSpacing.SPACING_10;

  /// The `$spacing-11` token.
  Spacing SPACING_11 = CarbonSpacing.SPACING_11;

  /// The `$spacing-12` token.
  Spacing SPACING_12 = CarbonSpacing.SPACING_12;

  /// The `$spacing-13` token.
  Spacing SPACING_13 = CarbonSpacing.SPACING_13;

}
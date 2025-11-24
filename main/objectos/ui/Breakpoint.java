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

import objectos.ui.impl.UiBreakpoint;

/// Represents a CSS breakpoint.
public sealed interface Breakpoint permits UiBreakpoint {

  /// The `sm` breakpoint: minimum width 40rem (640px).
  Breakpoint SM = UiBreakpoint.SM;

  /// The `md` breakpoint: minimum width 48rem (768px).
  Breakpoint MD = UiBreakpoint.MD;

  /// The `lg` breakpoint: minimum width 64rem (1024px).
  Breakpoint LG = UiBreakpoint.LG;

  /// The `xl` breakpoint: minimum width 80rem (1280px).
  Breakpoint XL = UiBreakpoint.XL;

  /// The `x2` breakpoint: minimum width 96rem (1536px).
  Breakpoint X2 = UiBreakpoint.X2;

}

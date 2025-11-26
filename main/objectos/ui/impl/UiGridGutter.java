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
package objectos.ui.impl;

import objectos.ui.GridGutter;

public enum UiGridGutter implements GridGutter {

  WIDE("--grid-gutter-start:calc(var(--grid-gutter)/2) --grid-column-hang:calc(var(--grid-gutter)/2)"),

  NARROW("--grid-gutter-start:0rem --grid-column-hang:calc(var(--grid-gutter)/2)"),

  CONDENSED("--grid-gutter:0.0625rem --grid-column-hang:0.96875rem");

  final String css;

  private UiGridGutter(String css) {
    this.css = css;
  }

}

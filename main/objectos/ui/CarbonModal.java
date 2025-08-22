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

import objectos.way.Css;

@Css.Source
final class CarbonModal {

  static final String MODAL = """
  position:fixed
  visibility:hidden

  block-size:100vh
  inline-size:100vw
  inset-block-start:0
  inset-inline-start:0
  z-index:9000

  background-color:overlay
  opacity:0

  display:flex
  align-items:center
  justify-content:center

  content:''
  transition:opacity_240ms_cubic-bezier(0.4,0.14,1,1),visibility_0ms_linear_240ms
  """;

}

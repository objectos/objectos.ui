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
package objectos.ui.carbon;

import objectos.ui.Carbon;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
public final class CarbonModal implements Carbon.Modal, Html.Component {

  static final String MODAL = """
  position:fixed
  visibility:hidden
  z-index:9000

  background-color:overlay
  opacity:0

  block-size:100vh
  inline-size:100vw
  inset-block-start:0
  inset-inline-start:0

  display:flex
  align-items:center
  justify-content:center

  content:''
  transition:opacity_240ms_cubic-bezier(0.4,0.14,1,1),visibility_0ms_linear_240ms
  """;

  static final String IS_VISIBLE = """
  visibility:inherit

  opacity:1

  transition:opacity_240ms_cubic-bezier(0,0,0.3,1),visibility_0ms_linear
  """;

  static final String CONTAINER = """
  background-color: var(--cds-layer);
  position:fixed

  block-size:100%
  inline-size:100%
  inset-block-start:0
  max-block-size:100%

  outline:3px_solid_rgba(0,0,0,0)
  outline-offset:-3px

  display:grid
  grid-template-columns:100%
  grid-template-rows:auto_1fr_auto

  transform:translate3d(0,-24px,0)
  transform-origin:top_center
  transition:transform_240ms_cubic-bezier(0.4, 0.14, 1, 1)
  """;

  private boolean open;

  @Override
  public final void open(boolean value) {
    open = value;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.dialog(
        m.id("reload"),

        m.css("""
        position:fixed
        visibility:hidden
        z-index:9000

        background-color:overlay
        opacity:0

        block-size:100vh
        inline-size:100vw
        inset-block-start:0
        inset-inline-start:0

        display:flex
        align-items:center
        justify-content:center

        content:''
        transition:opacity_240ms_cubic-bezier(0.4,0.14,1,1),visibility_0ms_linear_240ms
        """),
        open ? Html.Markup.open : m.noop()
    );
  }

}

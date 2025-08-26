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

import java.util.Objects;
import objectos.ui.Carbon;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
public final class CarbonTearsheet implements Carbon.Tearsheet, Html.Component {

  static final String CONTAINER = """
  block-size:100%
  inset-block-start:auto
  max-block-size:calc(100%-3rem)

  transform:translate3d(0,min(95vh,500px),0)
  """;

  private Html.Id id;

  private boolean open;

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == value");
  }

  @Override
  public final void open(boolean value) {
    open = value;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.dialog(
        id != null ? m.id(id.value()) : m.noop(),

        m.css("""
        z-index:9000

        block-size:100vh
        inline-size:100vw
        inset-block-start:0
        inset-inline-start:0
        max-block-size:none
        max-inline-size:none

        border:none
        outline:none

        display:flex
        align-items:center
        justify-content:center

        transition:opacity_240ms_cubic-bezier(0.4,0.14,1,1),visibility_0ms_linear_240ms

        backdrop:background-color:overlay
        backdrop:opacity:0

        [open]:backdrop:opacity:1
        """),

        open
            ? m.dataOnLoad(script -> {
              var el = script.element();

              el.showModal();
            })
            : m.noop(),

        m.p("Lorem Ipsum")
    );
  }

}

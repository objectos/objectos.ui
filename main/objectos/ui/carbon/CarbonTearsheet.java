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

  private Html.Id id;

  private boolean open;

  private String title = "";

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == value");
  }

  @Override
  public final void open(boolean value) {
    open = value;
  }

  @Override
  public final void title(String value) {
    title = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    // tearsheet
    m.dialog(
        id != null ? m.id(id.value()) : m.noop(),

        m.css("""
        background-color:layer
        block-size:100%
        border:none
        display:grid
        grid-template-columns:100%
        grid-template-rows:auto_1fr_auto
        inline-size:100%
        inset-block-start:auto
        inset-inline-start:0
        max-block-size:100%
        max-inline-size:100%
        outline:none
        transform:translateY(min(95vh,500px))
        transform-origin:top_center
        transition:transform_.24s_cubic-bezier(0,0,.3,1)
        z-index:9000

        backdrop:background-color:overlay
        backdrop:opacity:0

        [open]:transform:translateY(0)
        [open]:backdrop:opacity:1
        """),

        m.ariaLabel(title),

        open
            ? m.dataOnLoad(script -> {
              script.delay(1, () -> {
                var el = script.element();

                el.showModal();
              });
            })
            : m.noop(),

        // header
        m.div(
            m.css("""
            background-color:layer
            border-block-end:1px_solid_border-subtle
            grid-column:1/-1
            grid-row:1/1
            margin:0
            margin-block-end:.5rem
            max-block-size:50vh
            padding:1.5rem_2rem
            padding-block-start:1rem
            padding-inline:1rem_3rem
            overflow-y:auto
            """),

            // header-content
            m.div(
                m.css("""
                display:flex
                justify-content:space-between
                """)
            )
        ),

        // body
        m.div(
            m.css("""
            color:text-primary
            display:flex
            flex-direction:row
            font-size:var(--carbon-body-01-font-size,0.875rem)
            font-weight:var(--carbon-body-01-font-weight,400)
            grid-column:1/-1
            grid-row:2/-2
            letter-spacing:var(--carbon-body-01-letter-spacing,0.16px)
            line-height:var(--carbon-body-01-line-height,1.42857)
            margin:0
            overflow-y:auto
            padding-block:.5rem_3rem
            padding-inline:1rem_1rem
            position:relative
            """)
        )
    );
  }

}

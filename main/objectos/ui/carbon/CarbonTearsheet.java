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

  private String description = "";

  private Html.Component main;

  private boolean open;

  private String title = "";

  @Override
  public final void description(String value) {
    description = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void main(Html.Component value) {
    main = Objects.requireNonNull(value, "value == null");
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
            max-block-size:50vh
            padding:1.5rem_2rem
            overflow-y:auto
            """),

            // header-content
            m.div(
                m.css("""
                display:flex
                justify-content:space-between
                """),

                // fields
                m.div(
                    // title
                    m.h3(
                        m.css("""
                        font-size:var(--carbon-heading-04-font-size,1.75rem)
                        font-weight:var(--carbon-heading-04-font-weight,400)
                        letter-spacing:var(--carbon-heading-04-letter-spacing,0)
                        line-height:var(--carbon-heading-04-line-height,1.28572)
                        """),

                        m.text(title)
                    ),

                    // description
                    m.div(
                        m.css("""
                        display:inline-flex
                        font-size:var(--carbon-body-compact-01-font-size,.875rem)
                        font-weight:var(--carbon-body-compact-01-font-weight,400)
                        margin-block-start:1rem
                        max-inline-size:100%
                        letter-spacing:var(--carbon-body-compact-01-letter-spacing,.16px)
                        line-height:var(--carbon-body-compact-01-line-height,1.28572)
                        """),

                        m.text(description)
                    )
                ),

                // actions
                m.noop()
            ),

            // header-navigation
            m.noop(),

            // modal-close-button
            m.noop()
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
            padding:0
            position:relative
            """),

            // right
            m.div(
                m.css("""
                display:grid
                flex-grow:1
                grid-template-columns:100%
                grid-template-rows:1fr_auto
                """),

                // main
                m.div(
                    m.css("""
                    background-color:background
                    display:flex
                    flex-direction:row
                    grid-column:1/-1
                    grid-row:1/-1
                    """),

                    // content
                    m.section(
                        m.css("""
                        flex-grow:1
                        overflow:auto
                        """),

                        main != null ? m.renderComponent(main) : m.noop()
                    )
                ),

                // button-container
                m.div()
            )
        )
    );
  }

}

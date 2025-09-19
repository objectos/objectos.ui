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

import java.util.Objects;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
final class CarbonTextInput extends CarbonComponent implements Carbon.TextInput {

  private boolean focus;

  private String helperText;

  private String id;

  private String invalidText;

  private String labelText = "Please specify a labelText value";

  private String placeholder;

  private String value = "";

  private String warnText;

  @Override
  public final void focus() {
    focus = true;
  }

  @Override
  public final void helperText(String value) {
    helperText = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == null").attrValue();
  }

  @Override
  public final void invalidText(String value) {
    invalidText = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void labelText(String value) {
    labelText = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void placeholder(String value) {
    placeholder = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void value(String value) {
    this.value = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    final String id;
    id = id(m, this.id);

    final MessageLevel level;
    level = msg(helperText, warnText, invalidText);

    // wrapper
    m.div(
        m.css("""
        align-items:flex-start
        display:flex
        flex-direction:column
        font-size:var(--carbon-body-compact-01-font-size,0.875rem)
        font-weight:var(--carbon-body-compact-01-font-weight,400)
        letter-spacing:var(--carbon-body-compact-01-letter-spacing,0.16px)
        line-height:var(--carbon-body-compact-01-line-height,1.28572)
        """),

        // label wrapper
        m.div(
            m.css("""
            display:flex
            inline-size:100%
            justify-content:space-between
            """),

            m.label(
                m.css("""
                color:text-secondary
                display:inline-block
                font-size:var(--carbon-label-01-font-size,0.75rem)
                font-weight:400
                letter-spacing:var(--carbon-label-01-letter-spacing,0.32px)
                line-height:1rem
                margin-block-end:.5rem
                vertical-align:baseline
                """),

                m.forId(id),

                m.text(labelText)
            )
        ),

        // outer wrapper
        m.div(
            m.css("""
            align-items:flex-start
            display:flex
            flex:1_1_auto
            flex-direction:column
            inline-size:100%
            """),

            // field wrapper
            m.div(
                m.css("""
                display:flex
                inline-size:100%
                position:relative
                """),

                level == MessageLevel.ERROR
                    ? m.c(Carbon.icon(icon -> {
                      icon.iconWarningFilled();
                      icon.size16();
                      icon.css("""
                      fill:support-error
                      inset-block-start:50%
                      inset-inline-end:1rem
                      position:absolute
                      transform:translateY(-50%)
                      """);
                    }))
                    : m.noop(),

                m.input(
                    m.id(id),

                    m.css("""
                    background-color:field
                    border-block-end:1px_solid_border-strong
                    color:text-primary
                    font-size:var(--carbon-body-compact-01-font-size,0.875rem)
                    font-weight:var(--carbon-body-compact-01-font-weight,400)
                    inline-size:100%
                    letter-spacing:var(--carbon-body-compact-01-letter-spacing,0.16px)
                    line-height:var(--carbon-body-compact-01-line-height,1.28572)
                    margin:0
                    outline:2px_solid_rgba(0,0,0,0)
                    outline-offset:-2px
                    vertical-align:baseline
                    transition:background-color_70ms_cubic-bezier(0.2,0,0.38,0.9),outline_70ms_cubic-bezier(0.2,0,0.38,0.9)

                    active:outline-color:focus
                    focus:outline-color:focus
                    """),

                    // md
                    m.css("""
                    block-size:40rx
                    padding:0_16rx
                    """),

                    level == MessageLevel.ERROR ? m.css("""
                    box-shadow:none
                    outline:2px_solid_support-error
                    outline-offset:-2px
                    """) : m.noop(),

                    focus ? m.dataOnLoad(script -> {
                      var el = script.element();
                      el.focus();
                    }) : m.noop(),

                    placeholder != null ? m.placeholder(placeholder) : m.noop(),

                    m.type("text"),

                    m.value(value)
                )
            ),

            // message
            level == MessageLevel.NONE
                ? m.noop()
                : m.div(
                    m.css(
                        switch (level) {
                          default -> "color:text-helper";
                          case WARN -> "color:text-warn";
                          case ERROR -> "color:text-error";
                        }
                    ),

                    m.css("""
                    font-size:var(--carbon-helper-text-01-font-size,0.75rem)
                    inline-size:100%
                    letter-spacing:var(--carbon-helper-text-01-letter-spacing,0.32px)
                    line-height:var(--carbon-helper-text-01-line-height,1.33333)
                    margin-block-start:.25rem
                    opacity:1
                    z-index:0
                    """),

                    m.text(
                        switch (level) {
                          default -> helperText;
                          case WARN -> warnText;
                          case ERROR -> invalidText;
                        }
                    )
                )
        )
    );
  }

}

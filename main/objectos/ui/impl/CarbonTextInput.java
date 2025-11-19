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

import java.util.Objects;
import objectos.ui.Carbon;
import objectos.way.Html;

public final class CarbonTextInput extends CarbonComponent implements Carbon.TextInput {

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
        font-size:var(--type-body-compact-01-font-size)
        font-weight:var(--type-body-compact-01-font-weight)
        letter-spacing:var(--type-body-compact-01-letter-spacing)
        line-height:var(--type-body-compact-01-line-height)
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
                color:var(--color-text-secondary)
                display:inline-block
                font-size:var(--type-label-01-font-size)
                font-weight:400
                letter-spacing:var(--type-label-01-letter-spacing)
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
                      fill:var(--color-support-error)
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
                    background-color:var(--color-field)
                    border-block-end:1px_solid_var(--color-border-strong)
                    color:var(--color-text-primary)
                    font-size:var(--type-body-compact-01-font-size)
                    font-weight:var(--type-body-compact-01-font-weight)
                    inline-size:100%
                    letter-spacing:var(--type-body-compact-01-letter-spacing)
                    line-height:var(--type-body-compact-01-line-height)
                    margin:0
                    outline:2px_solid_rgba(0,0,0,0)
                    outline-offset:-2px
                    vertical-align:baseline
                    transition:background-color_70ms_cubic-bezier(0.2,0,0.38,0.9),outline_70ms_cubic-bezier(0.2,0,0.38,0.9)

                    active/outline-color:var(--color-focus)
                    focus/outline-color:var(--color-focus)
                    """),

                    // md
                    m.css("""
                    block-size:40rx
                    padding:0_16rx
                    """),

                    level == MessageLevel.ERROR ? m.css("""
                    box-shadow:none
                    outline:2px_solid_var(--color-support-error)
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
                          default -> "color:var(--color-text-helper)";
                          case WARN -> "color:var(--color-text-warn)";
                          case ERROR -> "color:var(--color-text-error)";
                        }
                    ),

                    m.css("""
                    font-size:var(--type-helper-text-01-font-size)
                    inline-size:100%
                    letter-spacing:var(--type-helper-text-01-letter-spacing)
                    line-height:var(--type-helper-text-01-line-height)
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

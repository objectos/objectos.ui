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

import module java.base;
import module objectos.ui;

public final class UiContainedList extends UiComponent implements ContainedList, ContainedList.Options {

  private final UiContainedListKind kind = UiContainedListKind.ON_PAGE;

  private String label;

  private List<Html.Component> main = EMPTY_MAIN;

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void label(String value) {
    label = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.div(
        m.css("""
        --layout-size-height-local:clamp(max(var(--layout-size-height-min),var(--layout-size-height-sm)),var(--layout-size-height,var(--layout-size-height-lg)),min(var(--layout-size-height-max),var(--layout-size-height-xl)))
        --layout-density-padding-inline-local:clamp(var(--layout-density-padding-inline-min),var(--layout-density-padding-inline,var(--layout-density-padding-inline-normal)),var(--layout-density-padding-inline-max))
        """),

        label != null
            ? m.div(
                m.css("""
                align-items:center
                display:flex
                inset-block-start:0
                padding-inline:var(--layout-density-padding-inline-local)
                position:sticky
                z-index:1
                """),

                switch (kind) {
                  case ON_PAGE -> m.css("""
                  background-color:var(--color-background)
                  block-size:var(--layout-size-height-local)
                  border-block-end:1px_solid_var(--color-border-subtle)
                  color:var(--color-text-primary)
                  font-size:var(--type-heading-compact-01-font-size)
                  font-weight:var(--type-heading-compact-01-font-weight)
                  letter-spacing:var(--type-heading-compact-01-letter-spacing)
                  line-height:var(--type-heading-compact-01-line-height)
                  """);

                  case DISCLOSED -> m.css("""

                  """);
                },

                m.div(
                    m.css("""
                    inline-size:100%
                    """),

                    m.text(label)
                )
            )
            : m.noop(),

        m.ul(
            m.role("list"),

            m.c(main)
        )
    );
  }

}

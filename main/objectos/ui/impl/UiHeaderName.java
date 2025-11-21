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
import module objectos.ui;

public final class UiHeaderName extends UiComponent implements Header.Name {

  private String href;

  private Html.Component main;

  private String prefix;

  @Override
  public final void href(String value) {
    href = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void main(String value) {
    final String t;
    t = Objects.requireNonNull(value, "value == null");

    main = m -> m.text(t);
  }

  @Override
  public final void prefix(String value) {
    prefix = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.a(
        m.css("""
        align-items:center
        display:flex
        border:.125rem_solid_rgba(0,0,0,0)
        block-size:100%
        font-size:var(--type-body-compact-01-font-size)
        font-weight:600
        letter-spacing:.1px
        line-height:1.25rem
        outline:none
        padding:0_2rem_0_1rem
        text-decoration:none
        transition:border-color_110ms
        user-select:none
        """),

        href != null ? m.href(href) : m.noop(),

        prefix != null
            ? m.flatten(
                m.span(
                    m.css("""
                    font-weight:400
                    """),

                    m.text(prefix)
                ),

                m.nbsp()
            )
            : m.noop(),

        main != null ? m.c(main) : m.noop()
    );
  }

}

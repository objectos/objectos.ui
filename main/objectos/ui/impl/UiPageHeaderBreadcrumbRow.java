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

public final class UiPageHeaderBreadcrumbRow extends UiPageHeader.Row
    implements PageHeaderBreadcrumbRow, PageHeaderBreadcrumbRow.Options {

  private boolean border = true;

  private List<Html.Component> main = EMPTY_MAIN;

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void border(boolean value) {
    border = value;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.div(
        m.css("""
        block-size:var(--spacing-08)
        """),

        border ? m.css("""
        border-block-end:1px_solid_var(--color-border-subtle-01)
        """) : m.noop(),

        // grid
        m.div(
            m.css(gridGutter.css),
            m.css(UiGrid.CSS),
            !fullWidth ? m.css(UiGrid.CSS_REGULAR_WIDTH) : m.css(UiGrid.CSS_FULL_WIDTH),

            m.css("block-size:100%"),

            // column
            m.div(
                m.css(UiColumn.CSS),
                m.css(UiColumnProps.cssOfSpan(UiBreakpoint.XS, 4)),
                m.css(UiColumnProps.cssOfSpan(UiBreakpoint.MD, 8)),
                m.css(UiColumnProps.cssOfSpan(UiBreakpoint.LG, 16)),

                m.css("block-size:100%"),

                // container
                m.div(
                    m.css("""
                    align-items:center
                    block-size:100%
                    display:flex
                    inline-size:100%
                    justify-content:space-between
                    """),

                    m.c(main)
                )
            )
        )
    );
  }

}

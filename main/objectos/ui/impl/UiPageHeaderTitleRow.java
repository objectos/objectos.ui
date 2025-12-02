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

public final class UiPageHeaderTitleRow extends UiPageHeader.Row
    implements PageHeaderTitleRow, PageHeaderTitleRow.Options {

  private String title = "Please set a page title";

  @Override
  public final void add(Html.Component value) {

  }

  @Override
  public final void title(String value) {
    title = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.div(
        m.css(gridGutter.css),
        m.css(UiGrid.CSS),
        !fullWidth ? m.css(UiGrid.CSS_REGULAR_WIDTH) : m.css(UiGrid.CSS_FULL_WIDTH),

        m.css("""
        padding:var(--spacing-06)_0
        """),

        m.div(
            m.css(UiColumn.CSS),
            m.css(UiColumnProps.cssOfSpan(UiBreakpoint.XS, 4)),
            m.css(UiColumnProps.cssOfSpan(UiBreakpoint.MD, 6)),
            m.css(UiColumnProps.cssOfSpan(UiBreakpoint.LG, 8)),

            m.h1(
                m.css("""
                display:-webkit-box
                font-size:var(--type-productive-heading-04-font-size)
                font-weight:var(--type-productive-heading-04-font-weight)
                letter-spacing:var(--type-productive-heading-04-letter-spacing)
                line-clamp:2
                line-height:var(--type-productive-heading-04-line-height)
                overflow:hidden
                text-overflow:ellipsis
                white-space:normal
                -webkit-box-orient:vertical
                -webkit-line-clamp:2
                """),

                m.text(title)
            )
        )
    );
  }

}

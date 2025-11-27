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

public final class UiContainedListItem extends UiComponent
    implements ContainedListItem, ContainedListItem.Options {

  private Html.Component main;

  @Override
  public final void set(Html.Component value) {
    main = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.li(
        m.css("""
        display:list-item
        list-style:none
        position:relative

        &:not(:last-of-type)::before/background-color:var(--color-border-subtle)
        &:not(:last-of-type)::before/block-size:1px
        &:not(:last-of-type)::before/content:''
        &:not(:last-of-type)::before/inset-block-end:0
        &:not(:last-of-type)::before/inset-inline:0
        &:not(:last-of-type)::before/position:absolute
        """),

        m.div(
            m.css("""
            --temp-1lh:calc(var(--type-body-01-line-height)*1em)
            color:var(--color-text-primary)
            font-size:var(--type-body-01-font-size)
            font-weight:var(--type-body-01-font-weight)
            letter-spacing:var(--type-body-01-letter-spacing)
            line-height:var(--type-body-01-line-height)
            min-block-size:var(--layout-size-height-local)
            padding:calc((var(--layout-size-height-local)_-_var(--temp-1lh))/2)_var(--layout-density-padding-inline-local)
            """),

            main != null ? m.c(main) : m.noop()
        )
    );
  }

}

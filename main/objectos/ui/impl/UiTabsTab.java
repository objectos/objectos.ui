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
import java.util.function.Consumer;
import module objectos.ui;
import objectos.way.Script;

public final class UiTabsTab extends UiComponent implements Tabs.Tab {

  private Consumer<? super Script> dataOnClick;

  private String href;

  boolean selected;

  private String text = "Tab";

  @Override
  public final void dataOnClick(Consumer<? super Script> value) {
    dataOnClick = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void href(String value) {
    href = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void text(String value) {
    text = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        href != null ? Html.ElementName.A : Html.ElementName.BUTTON,

        m.css("""
        align-items:center
        border-block-end-style:solid
        border-block-end-width:2rx
        cursor:pointer
        display:inline-flex
        outline-color:rgba(0,0,0,0)
        outline-offset:-2rx
        outline-style:solid
        outline-width:2rx
        overflow:hidden
        padding-inline:var(--spacing-05)
        text-align:start
        text-decoration:none
        text-overflow:ellipsis
        transition:border_var(--motion-fast-01),outline_var(--motion-fast-01)
        white-space:nowrap

        &:not(:first-of-type)/margin-inline-start:1rx
        active/outline-color:var(--color-focus)
        focus/outline-color:var(--color-focus)
        """),

        selected
            ? m.css("""
            border-block-end-color:var(--color-border-interactive)
            color:var(--color-text-primary)
            font-size:var(--type-heading-compact-01-font-size)
            font-weight:var(--type-heading-compact-01-font-weight)
            line-height:var(--type-heading-compact-01-line-height)
            letter-spacing:var(--type-heading-compact-01-letter-spacing)
            """)
            : m.css("""
            border-block-end-color:var(--color-border-subtle)
            color:var(--color-text-secondary)
            font-size:var(--type-body-compact-01-font-size)
            font-weight:var(--type-body-compact-01-font-weight)
            line-height:var(--type-body-compact-01-line-height)
            letter-spacing:var(--type-body-compact-01-letter-spacing)

            hover/border-block-end-color:var(--color-border-strong)
            hover/color:var(--color-text-primary)
            """),

        dataOnClick != null ? m.dataOnClick(dataOnClick) : m.noop(),

        href != null ? m.href(href) : m.noop(),

        m.role("tab"),

        m.tabindex(selected ? "0" : "-1"),

        m.text(text)
    );
  }

}

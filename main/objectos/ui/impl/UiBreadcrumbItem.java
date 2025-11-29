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

public final class UiBreadcrumbItem extends UiComponent implements BreadcrumbItem, BreadcrumbItem.Options {

  private String href;

  private Html.Instruction id = Html.Instruction.noop();

  private Html.Component main;

  private UiBreadcrumbSize size;

  @Override
  public final void href(String value) {
    href = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void set(Html.Component value) {
    main = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void set(String value) {
    main = m -> m.text(value);
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.li(
        id,

        m.css("""
        align-items:center
        display:flex
        position:relative

        after/color:var(--color-text-primary)
        after/content:'/'
        """),

        switch (size) {
          case MD -> m.css("""
          margin-inline-end:var(--spacing-03)

          after/margin-inline-start:var(--spacing-03)
          """);
        },

        m.a(
            m.css("""
            color:var(--color-link-text-color,var(--color-link-primary))
            display:inline-flex
            outline:none
            text-decoration:none
            transition:color_70ms_cubic-bezier(0.2,0,0.38,0.9)

            active/outline:1px_solid_var(--color-focus)
            active/outline-color:var(--color-link-focus-text-color,var(--color-focus))
            active/text-decoration:underline
            focus/outline:1px_solid_var(--color-focus)
            focus/outline-color:var(--color-link-focus-text-color,var(--color-focus))
            focus/text-decoration:underline
            hover/color:var(--color-link-hover-text-color,var(--color-link-primary-hover))
            hover/text-decoration:underline
            """),

            href != null ? m.href(href) : m.noop(),

            main != null ? m.c(main) : m.noop()
        )
    );
  }

  final void size(UiBreadcrumbSize value) {
    size = value;
  }

}

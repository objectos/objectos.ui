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

public final class UiBreadcrumb extends UiComponent implements Breadcrumb, Breadcrumb.Options {

  private String ariaLabel;

  private List<Html.Component> main = EMPTY_MAIN;

  private UiBreadcrumbSize size = UiBreadcrumbSize.MD;

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void ariaLabel(String value) {
    ariaLabel = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void size(BreadcrumbSize value) {
    final BreadcrumbSize s;
    s = Objects.requireNonNull(value, "value");

    size = (UiBreadcrumbSize) s;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.nav(
        ariaLabel != null ? m.ariaLabel(ariaLabel) : m.noop(),

        m.ol(
            m.css("""
            display:inline

            md/display:flex
            md/flex-wrap:wrap
            """),

            switch (size) {
              case SM -> m.css("""
              font-size:var(--type-label-01-font-size)
              font-weight:var(--type-label-01-font-weight)
              letter-spacing:var(--type-label-01-letter-spacing)
              line-height:var(--type-label-01-line-height)
              """);

              case MD -> m.css("""
              font-size:var(--type-body-compact-01-font-size)
              font-weight:var(--type-body-compact-01-font-weight)
              letter-spacing:var(--type-body-compact-01-letter-spacing)
              line-height:var(--type-body-compact-01-line-height)
              """);
            },

            m.f(this::items, m)
        )
    );
  }

  private void items(Html.Markup m) {
    for (Html.Component c : main) {
      if (c instanceof UiBreadcrumbItem item) {
        item.size(size);
      }

      m.c(c);
    }
  }

}

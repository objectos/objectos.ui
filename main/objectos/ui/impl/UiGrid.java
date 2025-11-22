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
import java.util.ArrayList;
import module objectos.ui;

public final class UiGrid extends UiComponent implements Grid, Grid.Options {

  private final Html.ElementName as = Html.ElementName.DIV;

  private String css;

  private final List<Html.Component> main = new ArrayList<>();

  @Override
  public final void column(Consumer<? super Column.Options> col) {
    main.add(
        Column.create(col)
    );
  }

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        m.css("""
        --grid-gutter-start:calc(var(--grid-gutter)_/_2)
        --grid-gutter-end:calc(var(--grid-gutter)_/_2)
        --grid-column-hang:calc(var(--grid-gutter)_/_2)
        display:grid
        grid-template-columns:repeat(var(--grid-columns),minmax(0,1fr))
        inline-size:100%
        margin-inline:auto
        max-inline-size:96rem
        padding-inline:var(--grid-margin)
        """),

        css != null ? m.css(css) : m.noop(),

        m.c(main)
    );
  }

}

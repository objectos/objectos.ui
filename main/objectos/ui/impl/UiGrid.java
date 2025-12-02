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
import objectos.ui.GridGutter;

public final class UiGrid extends UiComponent implements Grid, Grid.Options {

  static final String CSS = """
  --grid-gutter-end:calc(var(--grid-gutter)_/_2)
  display:grid
  grid-template-columns:repeat(var(--grid-columns),minmax(0,1fr))
  inline-size:100%
  margin-inline:auto
  padding-inline:var(--grid-margin)
  """;

  static final String CSS_FULL_WIDTH = "max-inline-size:100%";

  static final String CSS_REGULAR_WIDTH = "max-inline-size:var(--breakpoint-x2)";

  private final Html.ElementName as = Html.ElementName.DIV;

  private String css;

  private boolean fullWidth;

  private UiGridGutter gutter = UiGridGutter.WIDE;

  private List<Html.Component> main = EMPTY_MAIN;

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void fullWidth() {
    fullWidth = true;
  }

  @Override
  public final void gutter(GridGutter value) {
    final GridGutter v;
    v = Objects.requireNonNull(value, "value == null");

    gutter = (UiGridGutter) v;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        m.css(gutter.css),

        m.css(CSS),

        !fullWidth ? m.css(CSS_REGULAR_WIDTH) : m.css(CSS_FULL_WIDTH),

        css != null ? m.css(css) : m.noop(),

        m.c(main)
    );
  }

}

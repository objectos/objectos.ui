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
import objectos.ui.Spacing;
import objectos.ui.Vertical;
import objectos.way.Html.Component;

public final class UiVertical extends UiComponent implements Vertical, Vertical.Options {

  private static final String CSS = """
  display:grid
  grid-auto-flow:row
  """;

  private static final String[] GAPS = {
      "row-gap:0",
      "row-gap:2rx",
      "row-gap:4rx",
      "row-gap:8rx",
      "row-gap:12rx",
      "row-gap:16rx",
      "row-gap:24rx",
      "row-gap:32rx",
      "row-gap:40rx",
      "row-gap:48rx",
      "row-gap:64rx",
      "row-gap:80rx",
      "row-gap:96rx",
      "row-gap:160rx"
  };

  private Html.ElementName as = Html.ElementName.DIV;

  private UiSpacing gap = UiSpacing.SPACING_00;

  private List<Html.Component> main = EMPTY_MAIN;

  public UiVertical() {}

  public UiVertical(Spacing gap, List<Component> main) {
    this.gap = (UiSpacing) gap;

    this.main = main;
  }

  public static String cssOf0(Spacing gap, String... more) {
    final StringBuilder sb;
    sb = new StringBuilder(CSS);

    final UiSpacing impl;
    impl = (UiSpacing) gap;

    final int gapIdx;
    gapIdx = impl.ordinal();

    final String cn;
    cn = GAPS[gapIdx];

    sb.append(cn);

    for (String v : more) {
      sb.append(' ');

      sb.append(
          Objects.requireNonNull(v)
      );
    }

    return sb.toString();
  }

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void as(Html.ElementName value) {
    as = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void gap(Spacing value) {
    gap = (UiSpacing) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    final int gapIdx;
    gapIdx = gap.ordinal();

    final String cn;
    cn = GAPS[gapIdx];

    m.elem(
        as,

        m.css(CSS),

        m.className(cn),

        m.c(main)
    );
  }

}

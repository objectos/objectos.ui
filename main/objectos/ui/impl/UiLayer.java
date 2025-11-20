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
import module objectos.way;
import module objectos.ui;

public final class UiLayer extends UiComponent implements Layer, Layer.Options {

  static final Html.ClassName LAYER_0 = Html.ClassName.of("carbon-layer-0");
  static final Html.ClassName LAYER_1 = Html.ClassName.of("carbon-layer-1");
  static final Html.ClassName LAYER_2 = Html.ClassName.of("carbon-layer-2");

  private final Html.ElementName as = Html.ElementName.DIV;

  private List<Html.Component> components = List.of();

  private int level = -1;

  @Override
  public final void add(Html.Component value) {
    final Html.Component c;
    c = Objects.requireNonNull(value, "value == null");

    if (components.isEmpty()) {
      components = new ArrayList<>();
    }

    components.add(c);
  }

  public final void level(int value) {
    if (value < 0 || value > 2) {
      throw new IllegalArgumentException("valid levels are: 0, 1 and 2");
    }

    level = value;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    final int layer;

    if (level == -1) {
      if (m instanceof UiMarkup c) {
        layer = c.nextLayer();
      } else {
        layer = 1;
      }
    } else {
      layer = level;
    }

    final Html.ClassName cn = switch (layer) {
      case 0 -> LAYER_0;
      case 1 -> LAYER_1;
      default -> LAYER_2;
    };

    m.elem(as, cn, m.c(components));
  }

}

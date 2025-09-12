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
package objectos.ui.carbon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import objectos.ui.Carbon;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
public final class CarbonLayer extends CarbonComponent implements Carbon.Layer {

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
      if (m instanceof CarbonMarkup c) {
        layer = c.nextLayer();
      } else {
        layer = 1;
      }
    } else {
      layer = level;
    }

    final String cn = switch (layer) {
      case 0 -> "carbon-layer-0";
      case 1 -> "carbon-layer-1";
      default -> "carbon-layer-2";
    };

    m.elem(as, m.className(cn), m.c(components));
  }

}

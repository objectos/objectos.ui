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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import objectos.ui.Carbon;
import objectos.way.Html;

public final class CarbonFormGroup extends CarbonComponent implements Carbon.FormGroup {

  private List<Html.Component> components = List.of();

  private String css;

  private String legendText;

  @Override
  public final void add(Html.Component value) {
    final Html.Component c;
    c = Objects.requireNonNull(value, "value == null");

    if (components.isEmpty()) {
      components = new ArrayList<>();
    }

    components.add(c);
  }

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void legendText(String value) {
    legendText = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.fieldset(
        css != null ? m.css(css) : m.noop(),

        legendText != null
            ? m.legend(
                m.css("""
                color:var(--color-text-secondary)
                display:inline-block
                font-size:var(--type-label-01-font-size)
                font-weight:400
                letter-spacing:var(--type-label-01-letter-spacing)
                line-height:1rem
                margin-block-end:.5rem
                vertical-align:baseline
                """),

                m.text(legendText)
            )
            : m.noop(),

        m.c(components)
    );
  }

}

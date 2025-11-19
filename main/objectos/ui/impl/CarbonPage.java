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

import java.util.List;
import java.util.Objects;
import module objectos.ui;

public final class CarbonPage extends CarbonComponent implements Page, Page.Options {

  private String css;

  private Html.Component head;

  private List<Html.Component> main = List.of();

  private CarbonTheme theme = CarbonTheme.WHITE;

  private String title = "";

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void head(Html.Component value) {
    head = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void main(Html.Component... elements) {
    main = List.of(elements);
  }

  @Override
  public final void theme(Theme value) {
    theme = (CarbonTheme) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void title(String value) {
    title = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.doctype();

    m.html(
        m.head(
            m.meta(m.charset("utf-8")),
            m.meta(m.httpEquiv("content-type"), m.content("text/html; charset=utf-8")),
            m.meta(m.name("viewport"), m.content("width=device-width, initial-scale=1")),
            m.title(title),
            head != null ? m.c(head) : m.noop()
        ),

        m.body(
            m.className(theme.className),

            css != null ? m.css(css) : m.noop(),

            m.dataFrame("theme", theme.className),

            m.c(main)
        )
    );
  }

}

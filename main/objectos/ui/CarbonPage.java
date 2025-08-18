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
package objectos.ui;

import java.util.Objects;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
final class CarbonPage implements Carbon.Page, Html.Component {

  private Html.Component head;

  private CarbonTheme theme;

  private String title = "";

  @Override
  public final void headEnd(Html.Component value) {
    final Html.Component headEnd;
    headEnd = Objects.requireNonNull(value, "value == null");

    head = m -> {
      headDefault(m);
      headEnd.renderHtml(m);
    };
  }

  @Override
  public final void theme(Carbon.Theme value) {
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
        theme != null ? m.className(theme.className) : m.noop(),

        m.head(
            m.renderComponent(head != null ? head : this::headDefault)
        ),

        m.body(
        )
    );
  }

  private void headDefault(Html.Markup m) {
    m.meta(m.charset("utf-8"));
    m.meta(m.httpEquiv("content-type"), m.content("text/html; charset=utf-8"));
    m.meta(m.name("viewport"), m.content("width=device-width, initial-scale=1"));
    m.title(title);
  }

}

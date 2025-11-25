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

public final class UiPage extends UiComponent implements Page, Page.Options {

  private String css;

  private String dataFrame;

  private String dataFrameValue;

  private Html.Component head;

  private List<Html.Component> main = EMPTY_MAIN;

  private UiTheme theme = UiTheme.WHITE;

  private String title = "";

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void dataFrame(String name, String value) {
    dataFrame = Objects.requireNonNull(name, "name == null");
    dataFrameValue = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void head(Html.Component value) {
    head = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void theme(Theme value) {
    theme = (UiTheme) Objects.requireNonNull(value, "value == null");
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
            m.css(theme.css()),

            css != null ? m.css(css) : m.noop(),

            dataFrame != null ? m.dataFrame(dataFrame, dataFrameValue) : m.noop(),

            m.c(main)
        )
    );
  }

}

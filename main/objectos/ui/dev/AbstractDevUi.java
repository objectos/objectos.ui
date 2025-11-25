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
package objectos.ui.dev;

import module java.base;
import module objectos.ui;
import objectos.ui.impl.UiTheme;

abstract class AbstractDevUi implements Http.Routing.Module {

  private final String name;

  private final String suffix;

  AbstractDevUi(String name) {
    this(name, "/{id}/{theme}");
  }

  AbstractDevUi(String name, String suffix) {
    this.name = name;
    this.suffix = suffix;
  }

  @Override
  public void configure(Http.Routing routing) {
    routing.path("/" + name + suffix, Http.Method.GET, this::handle);
  }

  abstract void handle(Http.Exchange http);

  final void ok(Http.Exchange http, String title, Html.Component... elements) {
    http.ok(Page.create(page -> {
      page.theme(theme(http));

      page.title(title);

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.css("""
      display:flex
      flex-direction:column
      gap:16rx
      max-width:640rx
      padding:42rx
      """);

      page.add(m -> m.c(elements));

      page.add(themeSwitcher(http));
    }));
  }

  final Html.Component page(Http.Exchange http, Consumer<? super Page.Options> more) {
    return Page.create(page -> {
      page.theme(theme(http));

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  final Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Theme.of(themeName);
  }

  private Html.Component themeSwitcher(Http.Exchange http) {
    final Theme theme;
    theme = theme(http);

    final UiTheme impl;
    impl = (UiTheme) theme;

    final UiTheme[] values;
    values = UiTheme.values();

    final int ordinal;
    ordinal = impl.ordinal();

    final UiTheme prev;
    prev = ordinal == 0 ? null : values[ordinal - 1];

    final UiTheme next;
    next = ordinal == values.length - 1 ? null : values[ordinal + 1];

    return m -> m.div(
        m.css("""
        display:grid
        grid-template-columns:1fr_1fr
        """),

        prev != null ? m.a(
            m.dataOnClick(Script::navigate),
            m.href(prev.name().toLowerCase()),
            m.text("prev")
        ) : m.span(),

        next != null ? m.a(
            m.dataOnClick(Script::navigate),
            m.href(next.name().toLowerCase()),
            m.text("next")
        ) : m.span()
    );
  }

}

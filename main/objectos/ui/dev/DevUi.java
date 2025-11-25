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

import static objectos.way.Http.Method.GET;

import module java.base;
import module objectos.ui;
import objectos.ui.impl.UiTheme;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
@Css.Source
public final class DevUi implements Http.Routing.Module {

  @Override
  public final void configure(Http.Routing routing) {
    routing.path("/formgroup/{id}/{theme}", GET, this::formGroup);
    routing.path("/layer/{id}/{theme}", GET, this::layer);
    routing.path("/tearsheet/{id}/{theme}", GET, this::tearsheet);
    routing.path("/textinput/{id}/{theme}", GET, this::textInput);
  }

  private void formGroup(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Form Group - Default",

          FormGroup.create(g -> {
            g.css("""
            display:flex
            flex-direction:column
            gap:32rx
            """);

            g.legendText("FormGroup legend");

            g.textInput(t -> {
              t.labelText("First name");
            });

            g.textInput(t -> {
              t.labelText("Last name");
            });
          })
      );
    }
  }

  private void layer(Http.Exchange http) {
    final Html.Component test;
    test = m -> m.div(
        m.css("""
        background-color:var(--color-layer)
        color:var(--color-text-primary)
        padding:16rx
        """),

        m.text("Test component")
    );

    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Layer - Default");

        page.add(m -> m.div(
            m.css("padding:32rx sm/max-width:640rx"),

            m.c(test),

            m.c(Layer.create(l1 -> {
              l1.add(test);

              l1.add(Layer.create(l2 -> {
                l2.add(test);
              }));
            })),

            m.c(themeSwitcher(http))
        ));
      }));
    }
  }

  private Html.Component page(Http.Exchange http, Consumer<? super Page.Options> more) {
    return Page.create(page -> {
      page.theme(theme(http));

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  static final Html.Id TEARSHEET = Html.Id.of("ts");

  private void tearsheet(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Tearsheet - Default");

        page.add(
            Button.create(b -> {
              b.dataOnClick(Tearsheet.openScript(TEARSHEET));
              b.text("Open Tearsheet");
            })
        );

        page.add(
            Tearsheet.create(t -> {
              t.id(TEARSHEET);

              t.title("Title of the tearsheet");

              t.description("""
              This is a description for the tearsheet, \
              providing an opportunity to describe the flow over \
              a couple of lines in the header of the tearsheet.\
              """);

              t.main(m -> m.div(
                  m.css("""
                  padding:24rx_32rx
                  """),

                  m.h3("Main content heading"),

                  m.c(FormGroup.create(g -> {
                    g.css("""
                    display:flex
                    flex-direction:column
                    gap:16rx
                    """);

                    g.legendText("FormGroup legend");

                    g.textInput(input -> {
                      input.labelText("Enter an important value here");
                    });

                    g.textInput(input -> {
                      input.labelText("Here is an entry field:");
                    });
                  })),

                  m.c(themeSwitcher(http))
              ));

              t.actions(
                  a -> {
                    a.dataOnClick(Tearsheet.closeScript(TEARSHEET));
                    a.kind(ButtonKind.GHOST);
                    a.text("Cancel");
                  },

                  a -> {
                    a.kind(ButtonKind.SECONDARY);
                    a.text("Back");
                  },

                  a -> {
                    a.kind(ButtonKind.PRIMARY);
                    a.text("Replace");
                  }
              );
            })
        );
      }));
    }
  }

  public static final Html.Id TEXT_INPUT = Html.Id.of("text-input");

  private void textInput(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "TextInput - Default",

          TextInput.create(t -> {
            t.helperText("Helper text");
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );

      case "default-focus" -> ok(
          http,

          "TextInput - Default (Focus)",

          TextInput.create(t -> {
            t.focus();
            t.helperText("Helper text");
            t.id(TEXT_INPUT);
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );

      case "invalid" -> ok(
          http,

          "TextInput - Invalid",

          TextInput.create(t -> {
            t.invalidText("Error message goes here");

            t.labelText("Label text");

            t.placeholder("Placeholder text");
          })
      );

      case "invalid-focus" -> ok(
          http,

          "TextInput - Invalid (Focus)",

          TextInput.create(t -> {
            t.focus();
            t.id(TEXT_INPUT);
            t.invalidText("Error message goes here");
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );
    }
  }

  static void ok(Http.Exchange http, String title, Html.Component... elements) {
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

  private static Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Theme.of(themeName);
  }

  private static Html.Component themeSwitcher(Http.Exchange http) {
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

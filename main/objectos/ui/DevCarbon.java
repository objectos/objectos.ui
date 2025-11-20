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

import static objectos.way.Http.Method.GET;

import module java.base;
import module objectos.way;
import objectos.ui.impl.CarbonSpacing;
import objectos.ui.impl.CarbonTheme;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
@Css.Source
public final class DevCarbon implements Http.RoutingPath.Module {

  private final App.Injector injector;

  public DevCarbon(App.Injector injector) {
    this.injector = injector;
  }

  @Override
  public final void configure(Http.RoutingPath carbon) {
    carbon.subpath("/button/{id}/{theme}", GET, this::button);
    carbon.subpath("/formgroup/{id}/{theme}", GET, this::formGroup);
    carbon.subpath("/layer/{id}/{theme}", GET, this::layer);
    carbon.subpath("/page/{theme}", GET, this::page);
    carbon.subpath("/stack/{id}/{theme}", GET, this::stack);
    carbon.subpath("/tearsheet/{id}/{theme}", GET, this::tearsheet);
    carbon.subpath("/textinput/{id}/{theme}", GET, this::textInput);

    carbon.subpath("/styles.css", GET, this::styles);
  }

  static final Html.Id BTN = Html.Id.of("btn");
  static final Html.Id BTN_SM = Html.Id.of("btn-sm");
  static final Html.Id BTN_MD = Html.Id.of("btn-md");
  static final Html.Id BTN_XL = Html.Id.of("btn-xl");
  static final Html.Id BTN_X2L = Html.Id.of("btn-x2l");

  private void button(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Button - Default",

          button(ButtonKind.PRIMARY)
      );

      case "secondary" -> ok(
          http,

          "Button - Secondary",

          button(ButtonKind.SECONDARY)
      );

      case "ghost" -> ok(
          http,

          "Button - Ghost",

          button(ButtonKind.GHOST)
      );
    }
  }

  private Html.Component button(ButtonKind kind) {
    return Carbon.stack(
        Spacing.SPACING_04,

        Button.create(b -> {
          b.id(BTN);
          b.kind(kind);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_SM);
          b.kind(kind);
          b.size(ButtonSize.SM);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_MD);
          b.kind(kind);
          b.size(ButtonSize.MD);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_XL);
          b.kind(kind);
          b.size(ButtonSize.XL);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_X2L);
          b.kind(kind);
          b.size(ButtonSize.X2);
          b.text("Button");
        })
    );
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

        page.main(m -> m.div(
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

  private void page(Http.Exchange http) {
    http.ok(Page.create(page -> {
      page.theme(theme(http));

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.title("Objectos Carbon");
    }));
  }

  private Html.Component page(Http.Exchange http, Consumer<? super Page.Options> more) {
    return Page.create(page -> {
      page.theme(theme(http));

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  private void stack(Http.Exchange http) {
    final int raw;
    raw = http.queryParamAsInt("gap", 0);

    final CarbonSpacing gap;
    gap = 0 <= raw && raw <= 13 ? CarbonSpacing.values()[raw] : CarbonSpacing.SPACING_00;

    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Stack - Default",

          Carbon.stack(
              gap,

              m -> m.div("Item 1"),
              m -> m.div("Item 2"),
              m -> m.div("Item 3")
          )
      );
    }
  }

  static final Html.Id TEARSHEET = Html.Id.of("ts");

  private void tearsheet(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Tearsheet - Default");

        page.main(
            Button.create(b -> {
              b.dataOnClick(Carbon.Tearsheet.openAction(TEARSHEET));
              b.text("Open Tearsheet");
            }),

            Carbon.tearsheet(t -> {
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
                    a.dataOnClick(Carbon.Tearsheet.close(TEARSHEET));
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

  static final Html.Id TEXT_INPUT = Html.Id.of("text-input");

  private void textInput(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "TextInput - Default",

          Carbon.textInput(t -> {
            t.helperText("Helper text");
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );

      case "default-focus" -> ok(
          http,

          "TextInput - Default (Focus)",

          Carbon.textInput(t -> {
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

          Carbon.textInput(t -> {
            t.invalidText("Error message goes here");

            t.labelText("Label text");

            t.placeholder("Placeholder text");
          })
      );

      case "invalid-focus" -> ok(
          http,

          "TextInput - Invalid (Focus)",

          Carbon.textInput(t -> {
            t.focus();
            t.id(TEXT_INPUT);
            t.invalidText("Error message goes here");
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );
    }
  }

  private void ok(Http.Exchange http, String title, Html.Component... elements) {
    http.ok(Page.create(page -> {
      page.theme(theme(http));

      page.title(title);

      page.head(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.css("""
      display:flex
      flex-direction:column
      gap:16rx
      max-width:640rx
      padding:42rx
      """);

      page.main(
          m -> m.c(elements),

          themeSwitcher(http)
      );
    }));
  }

  private void styles(Http.Exchange http) {
    http.ok(Css.StyleSheet.create(opts -> {
      final Note.Sink noteSink;
      noteSink = injector.getInstance(Note.Sink.class);

      opts.noteSink(noteSink);

      final Path classOutput;
      classOutput = Path.of("work", "main");

      opts.scanDirectory(classOutput);

      final Css.Library styles;
      styles = injector.getInstance(DevStart.CARBON_STYLES);

      opts.include(styles);

      final Css.Library plex;
      plex = injector.getInstance(DevStart.CARBON_PLEX);

      opts.include(plex);
    }));
  }

  private Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Theme.of(themeName);
  }

  private Html.Component themeSwitcher(Http.Exchange http) {
    final Theme theme;
    theme = theme(http);

    final CarbonTheme impl;
    impl = (CarbonTheme) theme;

    final CarbonTheme[] values;
    values = CarbonTheme.values();

    final int ordinal;
    ordinal = impl.ordinal();

    final CarbonTheme prev;
    prev = ordinal == 0 ? null : values[ordinal - 1];

    final CarbonTheme next;
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

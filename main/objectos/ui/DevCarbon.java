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

import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import objectos.ui.carbon.CarbonTheme;
import objectos.way.App;
import objectos.way.Css;
import objectos.way.Html;
import objectos.way.Http;
import objectos.way.Note;
import objectos.way.Script;

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
    carbon.subpath("/formgroup/{id}/{theme}", GET, this::formGroup);
    carbon.subpath("/layer/{id}/{theme}", GET, this::layer);
    carbon.subpath("/page/{theme}", GET, this::page);
    carbon.subpath("/tearsheet/{id}/{theme}", GET, this::tearsheet);
    carbon.subpath("/textinput/{id}/{theme}", GET, this::textInput);

    carbon.subpath("/styles.css", GET, this::styles);

    carbon.handler(Http.Handler.notFound());
  }

  private void formGroup(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Form Group - Default",

          Carbon.formGroup(g -> {
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
        background-color:layer
        color:text-primary
        padding:16rx
        """),

        m.text("Test component")
    );

    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Layer - Default");

        page.add(m -> m.div(
            m.css("padding:32rx sm:max-width:640rx"),

            m.c(test),

            m.c(Carbon.layer(l1 -> {
              l1.add(test);

              l1.add(Carbon.layer(l2 -> {
                l2.add(test);
              }));
            })),

            m.c(themeSwitcher(http))
        ));
      }));
    }
  }

  private void page(Http.Exchange http) {
    http.ok(Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.title("Objectos Carbon");
    }));
  }

  private Html.Component page(Http.Exchange http, Consumer<? super Carbon.Page> more) {
    return Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      more.accept(page);
    });
  }

  private void tearsheet(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(page(http, page -> {
        page.title("Tearsheet - Default");

        page.add(Carbon.tearsheet(t -> {
          t.open(true);

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

              m.c(themeSwitcher(http))
          ));
        }));
      }));
    }
  }

  static final Html.Id TEXT_INPUT = Html.Id.of("text-input");

  private void textInput(Http.Exchange http) {
    final BiConsumer<String, Html.Component> tmpl;
    tmpl = (title, component) -> http.ok(page(http, page -> {
      page.title(title);

      page.add(m -> m.div(
          m.css("""
            display:flex
            flex-direction:column
            gap:16rx
            max-width:640rx
            padding:42rx
            """),

          m.c(component),

          m.c(themeSwitcher(http))
      ));
    }));

    switch (http.pathParam("id")) {
      case "default" -> tmpl.accept(
          "TextInput - Default",

          Carbon.textInput(t -> {
            t.helperText("Helper text");
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );

      case "default-focus" -> tmpl.accept(
          "TextInput - Default (Focus)",

          Carbon.textInput(t -> {
            t.focus();
            t.helperText("Helper text");
            t.id(TEXT_INPUT);
            t.labelText("Label text");
            t.placeholder("Placeholder text");
          })
      );

      case "invalid" -> tmpl.accept(
          "TextInput - Invalid",

          Carbon.textInput(t -> {
            t.invalidText("Error message goes here");

            t.labelText("Label text");

            t.placeholder("Placeholder text");
          })
      );

      case "invalid-focus" -> tmpl.accept(
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

  private void ok(Http.Exchange http, String title, Html.Component component) {
    http.ok(Carbon.page(page -> {
      page.theme(theme(http));

      page.headEnd(m -> {
        m.link(m.rel("stylesheet"), m.type("text/css"), m.href("/carbon/styles.css"));
        m.script(m.src("/script.js"));
      });

      page.title(title);

      page.add(m -> m.div(
          m.css("""
            display:flex
            flex-direction:column
            gap:16rx
            max-width:640rx
            padding:42rx
            """),

          m.c(component),

          m.c(themeSwitcher(http))
      ));
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

      Carbon.configureStyleSheet(opts);
    }));
  }

  private Carbon.Theme theme(Http.Exchange http) {
    final String themeName;
    themeName = http.pathParam("theme");

    return Carbon.Theme.of(themeName);
  }

  private Html.Component themeSwitcher(Http.Exchange http) {
    final Carbon.Theme theme;
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

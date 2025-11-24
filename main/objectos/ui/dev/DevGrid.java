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

import module objectos.ui;

@Css.Source
final class DevGrid extends AbstractDevUi {

  DevGrid() {
    super("grid");
  }

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(
          page(http, page -> {
            page.title("Grid - Default");

            page.css("""
            padding:16rx
            """);

            page.main(
                Vertical.of(
                    Spacing.SPACING_07,

                    Grid.create(grid -> {
                      grid.css("""
                      layer-0
                      background-color:var(--color-layer)
                      min-block-size:80rx
                      outline:1px_dashed_var(--color-border-strong)
                      """);

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(4);
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(4);
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(4);
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(4);
                      });
                    }),

                    Grid.create(grid -> {
                      grid.css("""
                      layer-0
                      background-color:var(--color-layer)
                      min-block-size:80rx
                      outline:1px_dashed_var(--color-border-strong)
                      """);

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(2);
                        col.span(Breakpoint.MD, 4);
                        col.span(Breakpoint.LG, 6);
                        col.add(m -> {
                          m.p("Small: Span 2 of 4");
                          m.p("Medium: Span 4 of 8");
                          m.p("Large: Span 6 of 16");
                        });
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(2);
                        col.span(Breakpoint.MD, 2);
                        col.span(Breakpoint.LG, 3);
                        col.add(m -> {
                          m.p("Small: Span 2 of 4");
                          m.p("Medium: Span 2 of 8");
                          m.p("Large: Span 3 of 16");
                        });
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(0);
                        col.span(Breakpoint.MD, 2);
                        col.span(Breakpoint.LG, 3);
                        col.add(m -> {
                          m.p("Small: Span 0 of 4");
                          m.p("Medium: Span 2 of 8");
                          m.p("Large: Span 3 of 16");
                        });
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(0);
                        col.span(Breakpoint.MD, 0);
                        col.span(Breakpoint.LG, 4);
                        col.add(m -> {
                          m.p("Small: Span 0 of 4");
                          m.p("Medium: Span 0 of 8");
                          m.p("Large: Span 4 of 16");
                        });
                      });

                      grid.column(col -> {
                        col.css("""
                        layer-1
                        background-color:var(--color-layer)
                        box-shadow:0_0_0_1px_var(--color-border-strong)
                        min-block-size:80rx
                        """);

                        col.span(1);
                        col.span(Breakpoint.MD, 4);
                        col.span(Breakpoint.LG, 12);
                        col.add(m -> {
                          m.p("Small: Span 25%");
                          m.p("Medium: Span 50%");
                          m.p("Large: Span 75%");
                        });
                      });
                    })
                ));
          })
      );
    }
  }

}

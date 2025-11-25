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

import static objectos.ui.Breakpoint.LG;
import static objectos.ui.Breakpoint.MD;

import module java.base;
import module objectos.ui;

@Css.Source
final class DevGrid extends AbstractDevUi {

  DevGrid() {
    super("grid");
  }

  private static final String GRID_DEFAULT = """
  min-block-size:80rx
  outline:1px_dashed_var(--color-border-strong)
  """;

  private static final String COLUMN = """
  layer-0
  background-color:var(--color-layer)
  box-shadow:0_0_0_1px_var(--color-border-strong)
  min-block-size:80rx
  """;

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(
          page(http, page -> {
            page.title("Grid - Default");

            grid(page, _ -> {});
          })
      );

      case "full-width" -> http.ok(
          page(http, page -> {
            page.title("Grid - Full Width");

            grid(page, g -> { g.fullWidth(); });
          })
      );
    }
  }

  private void grid(Page.Options page, Consumer<? super Grid.Options> more) {
    page.css(Vertical.cssOf(Spacing.SPACING_07, "padding:32rx_0"));

    page.add(Grid.create(grid -> {
      more.accept(grid);

      grid.css(GRID_DEFAULT);

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(4));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(4));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(4));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(4));
      }));
    }));

    page.add(Grid.create(grid -> {
      more.accept(grid);

      grid.css(GRID_DEFAULT);

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(p -> p.start(4).span(1));
        col.props(MD, p -> p.start(7).span(2));
        col.props(LG, p -> p.start(13).span(4));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(p -> p.start(3).span(2));
        col.props(MD, p -> p.start(5).span(4));
        col.props(LG, p -> p.start(9).span(8));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(p -> p.start(2).span(3));
        col.props(MD, p -> p.start(3).span(6));
        col.props(LG, p -> p.start(5).span(12));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(p -> p.span(4));
        col.props(MD, p -> p.span(8));
        col.props(LG, p -> p.span(16));
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(p -> p.start(2).span(1));
        col.props(MD, p -> p.start(3).span(4));
        col.props(LG, p -> p.start(5).span(12));
      }));
    }));

    page.add(Grid.create(grid -> {
      more.accept(grid);

      grid.css(GRID_DEFAULT);

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(2));
        col.props(MD, props -> props.span(4));
        col.props(LG, props -> props.span(6));
        col.add(m -> {
          m.p("Small: Span 2 of 4");
          m.p("Medium: Span 4 of 8");
          m.p("Large: Span 6 of 16");
        });
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(2));
        col.props(MD, props -> props.span(2));
        col.props(LG, props -> props.span(3));
        col.add(m -> {
          m.p("Small: Span 2 of 4");
          m.p("Medium: Span 2 of 8");
          m.p("Large: Span 3 of 16");
        });
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(0));
        col.props(MD, props -> props.span(2));
        col.props(LG, props -> props.span(3));
        col.add(m -> {
          m.p("Small: Span 0 of 4");
          m.p("Medium: Span 2 of 8");
          m.p("Large: Span 3 of 16");
        });
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(0));
        col.props(MD, props -> props.span(0));
        col.props(LG, props -> props.span(4));
        col.add(m -> {
          m.p("Small: Span 0 of 4");
          m.p("Medium: Span 0 of 8");
          m.p("Large: Span 4 of 16");
        });
      }));

      grid.add(Column.create(col -> {
        col.css(COLUMN);

        col.props(props -> props.span(1));
        col.props(MD, props -> props.span(4));
        col.props(LG, props -> props.span(12));
        col.add(m -> {
          m.p("Small: Span 25%");
          m.p("Medium: Span 50%");
          m.p("Large: Span 75%");
        });
      }));
    }));
  }

}

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

import module java.base;
import module objectos.ui;
import objectos.ui.Breakpoint;

public final class UiColumn extends UiComponent implements Column, Column.Options {

  private static final Map<UiBreakpoint, List<Html.ClassName>> SPANS;

  static {
    final Map<UiBreakpoint, List<Html.ClassName>> spans;
    spans = new EnumMap<>(UiBreakpoint.class);

    // START: generated code

    spans.put(
        UiBreakpoint.XS,
        List.of(
            cn("""
            display:none
            """),
            cn("""
            --grid-columns:1
            display:block
            grid-column:span_1/span_1
            """),
            cn("""
            --grid-columns:2
            display:block
            grid-column:span_2/span_2
            """),
            cn("""
            --grid-columns:3
            display:block
            grid-column:span_3/span_3
            """),
            cn("""
            --grid-columns:4
            display:block
            grid-column:span_4/span_4
            """),
            cn("""
            --grid-columns:5
            display:block
            grid-column:span_5/span_5
            """),
            cn("""
            --grid-columns:6
            display:block
            grid-column:span_6/span_6
            """),
            cn("""
            --grid-columns:7
            display:block
            grid-column:span_7/span_7
            """),
            cn("""
            --grid-columns:8
            display:block
            grid-column:span_8/span_8
            """),
            cn("""
            --grid-columns:9
            display:block
            grid-column:span_9/span_9
            """),
            cn("""
            --grid-columns:10
            display:block
            grid-column:span_10/span_10
            """),
            cn("""
            --grid-columns:11
            display:block
            grid-column:span_11/span_11
            """),
            cn("""
            --grid-columns:12
            display:block
            grid-column:span_12/span_12
            """),
            cn("""
            --grid-columns:13
            display:block
            grid-column:span_13/span_13
            """),
            cn("""
            --grid-columns:14
            display:block
            grid-column:span_14/span_14
            """),
            cn("""
            --grid-columns:15
            display:block
            grid-column:span_15/span_15
            """),
            cn("""
            --grid-columns:16
            display:block
            grid-column:span_16/span_16
            """)
        )
    );
    spans.put(
        UiBreakpoint.SM,
        List.of(
            cn("""
            sm/display:none
            """),
            cn("""
            sm/--grid-columns:1
            sm/display:block
            sm/grid-column:span_1/span_1
            """),
            cn("""
            sm/--grid-columns:2
            sm/display:block
            sm/grid-column:span_2/span_2
            """),
            cn("""
            sm/--grid-columns:3
            sm/display:block
            sm/grid-column:span_3/span_3
            """),
            cn("""
            sm/--grid-columns:4
            sm/display:block
            sm/grid-column:span_4/span_4
            """)
        )
    );
    spans.put(
        UiBreakpoint.MD,
        List.of(
            cn("""
            md/display:none
            """),
            cn("""
            md/--grid-columns:1
            md/display:block
            md/grid-column:span_1/span_1
            """),
            cn("""
            md/--grid-columns:2
            md/display:block
            md/grid-column:span_2/span_2
            """),
            cn("""
            md/--grid-columns:3
            md/display:block
            md/grid-column:span_3/span_3
            """),
            cn("""
            md/--grid-columns:4
            md/display:block
            md/grid-column:span_4/span_4
            """),
            cn("""
            md/--grid-columns:5
            md/display:block
            md/grid-column:span_5/span_5
            """),
            cn("""
            md/--grid-columns:6
            md/display:block
            md/grid-column:span_6/span_6
            """),
            cn("""
            md/--grid-columns:7
            md/display:block
            md/grid-column:span_7/span_7
            """),
            cn("""
            md/--grid-columns:8
            md/display:block
            md/grid-column:span_8/span_8
            """)
        )
    );
    spans.put(
        UiBreakpoint.LG,
        List.of(
            cn("""
            lg/display:none
            """),
            cn("""
            lg/--grid-columns:1
            lg/display:block
            lg/grid-column:span_1/span_1
            """),
            cn("""
            lg/--grid-columns:2
            lg/display:block
            lg/grid-column:span_2/span_2
            """),
            cn("""
            lg/--grid-columns:3
            lg/display:block
            lg/grid-column:span_3/span_3
            """),
            cn("""
            lg/--grid-columns:4
            lg/display:block
            lg/grid-column:span_4/span_4
            """),
            cn("""
            lg/--grid-columns:5
            lg/display:block
            lg/grid-column:span_5/span_5
            """),
            cn("""
            lg/--grid-columns:6
            lg/display:block
            lg/grid-column:span_6/span_6
            """),
            cn("""
            lg/--grid-columns:7
            lg/display:block
            lg/grid-column:span_7/span_7
            """),
            cn("""
            lg/--grid-columns:8
            lg/display:block
            lg/grid-column:span_8/span_8
            """),
            cn("""
            lg/--grid-columns:9
            lg/display:block
            lg/grid-column:span_9/span_9
            """),
            cn("""
            lg/--grid-columns:10
            lg/display:block
            lg/grid-column:span_10/span_10
            """),
            cn("""
            lg/--grid-columns:11
            lg/display:block
            lg/grid-column:span_11/span_11
            """),
            cn("""
            lg/--grid-columns:12
            lg/display:block
            lg/grid-column:span_12/span_12
            """),
            cn("""
            lg/--grid-columns:13
            lg/display:block
            lg/grid-column:span_13/span_13
            """),
            cn("""
            lg/--grid-columns:14
            lg/display:block
            lg/grid-column:span_14/span_14
            """),
            cn("""
            lg/--grid-columns:15
            lg/display:block
            lg/grid-column:span_15/span_15
            """),
            cn("""
            lg/--grid-columns:16
            lg/display:block
            lg/grid-column:span_16/span_16
            """)
        )
    );
    spans.put(
        UiBreakpoint.XL,
        List.of(
            cn("""
            xl/display:none
            """),
            cn("""
            xl/--grid-columns:1
            xl/display:block
            xl/grid-column:span_1/span_1
            """),
            cn("""
            xl/--grid-columns:2
            xl/display:block
            xl/grid-column:span_2/span_2
            """),
            cn("""
            xl/--grid-columns:3
            xl/display:block
            xl/grid-column:span_3/span_3
            """),
            cn("""
            xl/--grid-columns:4
            xl/display:block
            xl/grid-column:span_4/span_4
            """),
            cn("""
            xl/--grid-columns:5
            xl/display:block
            xl/grid-column:span_5/span_5
            """),
            cn("""
            xl/--grid-columns:6
            xl/display:block
            xl/grid-column:span_6/span_6
            """),
            cn("""
            xl/--grid-columns:7
            xl/display:block
            xl/grid-column:span_7/span_7
            """),
            cn("""
            xl/--grid-columns:8
            xl/display:block
            xl/grid-column:span_8/span_8
            """),
            cn("""
            xl/--grid-columns:9
            xl/display:block
            xl/grid-column:span_9/span_9
            """),
            cn("""
            xl/--grid-columns:10
            xl/display:block
            xl/grid-column:span_10/span_10
            """),
            cn("""
            xl/--grid-columns:11
            xl/display:block
            xl/grid-column:span_11/span_11
            """),
            cn("""
            xl/--grid-columns:12
            xl/display:block
            xl/grid-column:span_12/span_12
            """),
            cn("""
            xl/--grid-columns:13
            xl/display:block
            xl/grid-column:span_13/span_13
            """),
            cn("""
            xl/--grid-columns:14
            xl/display:block
            xl/grid-column:span_14/span_14
            """),
            cn("""
            xl/--grid-columns:15
            xl/display:block
            xl/grid-column:span_15/span_15
            """),
            cn("""
            xl/--grid-columns:16
            xl/display:block
            xl/grid-column:span_16/span_16
            """)
        )
    );
    spans.put(
        UiBreakpoint.X2,
        List.of(
            cn("""
            x2/display:none
            """),
            cn("""
            x2/--grid-columns:1
            x2/display:block
            x2/grid-column:span_1/span_1
            """),
            cn("""
            x2/--grid-columns:2
            x2/display:block
            x2/grid-column:span_2/span_2
            """),
            cn("""
            x2/--grid-columns:3
            x2/display:block
            x2/grid-column:span_3/span_3
            """),
            cn("""
            x2/--grid-columns:4
            x2/display:block
            x2/grid-column:span_4/span_4
            """),
            cn("""
            x2/--grid-columns:5
            x2/display:block
            x2/grid-column:span_5/span_5
            """),
            cn("""
            x2/--grid-columns:6
            x2/display:block
            x2/grid-column:span_6/span_6
            """),
            cn("""
            x2/--grid-columns:7
            x2/display:block
            x2/grid-column:span_7/span_7
            """),
            cn("""
            x2/--grid-columns:8
            x2/display:block
            x2/grid-column:span_8/span_8
            """),
            cn("""
            x2/--grid-columns:9
            x2/display:block
            x2/grid-column:span_9/span_9
            """),
            cn("""
            x2/--grid-columns:10
            x2/display:block
            x2/grid-column:span_10/span_10
            """),
            cn("""
            x2/--grid-columns:11
            x2/display:block
            x2/grid-column:span_11/span_11
            """),
            cn("""
            x2/--grid-columns:12
            x2/display:block
            x2/grid-column:span_12/span_12
            """),
            cn("""
            x2/--grid-columns:13
            x2/display:block
            x2/grid-column:span_13/span_13
            """),
            cn("""
            x2/--grid-columns:14
            x2/display:block
            x2/grid-column:span_14/span_14
            """),
            cn("""
            x2/--grid-columns:15
            x2/display:block
            x2/grid-column:span_15/span_15
            """),
            cn("""
            x2/--grid-columns:16
            x2/display:block
            x2/grid-column:span_16/span_16
            """)
        )
    );

    // END: generated code

    SPANS = spans;
  }

  private static Html.ClassName cn(String value) {
    return Html.ClassName.ofText(value);
  }

  private final Html.ElementName as = Html.ElementName.DIV;

  private String css;

  private final Map<UiBreakpoint, Html.ClassName> span = new EnumMap<>(UiBreakpoint.class);

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void span(int value) {
    span0(UiBreakpoint.XS, value);
  }

  @Override
  public final void span(Breakpoint breakpoint, int value) {
    final UiBreakpoint impl;
    impl = (UiBreakpoint) breakpoint;

    span0(impl, value);
  }

  private void span0(UiBreakpoint impl, int value) {
    final int max;
    max = impl.gridColumns;

    checkSpan(max, value);

    final List<Html.ClassName> list;
    list = SPANS.get(impl);

    final Html.ClassName style;
    style = list.get(value);

    span.put(impl, style);
  }

  private void checkSpan(int max, int value) {
    if (0 <= value && value <= max) {
      return;
    }

    throw new IllegalArgumentException(
        "span values must be: 0 <= span <= %d".formatted(max)
    );
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        m.css("""
        --grid-mode-start:var(--grid-gutter-start)
        --grid-mode-end:var(--grid-gutter-end)
        margin-inline:var(--grid-gutter-start)_var(--grid-gutter-end)
        """),

        css != null ? m.css(css) : m.noop(),

        m.flatten(span.values())
    );
  }

}

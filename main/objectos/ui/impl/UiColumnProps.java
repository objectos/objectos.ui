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

public final class UiColumnProps implements Column.Props, Html.AttributeObject {

  private final UiBreakpoint breakpoint;

  private int span = -1;

  private int start = 0;

  UiColumnProps(UiBreakpoint breakpoint) {
    this.breakpoint = breakpoint;
  }

  @Override
  public final Column.Props span(int value) {
    if (value < 0) {
      throw new IllegalArgumentException(
          "span values must not be negative"
      );
    }

    final int max;
    max = breakpoint.gridColumns;

    if (value > max) {
      throw new IllegalArgumentException(
          "span values must be: 0 <= span <= %d".formatted(max)
      );
    }

    span = value;

    return this;
  }

  @Override
  public final Column.Props start(int value) {
    if (value == 0) {
      throw new IllegalArgumentException(
          "0 is not a valid column number: column numbers are 1-based"
      );
    }

    if (value < 0) {
      throw new IllegalArgumentException(
          "column numbers must not be negative"
      );
    }

    final int max;
    max = breakpoint.gridColumns;

    if (value > max) {
      throw new IllegalArgumentException(
          "start values must be: 1 <= start <= %d".formatted(max)
      );
    }

    start = value;

    return this;
  }

  //

  @Override
  public final Html.AttributeName attrName() {
    return Html.AttributeName.CLASS;
  }

  @Override
  public final String attrValue() {
    final StringBuilder sb;
    sb = new StringBuilder();

    if (span != -1) {
      append(sb, SPAN, span);
    }

    if (start != 0) {
      append(sb, START, start);
    }

    return sb.toString();
  }

  private void append(StringBuilder sb, Map<UiBreakpoint, List<String>> map, int idx) {
    if (!sb.isEmpty()) {
      sb.append(' ');
    }

    final List<String> list;
    list = map.get(breakpoint);

    final String cn;
    cn = list.get(idx);

    sb.append(cn);
  }

  private static final Map<UiBreakpoint, List<String>> SPAN = new EnumMap<>(
      Map.of(
          // START generated code

          UiBreakpoint.XS, List.of(
              """
              display:none
              """,
              """
              --grid-columns:1
              display:block
              grid-column:span_1/span_1
              """,
              """
              --grid-columns:2
              display:block
              grid-column:span_2/span_2
              """,
              """
              --grid-columns:3
              display:block
              grid-column:span_3/span_3
              """,
              """
              --grid-columns:4
              display:block
              grid-column:span_4/span_4
              """,
              """
              --grid-columns:5
              display:block
              grid-column:span_5/span_5
              """,
              """
              --grid-columns:6
              display:block
              grid-column:span_6/span_6
              """,
              """
              --grid-columns:7
              display:block
              grid-column:span_7/span_7
              """,
              """
              --grid-columns:8
              display:block
              grid-column:span_8/span_8
              """,
              """
              --grid-columns:9
              display:block
              grid-column:span_9/span_9
              """,
              """
              --grid-columns:10
              display:block
              grid-column:span_10/span_10
              """,
              """
              --grid-columns:11
              display:block
              grid-column:span_11/span_11
              """,
              """
              --grid-columns:12
              display:block
              grid-column:span_12/span_12
              """,
              """
              --grid-columns:13
              display:block
              grid-column:span_13/span_13
              """,
              """
              --grid-columns:14
              display:block
              grid-column:span_14/span_14
              """,
              """
              --grid-columns:15
              display:block
              grid-column:span_15/span_15
              """,
              """
              --grid-columns:16
              display:block
              grid-column:span_16/span_16
              """
          ),
          UiBreakpoint.SM, List.of(
              """
              sm/display:none
              """,
              """
              sm/--grid-columns:1
              sm/display:block
              sm/grid-column:span_1/span_1
              """,
              """
              sm/--grid-columns:2
              sm/display:block
              sm/grid-column:span_2/span_2
              """,
              """
              sm/--grid-columns:3
              sm/display:block
              sm/grid-column:span_3/span_3
              """,
              """
              sm/--grid-columns:4
              sm/display:block
              sm/grid-column:span_4/span_4
              """
          ),
          UiBreakpoint.MD, List.of(
              """
              md/display:none
              """,
              """
              md/--grid-columns:1
              md/display:block
              md/grid-column:span_1/span_1
              """,
              """
              md/--grid-columns:2
              md/display:block
              md/grid-column:span_2/span_2
              """,
              """
              md/--grid-columns:3
              md/display:block
              md/grid-column:span_3/span_3
              """,
              """
              md/--grid-columns:4
              md/display:block
              md/grid-column:span_4/span_4
              """,
              """
              md/--grid-columns:5
              md/display:block
              md/grid-column:span_5/span_5
              """,
              """
              md/--grid-columns:6
              md/display:block
              md/grid-column:span_6/span_6
              """,
              """
              md/--grid-columns:7
              md/display:block
              md/grid-column:span_7/span_7
              """,
              """
              md/--grid-columns:8
              md/display:block
              md/grid-column:span_8/span_8
              """
          ),
          UiBreakpoint.LG, List.of(
              """
              lg/display:none
              """,
              """
              lg/--grid-columns:1
              lg/display:block
              lg/grid-column:span_1/span_1
              """,
              """
              lg/--grid-columns:2
              lg/display:block
              lg/grid-column:span_2/span_2
              """,
              """
              lg/--grid-columns:3
              lg/display:block
              lg/grid-column:span_3/span_3
              """,
              """
              lg/--grid-columns:4
              lg/display:block
              lg/grid-column:span_4/span_4
              """,
              """
              lg/--grid-columns:5
              lg/display:block
              lg/grid-column:span_5/span_5
              """,
              """
              lg/--grid-columns:6
              lg/display:block
              lg/grid-column:span_6/span_6
              """,
              """
              lg/--grid-columns:7
              lg/display:block
              lg/grid-column:span_7/span_7
              """,
              """
              lg/--grid-columns:8
              lg/display:block
              lg/grid-column:span_8/span_8
              """,
              """
              lg/--grid-columns:9
              lg/display:block
              lg/grid-column:span_9/span_9
              """,
              """
              lg/--grid-columns:10
              lg/display:block
              lg/grid-column:span_10/span_10
              """,
              """
              lg/--grid-columns:11
              lg/display:block
              lg/grid-column:span_11/span_11
              """,
              """
              lg/--grid-columns:12
              lg/display:block
              lg/grid-column:span_12/span_12
              """,
              """
              lg/--grid-columns:13
              lg/display:block
              lg/grid-column:span_13/span_13
              """,
              """
              lg/--grid-columns:14
              lg/display:block
              lg/grid-column:span_14/span_14
              """,
              """
              lg/--grid-columns:15
              lg/display:block
              lg/grid-column:span_15/span_15
              """,
              """
              lg/--grid-columns:16
              lg/display:block
              lg/grid-column:span_16/span_16
              """
          ),
          UiBreakpoint.XL, List.of(
              """
              xl/display:none
              """,
              """
              xl/--grid-columns:1
              xl/display:block
              xl/grid-column:span_1/span_1
              """,
              """
              xl/--grid-columns:2
              xl/display:block
              xl/grid-column:span_2/span_2
              """,
              """
              xl/--grid-columns:3
              xl/display:block
              xl/grid-column:span_3/span_3
              """,
              """
              xl/--grid-columns:4
              xl/display:block
              xl/grid-column:span_4/span_4
              """,
              """
              xl/--grid-columns:5
              xl/display:block
              xl/grid-column:span_5/span_5
              """,
              """
              xl/--grid-columns:6
              xl/display:block
              xl/grid-column:span_6/span_6
              """,
              """
              xl/--grid-columns:7
              xl/display:block
              xl/grid-column:span_7/span_7
              """,
              """
              xl/--grid-columns:8
              xl/display:block
              xl/grid-column:span_8/span_8
              """,
              """
              xl/--grid-columns:9
              xl/display:block
              xl/grid-column:span_9/span_9
              """,
              """
              xl/--grid-columns:10
              xl/display:block
              xl/grid-column:span_10/span_10
              """,
              """
              xl/--grid-columns:11
              xl/display:block
              xl/grid-column:span_11/span_11
              """,
              """
              xl/--grid-columns:12
              xl/display:block
              xl/grid-column:span_12/span_12
              """,
              """
              xl/--grid-columns:13
              xl/display:block
              xl/grid-column:span_13/span_13
              """,
              """
              xl/--grid-columns:14
              xl/display:block
              xl/grid-column:span_14/span_14
              """,
              """
              xl/--grid-columns:15
              xl/display:block
              xl/grid-column:span_15/span_15
              """,
              """
              xl/--grid-columns:16
              xl/display:block
              xl/grid-column:span_16/span_16
              """
          ),
          UiBreakpoint.X2, List.of(
              """
              x2/display:none
              """,
              """
              x2/--grid-columns:1
              x2/display:block
              x2/grid-column:span_1/span_1
              """,
              """
              x2/--grid-columns:2
              x2/display:block
              x2/grid-column:span_2/span_2
              """,
              """
              x2/--grid-columns:3
              x2/display:block
              x2/grid-column:span_3/span_3
              """,
              """
              x2/--grid-columns:4
              x2/display:block
              x2/grid-column:span_4/span_4
              """,
              """
              x2/--grid-columns:5
              x2/display:block
              x2/grid-column:span_5/span_5
              """,
              """
              x2/--grid-columns:6
              x2/display:block
              x2/grid-column:span_6/span_6
              """,
              """
              x2/--grid-columns:7
              x2/display:block
              x2/grid-column:span_7/span_7
              """,
              """
              x2/--grid-columns:8
              x2/display:block
              x2/grid-column:span_8/span_8
              """,
              """
              x2/--grid-columns:9
              x2/display:block
              x2/grid-column:span_9/span_9
              """,
              """
              x2/--grid-columns:10
              x2/display:block
              x2/grid-column:span_10/span_10
              """,
              """
              x2/--grid-columns:11
              x2/display:block
              x2/grid-column:span_11/span_11
              """,
              """
              x2/--grid-columns:12
              x2/display:block
              x2/grid-column:span_12/span_12
              """,
              """
              x2/--grid-columns:13
              x2/display:block
              x2/grid-column:span_13/span_13
              """,
              """
              x2/--grid-columns:14
              x2/display:block
              x2/grid-column:span_14/span_14
              """,
              """
              x2/--grid-columns:15
              x2/display:block
              x2/grid-column:span_15/span_15
              """,
              """
              x2/--grid-columns:16
              x2/display:block
              x2/grid-column:span_16/span_16
              """
          )

      // END generated code
      )
  );

  private static final Map<UiBreakpoint, List<String>> START = new EnumMap<>(
      Map.of(
          // START generated code
          UiBreakpoint.XS, List.of(
              "",
              "grid-column-start:1",
              "grid-column-start:2",
              "grid-column-start:3",
              "grid-column-start:4",
              "grid-column-start:5",
              "grid-column-start:6",
              "grid-column-start:7",
              "grid-column-start:8",
              "grid-column-start:9",
              "grid-column-start:10",
              "grid-column-start:11",
              "grid-column-start:12",
              "grid-column-start:13",
              "grid-column-start:14",
              "grid-column-start:15",
              "grid-column-start:16"
          ),
          UiBreakpoint.SM, List.of(
              "",
              "sm/grid-column-start:1",
              "sm/grid-column-start:2",
              "sm/grid-column-start:3",
              "sm/grid-column-start:4"
          ),
          UiBreakpoint.MD, List.of(
              "",
              "md/grid-column-start:1",
              "md/grid-column-start:2",
              "md/grid-column-start:3",
              "md/grid-column-start:4",
              "md/grid-column-start:5",
              "md/grid-column-start:6",
              "md/grid-column-start:7",
              "md/grid-column-start:8"
          ),
          UiBreakpoint.LG, List.of(
              "",
              "lg/grid-column-start:1",
              "lg/grid-column-start:2",
              "lg/grid-column-start:3",
              "lg/grid-column-start:4",
              "lg/grid-column-start:5",
              "lg/grid-column-start:6",
              "lg/grid-column-start:7",
              "lg/grid-column-start:8",
              "lg/grid-column-start:9",
              "lg/grid-column-start:10",
              "lg/grid-column-start:11",
              "lg/grid-column-start:12",
              "lg/grid-column-start:13",
              "lg/grid-column-start:14",
              "lg/grid-column-start:15",
              "lg/grid-column-start:16"
          ),
          UiBreakpoint.XL, List.of(
              "",
              "xl/grid-column-start:1",
              "xl/grid-column-start:2",
              "xl/grid-column-start:3",
              "xl/grid-column-start:4",
              "xl/grid-column-start:5",
              "xl/grid-column-start:6",
              "xl/grid-column-start:7",
              "xl/grid-column-start:8",
              "xl/grid-column-start:9",
              "xl/grid-column-start:10",
              "xl/grid-column-start:11",
              "xl/grid-column-start:12",
              "xl/grid-column-start:13",
              "xl/grid-column-start:14",
              "xl/grid-column-start:15",
              "xl/grid-column-start:16"
          ),
          UiBreakpoint.X2, List.of(
              "",
              "x2/grid-column-start:1",
              "x2/grid-column-start:2",
              "x2/grid-column-start:3",
              "x2/grid-column-start:4",
              "x2/grid-column-start:5",
              "x2/grid-column-start:6",
              "x2/grid-column-start:7",
              "x2/grid-column-start:8",
              "x2/grid-column-start:9",
              "x2/grid-column-start:10",
              "x2/grid-column-start:11",
              "x2/grid-column-start:12",
              "x2/grid-column-start:13",
              "x2/grid-column-start:14",
              "x2/grid-column-start:15",
              "x2/grid-column-start:16"
          )

      // END generated code
      )
  );

}

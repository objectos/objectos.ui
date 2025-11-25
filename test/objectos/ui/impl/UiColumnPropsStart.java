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

public class UiColumnPropsStart {

  private final StringBuilder code = new StringBuilder();

  public static void main(String[] args) {
    System.out.println(new UiColumnPropsStart());
  }

  @Override
  public final String toString() {
    prepare("XS", "", 16);
    prepare("SM", "sm/", 4);
    prepare("MD", "md/", 8);
    prepare("LG", "lg/", 16);
    prepare("XL", "xl/", 16);
    prepare("X2", "x2/", 16);

    final String result;
    result = code.toString();

    return result.indent(4);
  }

  private void prepare(String enumName, String variant, int max) {
    if (!variant.isEmpty()) {
      code.append(",\n");
    }

    code.append("""
              UiBreakpoint.%s, List.of(
              ""\
    """.formatted(enumName, variant));

    for (int span = 1; span <= max; span++) {
      code.append("""
    ,
              "%sgrid-column-start:%d"\
    """.formatted(variant, span));
    }

    code.append("""

              )\
    """);
  }

}

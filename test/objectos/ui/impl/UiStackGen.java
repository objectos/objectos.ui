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

public class UiStackGen {

  private final StringBuilder fields = new StringBuilder();

  public static void main(String[] args) {
    System.out.println(new UiStackGen());
  }

  @Override
  public final String toString() {
    prepare();

    return fields.toString();
  }

  private void prepare() {
    fields.append("""

      private static final String[] GAPS = {
    """);

    final int[] values;
    values = UiSpacingGen.VALUES;

    for (int idx = 0, len = values.length; idx < len; idx++) {
      fields.append("    \"row-gap:");

      final int value;
      value = values[idx];

      final String gap;
      gap = value == 0 ? "0" : value + "rx";

      fields.append(gap);

      fields.append('\"');

      if (idx + 1 < len) {
        fields.append(',');
      }

      fields.append('\n');
    }

    fields.append("""
      };
    """);
  }

}

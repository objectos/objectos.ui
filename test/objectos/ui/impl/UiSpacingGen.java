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

public class UiSpacingGen {

  static final int[] VALUES = {
      0,
      2,
      4,
      8,
      12,
      16,
      24,
      32,
      40,
      48,
      64,
      80,
      96,
      160
  };

  private static final int MIN = 1;
  private static final int MAX = 13;

  private final StringBuilder fields = new StringBuilder();

  public static void main(String[] args) {
    System.out.println(new UiSpacingGen());
  }

  @Override
  public final String toString() {
    prepare();

    return fields.toString();
  }

  private void prepare() {
    fields.append("""

      /// The zero value spacing.
      public static final Spacing SPACING_00 = CarbonSpacing.SPACING_00;
    """);

    for (int step = MIN; step <= MAX; step++) {
      fields.append("""

        /// The `$spacing-%1$02d` token.
        public static final Spacing SPACING_%1$02d = CarbonSpacing.SPACING_%1$02d;
      """.formatted(step));
    }
  }

}

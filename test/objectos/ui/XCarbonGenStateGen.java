/*
 * Objectos Start
 * Copyright (C) 2025 Objectos Software LTDA.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package objectos.ui;

final class XCarbonGenStateGen {

  private int state = 0;

  public static void main(String[] args) {
    final XCarbonGenStateGen gen;
    gen = new XCarbonGenStateGen();

    gen.value("$OPTIONS");
    gen.value("$OPTIONS_PARSE");

    gen.line();

    gen.value("$INIT");

    gen.line();

    gen.value("$HTML");
    gen.value("$HTML_IMPORT");
    gen.value("$HTML_BRACKET");
    gen.value("$HTML_LINK");
    gen.value("$HTML_HREF");

    gen.line();

    gen.value("$JS");
    gen.value("$JS_VERSION");

    gen.line();

    gen.value("$CSS");
    gen.value("$CSS_SEL");
    gen.value("$CSS_TARGET");
    gen.value("$CSS_SKIP_RULE");
    gen.value("$CSS_THEME");

    gen.line();

    gen.value("$MODULE");

    gen.line();

    gen.value("$DONE");
  }

  private void line() {
    System.out.println();
  }

  private void value(String name) {
    System.out.printf("static final byte %s = %d;%n", name, state++);
  }

}
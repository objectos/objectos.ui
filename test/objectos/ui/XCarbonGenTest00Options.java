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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class XCarbonGenTest00Options {

  @Test
  public void testCase01() {
    final XCarbonGen.Options options;
    options = new XCarbonGen.Options();

    options.parse(
        "--cds-iframe", "https://example.com/cds.html",
        "--c4p-iframe", "https://example.com/c4p.html",
        "--plex-sans", "https://example.com/plex-sans.zip"
    );

    assertEquals(options.cdsIframe.string(), "https://example.com/cds.html");
    assertEquals(options.c4pIframe.string(), "https://example.com/c4p.html");
    assertEquals(options.plexSans.string(), "https://example.com/plex-sans.zip");
  }

}
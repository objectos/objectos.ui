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

import java.net.URI;
import java.nio.file.Path;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Y.class)
public class XCarbonGenTest {

  @Test
  public void cds() {
    try (Y.Project proj = Y.project(opts -> {
      opts.webdir(Path.of("test-resources", "cds"));
    })) {
      final URI iframe;
      iframe = proj.resolveWeb("iframe.html");

      proj.carbonGen(
          "--cds-iframe", iframe.toString(),
          "--cds-html-filter", "button--default",
          "--c4p-skip", "true"
      );

      assertEquals(proj.exists("main/objectos/ui/CarbonStyles.java"), true);
      assertEquals(proj.exists("main-carbon/button.css"), true);
      assertEquals(proj.exists("main-carbon/button--default.html"), true);
    }
  }

  @Test
  public void c4p() {
    try (Y.Project proj = Y.project(opts -> {
      opts.webdir(Path.of("test-resources", "c4p"));
    })) {
      final URI iframe;
      iframe = proj.resolveWeb("iframe.html");

      proj.carbonGen(
          "--c4p-iframe", iframe.toString(),
          "--c4p-html-filter", "tearsheet--tearsheet",
          "--cds-skip", "true"
      );

      assertEquals(proj.exists("main-carbon/tearsheet.css"), true);
      assertEquals(proj.exists("main-carbon/tearsheet--tearsheet.html"), true);
    }
  }

}

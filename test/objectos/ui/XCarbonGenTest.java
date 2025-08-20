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

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.Test;

public class XCarbonGenTest {

  @Test
  public void testCase01() {
    try (Y.Project proj = Y.project(opts -> {
      opts.webdir(Path.of("test-resources", "carbon"));
    })) {
      final URI html;
      html = proj.resolveWeb("cds.html");

      proj.carbonGen(
          "--cds-html", html.toString(),
          "--c4p-skip", "true"
      );

      final Path carbon;
      carbon = proj.resolve("main/objectos/ui/CarbonStyles.java");

      assertEquals(Files.exists(carbon), true);
    }
  }

  @Test(enabled = false)
  public void testCase02() {
    try (Y.Project proj = Y.project(opts -> {
      opts.webdir(Path.of("test-resources", "carbon"));
    })) {
      final URI html;
      html = proj.resolveWeb("c4p.html");

      proj.carbonGen(
          "--c4p-html", html.toString(),
          "--cds-skip", "true"
      );

      final Path carbon;
      carbon = proj.resolve("main/objectos/ui/CarbonStyles.java");

      assertEquals(Files.exists(carbon), true);

      System.out.println(Files.readString(carbon));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}

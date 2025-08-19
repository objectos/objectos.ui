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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.Test;

public class XCarbonGenTest {

  @Test
  public void testCase01() throws IOException {
    try (Y.Project proj = Y.project(opts -> {
      opts.webdir(Path.of("test-resources", "carbon"));
    })) {
      final URI html;
      html = proj.resolveWeb("iframe.html");

      proj.carbonGen("--html", html.toString());

      final Path carbonModule;
      carbonModule = proj.resolve("main/objectos/ui/CarbonStyleSheet.java");

      System.out.println(
          Files.readString(carbonModule)
      );
    }
  }

}

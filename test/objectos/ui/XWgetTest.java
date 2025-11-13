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

import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.Test;

public class XWgetTest {

  @Test
  public void testCase01() {
    try (Y.Project proj = Y.project(opts -> { opts.webdir(Path.of("test-resources", "cds")); })) {
      final URI iframe;
      iframe = proj.resolveWeb("iframe.html");

      final String location;
      location = iframe.toString();

      final Path target;
      target = proj.resolve("parent/target.html");

      final XWget wget;
      wget = new XWget();

      wget.downloadTo(location, target);

      assertTrue(Files.exists(target));
    }
  }

}

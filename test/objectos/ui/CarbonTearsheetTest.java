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

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Request;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import objectos.way.Http;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Y.class)
public class CarbonTearsheetTest {

  static void module(Http.Routing carbon) {
  }

  static final String JS = """
  (_el, _level) => {
    function dom(element, level) {
      const tagName = element.tagName.toLowerCase();

      const classAttr = element.className ? ` class="${element.className}"` : '';

      const startTag = `<${tagName}${classAttr}>`;

      const children = element.children;

      const ind = " ".repeat(level * 2);

      if (children.length === 0) {
        return `${ind}${startTag}\\n${ind}</${tagName}>\\n`;
      } else {
        const nested = Array.from(children)
          .map(child => dom(child, level + 1))
          .join("");

        return `${ind}${startTag}\\n${nested}${ind}</${tagName}>\\n`;
      }
    }

    return dom(_el, _level);
  }
  """;

  @Test(enabled = false)
  public void testCase01() {
    try (Page page = Y.page()) {
      page.navigate("https://ibm-products.carbondesignsystem.com/iframe.html?globals=viewport.value%3Amobile2&viewMode=story&id=components-tearsheet--tearsheet");

      final Locator root;
      root = page.locator("#storybook-root");

      root.waitFor();

      final Locator styles;
      styles = root.locator("style");

      try (BufferedWriter w = Files.newBufferedWriter(Path.of("/tmp/tearsheet.css"))) {
        for (Locator style : styles.all()) {
          w.write(style.textContent());

          w.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testCase02() {
    try (Page page = Y.page()) {
      Consumer<Request> listener = request -> System.out.println("curl -O " + request.url());
      page.onRequestFinished(listener);
      page.navigate("https://ibm-products.carbondesignsystem.com/iframe.html?globals=viewport.value%3Amobile2&viewMode=story&id=components-tearsheet--tearsheet");

      final Locator root;
      root = page.locator("[data-carbon-devtools-id='c4p--Tearsheet']");

      root.waitFor();

      Object result = root.evaluate(JS, 0);

      try (BufferedWriter w = Files.newBufferedWriter(Path.of("/tmp/tearsheet.html"))) {
        w.write(result.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test(enabled = false)
  public void testCase03() {
    try (Page page = Y.page()) {
      List<String> urls = new ArrayList<>();
      Consumer<Request> listener = request -> urls.add(request.url());
      page.onRequestFinished(listener);
      page.navigate("https://ibm-products.carbondesignsystem.com/iframe.html?id=overview-examples--playground&viewMode=story");

      final Locator root;
      root = page.locator("#storybook-root");

      root.waitFor();
    }
  }

}

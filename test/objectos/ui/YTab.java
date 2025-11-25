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

import static org.testng.Assert.assertEquals;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForURLOptions;
import com.microsoft.playwright.options.ScreenshotAnimations;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;
import objectos.way.Html;

final class YTab implements Y.Tab {

  static final class ThisElem implements Y.TabElem {

    private final Locator locator;

    private ThisElem(Locator locator) {
      this.locator = locator;
    }

    @Override
    public final void blur() {
      locator.blur();
    }

    @Override
    public final void focus() {
      locator.focus();
    }

    @Override
    public final void hover() {
      locator.hover();
    }

  }

  private final String baseUrl;

  private final Page page;

  YTab(String baseUrl, Page page) {
    this.baseUrl = baseUrl;

    this.page = page;
  }

  @Override
  public final Y.TabElem byId(Html.Id id) {
    final String selector;
    selector = "#" + id.attrValue();

    final Locator locator;
    locator = page.locator(selector);

    return new ThisElem(locator);
  }

  @Override
  public final void close() {
    page.close();
  }

  @Override
  public final void dev() {
    final boolean headless;
    headless = Boolean.getBoolean("playwright.headless");

    if (headless) {
      // noop if running in headless mode
      return;
    }

    final WaitForURLOptions options;
    options = new Page.WaitForURLOptions().setTimeout(TimeUnit.DAYS.toMillis(1));

    page.waitForURL(baseUrl + "/dev-stop", options);
  }

  @Override
  public final void mouseDown() {
    page.mouse().down();
  }

  @Override
  public final void mouseUp() {
    page.mouse().up();
  }

  @Override
  public final void mouseTo(double x, double y) {
    page.mouse().move(x, y);
  }

  @Override
  public final void navigate(String path) {
    if (path.isEmpty()) {
      throw new IllegalArgumentException("path is empty");
    }

    final char first;
    first = path.charAt(0);

    if (first != '/') {
      throw new IllegalArgumentException("path must start with the '/' character");
    }

    final String url;
    url = baseUrl + path;

    page.navigate(url);
  }

  @Override
  public final void navigate(String path, Theme theme) {
    navigate(path + "/" + theme);
  }

  @Override
  public final void press(String key) {
    final Keyboard keyboard;
    keyboard = page.keyboard();

    keyboard.press(key);
  }

  private static final Path TMPDIR = Y.nextTempDir();

  private static final Path TESTDIR = Path.of("test-screenshots");

  @Override
  public final void screenshot(String... suffixes) {
    final String url;
    url = page.url();

    final String sub;
    sub = url.substring(baseUrl.length());

    final String suffix;

    if (suffixes.length == 0) {
      suffix = ".png";
    } else {
      suffix = Stream.of(suffixes)
          .map(o -> String.valueOf(o))
          .collect(Collectors.joining("-", "-", ".png"));
    }

    final String rel;
    rel = sub.substring(1) + suffix;

    final Path relative;
    relative = Path.of(rel);

    final Path path;
    path = Path.of("test-screenshots" + sub + suffix);

    final Path test;
    test = TESTDIR.resolve(relative);

    if (Files.exists(path)) {
      final Path shot;
      shot = TMPDIR.resolve(relative);

      screenshot(shot);

      compare(test, shot);
    } else {
      screenshot(test);
    }
  }

  private void screenshot(Path file) {
    final Locator.ScreenshotOptions options;
    options = new Locator.ScreenshotOptions();

    options.setAnimations(ScreenshotAnimations.DISABLED);

    options.setPath(file);

    final Locator body;
    body = page.locator("body");

    body.screenshot(options);
  }

  private void compare(Path test, Path shot) {
    try {
      final String pathName;
      pathName = test.toString();

      final BufferedImage img1;
      img1 = ImageIO.read(test.toFile());

      final BufferedImage img2;
      img2 = ImageIO.read(shot.toFile());

      assertEquals(img1.getWidth(), img2.getWidth());

      assertEquals(img1.getHeight(), img2.getHeight());

      for (int y = 0; y < img1.getHeight(); y++) {
        for (int x = 0; x < img1.getWidth(); x++) {
          final int pixel1;
          pixel1 = img1.getRGB(x, y);

          final int pixel2;
          pixel2 = img2.getRGB(x, y);

          assertEquals(pixel1, pixel2, "x=%d,y=%d,pathName=%s".formatted(x, y, pathName));
        }
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final String title() {
    return page.title();
  }

  @Override
  public final void waitForFunction(String expression, Object arg) {
    page.waitForFunction(expression, arg);
  }

}
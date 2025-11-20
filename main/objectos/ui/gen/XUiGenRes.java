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
package objectos.ui.gen; // SED_REMOVE

import static java.lang.System.Logger.Level.INFO;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Request;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
final class XUiGenRes {

  private Browser browser;

  private Clock clock;

  private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  private Appendable logger;

  private Playwright playwright;

  public static void main(String[] args) {
    final XUiGenRes gen;
    gen = new XUiGenRes();

    gen.execute(args);
  }

  // ##################################################################
  // # BEGIN: Main
  // ##################################################################

  final void execute(String[] args) {
    final long startTime;
    startTime = System.currentTimeMillis();

    try {
      init();

      cds();

      c4p();

      plex();
    } finally {
      if (playwright != null) {
        playwright.close();
      }

      final long elapsed;
      elapsed = System.currentTimeMillis() - startTime;

      logInfo("Done in %ds".formatted(TimeUnit.MILLISECONDS.toSeconds(elapsed)));
    }
  }

  // ##################################################################
  // # END: Main
  // ##################################################################

  // ##################################################################
  // # BEGIN: Init
  // ##################################################################

  private void init() {
    if (clock == null) {
      clock = Clock.systemDefaultZone();
    }

    if (logger == null) {
      logger = System.out;
    }

    logInfo("Objectos UI carbon-test-resources");

    playwright = Playwright.create();

    final BrowserType chromium;
    chromium = playwright.chromium();

    final BrowserType.LaunchOptions launchOptions;
    launchOptions = new BrowserType.LaunchOptions().setHeadless(true);

    browser = chromium.launch(launchOptions);
  }

  // ##################################################################
  // # END: Init
  // ##################################################################

  private record Resource(String url, Path target) {
    final void write() {
      try {
        final URI uri;
        uri = URI.create(url);

        final URL _url;
        _url = uri.toURL();

        final Path parent;
        parent = target.getParent();

        Files.createDirectories(parent);

        try (InputStream in = _url.openStream()) {
          Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.printf("%s->%s%n", _url, target);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  // ##################################################################
  // # BEGIN: CDS
  // ##################################################################

  private void cds() {
    logInfo("cds");

    exec(
        "https://react.carbondesignsystem.com/",
        "id=components-button--default",
        Path.of("test-resources", "cds")
    );
  }

  // ##################################################################
  // # END: CDS
  // ##################################################################

  // ##################################################################
  // # BEGIN: C4P
  // ##################################################################

  private void c4p() {
    logInfo("c4p");

    exec(
        "https://ibm-products.carbondesignsystem.com/",
        "id=components-tearsheet--tearsheet",
        Path.of("test-resources", "c4p")
    );
  }

  // ##################################################################
  // # END: C4P
  // ##################################################################

  // ##################################################################
  // # BEGIN: IBM Plex
  // ##################################################################

  private void plex() {
    logInfo("IBM Plex");

    plex(
        "https://github.com/IBM/plex/releases/download/%40ibm%2Fplex-sans%401.1.0/ibm-plex-sans.zip",
        "ibm-plex-sans.zip"
    );
  }

  private void plex(String url, String fileName) {
    final Path target;
    target = Path.of("test-resources", "ibm-plex", fileName);

    final Resource resource;
    resource = new Resource(url, target);

    resource.write();
  }

  // ##################################################################
  // # END: IBM Plex
  // ##################################################################

  // ##################################################################
  // # BEGIN: I/O
  // ##################################################################

  private void exec(String baseurl, String id, Path basedir) {
    final List<Resource> resources;
    resources = new ArrayList<>();

    try (Page page = newPage()) {
      final Consumer<Request> listener;
      listener = req -> {
        final String url;
        url = req.url();

        int question;
        question = url.indexOf('?');

        final int endIndex;
        endIndex = question < 0 ? url.length() : question;

        final String fileName;
        fileName = url.substring(baseurl.length(), endIndex);

        resources.add(new Resource(url, basedir.resolve(fileName)));
      };

      page.onRequestFinished(listener);

      page.navigate(baseurl + "iframe.html?" + id);

      final Locator root;
      root = page.locator("#storybook-root");

      root.waitFor();
    }

    resources.stream().parallel().forEach(Resource::write);
  }

  private Page newPage() {
    final Page page;
    page = browser.newPage();

    page.setDefaultTimeout(TimeUnit.SECONDS.toMillis(5));

    return page;
  }

  // ##################################################################
  // # END: I/O
  // ##################################################################

  // ##################################################################
  // # BEGIN: Logging
  // ##################################################################

  private void logInfo(String message) {
    log0(INFO, message);
  }

  private void log0(System.Logger.Level level, String message) {
    try {
      final LocalDateTime now;
      now = LocalDateTime.now(clock);

      final String time;
      time = dateFormat.format(now);

      final String markerName;
      markerName = level.getName();

      final String log;
      log = String.format("%s %-5s %s%n", time, markerName, message);

      logger.append(log);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to log message", e);
    }
  }

  // ##################################################################
  // # END: Logging
  // ##################################################################

}
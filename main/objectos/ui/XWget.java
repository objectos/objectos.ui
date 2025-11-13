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
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

final class XWget {

  private final Duration connectTimeout;

  private final Duration requestTimeout;

  // for testing
  XWget() {
    connectTimeout = Duration.ofSeconds(10);

    requestTimeout = Duration.ofMinutes(1);
  }

  XWget(Duration connectTimeout, Duration requestTimeout) {
    this.connectTimeout = connectTimeout;

    this.requestTimeout = requestTimeout;
  }

  public final void downloadTo(String location, Path target) {
    final URI uri;
    uri = URI.create(location);

    final URL url;

    try {
      url = uri.toURL();
    } catch (MalformedURLException e) {
      throw new UncheckedIOException("Failed to obtain an URL from URI", e);
    }

    final URLConnection conn;

    try {
      conn = url.openConnection();
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to open an URL connection", e);
    }

    conn.setConnectTimeout((int) connectTimeout.toMillis());

    conn.setReadTimeout((int) requestTimeout.toMillis());

    try {
      conn.connect();
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to connect to the URL", e);
    }

    final Path parent;
    parent = target.getParent();

    try {
      Files.createDirectories(parent);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to create parent directories", e);
    }

    try (InputStream in = conn.getInputStream()) {
      Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to write to target file", e);
    }
  }

}
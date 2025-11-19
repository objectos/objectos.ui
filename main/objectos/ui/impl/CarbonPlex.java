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
package objectos.ui.impl;

import module java.base;
import module objectos.way;
import module objectos.ui;

public final class CarbonPlex extends CarbonPlexGenerated implements Plex {

  public static final class Builder implements Plex.Options {

    private String prefix = "/fonts";

    @Override
    public final void prefix(String value) {
      prefix = Objects.requireNonNull(value);
    }

    public final Plex build() {
      return new CarbonPlex(prefix);
    }

  }

  public CarbonPlex(String prefix) {
    super(prefix);
  }

  @Override
  public final void configure(Css.Library.Options opts) {
    sans(opts);

    opts.theme("""
    :root {
      --default-font-family: 'IBM Plex Sans', var(--font-sans, initial);
    }
    """);
  }

  @Override
  public final void configure(Web.Resources.Library.Options opts) {
    sans(opts);
  }

}

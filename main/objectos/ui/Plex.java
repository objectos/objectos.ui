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

import java.util.function.Consumer;
import objectos.ui.impl.CarbonPlex;
import objectos.way.Css;
import objectos.way.Web;

/// Provides the IBM Plex font family.
public sealed interface Plex
    extends Css.Library, Web.Resources.Library
    permits CarbonPlex {

  /// Configures the creation of a `Plex` instance.
  sealed interface Options permits CarbonPlex.Builder {

    /// Sets the path prefix on which to serve the font files.
    /// The prefix value must start with a '/' character and it must not end with a '/' character.
    ///
    /// @param value the path prefix
    void prefix(String value);

  }

  /// Creates a new `Plex` instance with the specified options.
  ///
  /// @param options allows for setting the options
  ///
  /// @return a new `Plex` instance
  static Plex create(Consumer<? super Options> options) {
    final CarbonPlex.Builder builder;
    builder = new CarbonPlex.Builder();

    options.accept(builder);

    return builder.build();
  }

}
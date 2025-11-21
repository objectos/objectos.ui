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

import module java.base;
import module objectos.way;
import objectos.ui.impl.UiHeader;
import objectos.ui.impl.UiHeaderName;
import objectos.ui.impl.UiHeaderSkipToContent;

/// The UI shell header is the foundation for navigating and orienting your user to the UI.
public sealed interface Header extends Html.Component permits UiHeader {

  /// Configures the creation of a header name.
  sealed interface Name permits UiHeaderName {

    void href(String value);

    void main(String value);

    void prefix(String value);

  }

  /// Configures the creation of a header "skip to content" link.
  sealed interface SkipToContent permits UiHeaderSkipToContent {

    void href(String value);

    void main(String value);

    void tabindex(int value);

  }

  /// Configures the creation of a header component.
  sealed interface Options permits UiHeader {

    /// Renders the header name with the specified options.
    /// @param name allows for setting the options
    void name(Consumer<? super Name> name);

    /// Renders a header "skip to content" link with the specified options.
    /// @param skip allows for setting the options
    void skipToContent(Consumer<? super SkipToContent> skip);

  }

  /// Creates a new header with the specified options.
  ///
  /// @param header allows for setting the options
  ///
  /// @return a newly created header with the specified options
  static Header create(Consumer<? super Options> header) {
    final UiHeader pojo;
    pojo = new UiHeader();

    header.accept(pojo);

    return pojo;
  }

}

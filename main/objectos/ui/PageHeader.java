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
import module objectos.ui;
import objectos.ui.impl.UiPageHeader;

/// Renders a page header made of three zones: the breadcrumb, content and tabs.
public sealed interface PageHeader extends Html.Component permits UiPageHeader {

  /// Configures the creation of a page header.
  sealed interface Options permits UiPageHeader {

    /// Adds a child component to this page header.
    /// @param value the child component
    void add(Html.Component value);

  }

  /// Creates a new page header with the specified options.
  /// @param header allows for setting the options
  /// @return a newly created page header with the specified options
  static PageHeader create(Consumer<? super Options> header) {
    final UiPageHeader pojo;
    pojo = new UiPageHeader();

    header.accept(pojo);

    return pojo;
  }

}

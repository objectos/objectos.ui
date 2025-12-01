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
import objectos.ui.impl.UiPageHeaderBreadcrumbBar;

/// Renders a `PageHeader` breadcrumb bar.
public sealed interface PageHeaderBreadcrumbBar extends Html.Component permits UiPageHeaderBreadcrumbBar {

  /// Configures the creation of a page header breadcrumb.
  sealed interface Options permits UiPageHeaderBreadcrumbBar {

    /// Adds a child component to this page header breadcrumb.
    /// @param value the child component
    void add(Html.Component value);

    /// Configures whether the breadcrumb bar is rendered with a bottom border or not.
    /// Defaults to `true`.
    /// @param value `true` to render the bar with a bottom border; `false` otherwise
    void border(boolean value);

  }

  /// Creates a new page header breadcrumb bar with the specified options.
  ///
  /// @param bar allows for setting the options
  ///
  /// @return a newly created page header breadcrumb bar with the specified options
  static PageHeaderBreadcrumbBar create(Consumer<? super Options> bar) {
    final UiPageHeaderBreadcrumbBar pojo;
    pojo = new UiPageHeaderBreadcrumbBar();

    bar.accept(pojo);

    return pojo;
  }

}

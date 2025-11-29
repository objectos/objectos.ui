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
import objectos.ui.impl.UiBreadcrumb;

/// The breadcrumb is a secondary navigation pattern that helps a user
/// understand the hierarchy among levels and navigate back through them.
public sealed interface Breadcrumb extends Html.Component permits UiBreadcrumb {

  /// Configures the creation of a breadcrumb.
  sealed interface Options permits UiBreadcrumb {

    /// Adds a child component to this breadcrumb.
    /// @param value the child component
    void add(Html.Component value);

    /// Sets the label for the breadcrumb container.
    /// @param value the label
    void ariaLabel(String value);

    /// Sets the size of this breadcrumb, defaults to `[MD][BreadcrumbSize#MD]` when not specified.
    /// @param value the size of this breadcrumb
    void size(BreadcrumbSize value);

  }

  /// Creates a new breadcrumb with the specified options.
  ///
  /// @param bc allows for setting the options
  ///
  /// @return a newly created breadcrumb with the specified options
  static Breadcrumb create(Consumer<? super Options> bc) {
    final UiBreadcrumb pojo;
    pojo = new UiBreadcrumb();

    bc.accept(pojo);

    return pojo;
  }

}

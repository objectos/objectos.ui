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
import objectos.ui.impl.UiBreadcrumbItem;

/// Renders an item in a breadcrumb.
public sealed interface BreadcrumbItem extends Html.Component permits UiBreadcrumbItem {

  /// Configures the creation of a breadcrumb item.
  sealed interface Options permits UiBreadcrumbItem {

    /// Sets the `href` attribute to the specified value.
    /// @param value the attribute value
    void href(String value);

    /// Sets the `id` attribute value of the outermost HTML element.
    /// @param value the `id` attribute value
    void id(Html.Id value);

    /// Sets the child component to be rendered in this item.
    /// @param value the child component
    void set(Html.Component value);

    /// Sets the child component of this breadcrumb item to .
    /// @param value the text node value
    void set(String value);

  }

  /// Creates a new breadcrumb item with the specified options.
  ///
  /// @param item allows for setting the options
  ///
  /// @return a newly created breadcrumb item with the specified options
  static BreadcrumbItem create(Consumer<? super Options> item) {
    final UiBreadcrumbItem pojo;
    pojo = new UiBreadcrumbItem();

    item.accept(pojo);

    return pojo;
  }

}

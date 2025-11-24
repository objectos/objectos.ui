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
import objectos.ui.impl.UiPage;

/// Renders the base page.
public sealed interface Page extends Html.Component permits UiPage {

  /// Configures the creation of a page component.
  sealed interface Options permits UiPage {

    /// Adds a child component to the end of the `<body>` element of this page.
    /// @param value the child component
    void add(Html.Component value);

    /// Applies the specified Objectos CSS to the `<body>` element of the page.
    /// @param value the Objectos CSS to apply
    void css(String value);

    /// Sets the component that will be applied to the end of the `<head>` section of this page.
    /// @param value the component to apply
    void head(Html.Component value);

    /// Sets the page theme.
    /// @param value the page theme
    void theme(Theme value);

    /// Sets the page title.
    /// @param value the page title
    void title(String value);

  }

  /// Creates a new page with the specified options.
  ///
  /// @param page allows for setting the options
  ///
  /// @return a newly created page with the specified options
  static Page create(Consumer<? super Options> page) {
    final UiPage pojo;
    pojo = new UiPage();

    page.accept(pojo);

    return pojo;
  }

}
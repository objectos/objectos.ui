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
import objectos.ui.impl.UiPageHeaderTitleRow;

/// Renders a `PageHeader` title row.
public sealed interface PageHeaderTitleRow extends Html.Component permits UiPageHeaderTitleRow {

  /// Configures the creation of a page header title row.
  sealed interface Options permits UiPageHeaderTitleRow {

    /// Adds a child component to this page header title row.
    /// @param value the child component
    void add(Html.Component value);

    /// Sets the title to be displayed in this row.
    /// @param value the title
    void title(String value);

  }

  /// Creates a new page header title row with the specified options.
  /// @param row allows for setting the options
  /// @return a newly created page header title row with the specified options
  static PageHeaderTitleRow create(Consumer<? super Options> row) {
    final UiPageHeaderTitleRow pojo;
    pojo = new UiPageHeaderTitleRow();

    row.accept(pojo);

    return pojo;
  }

}

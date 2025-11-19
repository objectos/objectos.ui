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

import objectos.ui.impl.CarbonFormGroup;
import module java.base;
import module objectos.way;

/// A group of related input controls.
public sealed interface FormGroup extends Html.Component permits CarbonFormGroup {

  /// Configures the creation of a form group.
  sealed interface Options extends FormBuilder permits CarbonFormGroup {

    /// Adds the specified component to the form group.
    /// @param value the component to add
    @Override
    void add(Html.Component value);

    /// Class name to be applied to the `fieldset` element.
    /// @param value the class name
    void css(String value);

    /// The text to be rendered inside of the `fieldset` element.
    /// @param value the text value
    void legendText(String value);

  }

  /// Creates a new form group with the specified options.
  ///
  /// @param group allows for setting the options
  ///
  /// @return a newly created form group with the specified options
  static FormGroup create(Consumer<? super Options> group) {
    final CarbonFormGroup pojo;
    pojo = new CarbonFormGroup();

    group.accept(pojo);

    return pojo;
  }

}
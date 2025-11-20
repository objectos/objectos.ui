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

import objectos.ui.impl.UiTextInput;
import module java.base;
import module objectos.way;

/// Text inputs enable users to enter free-form text data.
public sealed interface TextInput extends Html.Component permits UiTextInput {

  /// Configures the creation of a text input.
  sealed interface Options permits UiTextInput {

    /// Adds a script that causes the focus to be set on this `<input>` upon loading.
    void focus();

    /// Provide text that is used alongside the control label for additional help.
    /// @param value the helper text value
    void helperText(String value);

    /// Specify a custom `id` for the `<input>`.
    /// @param value the `id` value
    void id(Html.Id value);

    /// Sets this control in the invalid state and displays the specified value as the error message.
    /// @param value the error message to be displayed
    void invalidText(String value);

    /// Provide the text that will be read by a screen reader when visiting this control.
    /// @param value the label text value
    void labelText(String value);

    /// Specify the placeholder attribute for the `<input>`
    /// @param value the placeholder text value
    void placeholder(String value);

    /// Specify the value of the `<input>`.
    /// @param value the value of the `<input>`
    void value(String value);

  }

  /// Creates a new text input with the specified options.
  ///
  /// @param textInput allows for setting the options
  ///
  /// @return a newly created text input with the specified options
  static Html.Component create(Consumer<? super Options> textInput) {
    final UiTextInput pojo;
    pojo = new UiTextInput();

    textInput.accept(pojo);

    return pojo;
  }

}
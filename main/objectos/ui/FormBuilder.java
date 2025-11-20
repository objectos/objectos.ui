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
import objectos.way.Html;

sealed interface FormBuilder permits FormGroup.Options {

  void add(Html.Component value);

  /// Adds a text input with the specified options.
  /// @param textInput allows for setting the text input options
  default void textInput(Consumer<? super TextInput.Options> textInput) {
    add(TextInput.create(textInput));
  }

}
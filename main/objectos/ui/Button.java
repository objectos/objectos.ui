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
import objectos.ui.impl.CarbonButton;

/// Buttons are used to initialize an action.
/// Button labels express what action will occur when the user interacts with it.
public sealed interface Button extends Html.Component permits CarbonButton {

  /// Configures the creation of a button.
  sealed interface Options permits CarbonButton {

    /// Sets the `data-on-click` attribute value to the specified script.
    /// @param value the Objectos Script to execute
    void dataOnClick(Consumer<? super Script> value);

    /// Sets this button as expressive.
    void expressive();

    /// Sets the `id` attribute for the `<button>`.
    /// @param value the `id` attribute value
    void id(Html.Id value);

    /// Sets the kind of this button, defaults to `[PRIMARY][ButtonKind#PRIMARY]` when not specified.
    /// @param value the kind of this button
    void kind(ButtonKind value);

    /// Sets the size of this button, defaults to `[LG][ButtonSize#LG]` when not specified.
    /// @param value the size of this button
    void size(ButtonSize value);

    /// Sets the single text node to be rendered as a child of this button.
    /// @param value the text value
    void text(String value);

    /// Sets the type of this button, defaults to `[BUTTON][ButtonType#BUTTON]` when not specified.
    /// @param value the type of this button
    void type(ButtonType value);

  }

  /// Creates a new button with the specified options.
  ///
  /// @param btn allows for setting the options
  ///
  /// @return a newly created button with the specified options
  static Button create(Consumer<? super Options> btn) {
    final CarbonButton pojo;
    pojo = new CarbonButton();

    btn.accept(pojo);

    return pojo;
  }

}

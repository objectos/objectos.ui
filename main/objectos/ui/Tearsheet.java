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
import objectos.ui.impl.CarbonButton;
import objectos.ui.impl.CarbonTearsheet;

/// A tearsheet is a mostly full-screen type of dialog that keeps users
/// in-context and focused by bringing actionable content front and center
/// while revealing parts of the UI behind it.
public sealed interface Tearsheet extends Html.Component permits CarbonTearsheet {

  /// Configures the creation of a tearsheet.
  sealed interface Options permits CarbonTearsheet {

    /// Sets the tearsheet's action button.
    /// @param action the single action
    void actions(Consumer<? super Action> action);

    /// Sets the tearsheet's action buttons.
    /// @param action1 the first action
    /// @param action2 the second action
    void actions(Consumer<? super Action> action1, Consumer<? super Action> action2);

    /// Sets the tearsheet's action buttons.
    /// @param action1 the first action
    /// @param action2 the second action
    /// @param action3 the third action
    void actions(Consumer<? super Action> action1, Consumer<? super Action> action2, Consumer<? super Action> action3);

    /// A description of the flow, displayed in the header area of the tearsheet.
    /// @param value the tearsheet description
    void description(String value);

    /// Sets the `id` attribute for the `<dialog>` element.
    /// @param value the `id` attribute value
    void id(Html.Id value);

    /// The HTML component to be rendered as the main section of the tearsheet.
    /// @param value the HTML component
    void main(Html.Component value);

    /// Specifies whether the tearsheet is currently open.
    /// @param value `true` if the tearsheet is currently open; `false` otherwise
    void open(boolean value);

    /// The main title of the tearsheet, displayed in the header area.
    /// @param value the title value
    void title(String value);

  }

  /// Creates a new tearsheet with the specified options.
  ///
  /// @param tearsheet allows for setting the options
  ///
  /// @return a newly created tearsheet with the specified options
  static Tearsheet create(Consumer<? super Options> tearsheet) {
    final CarbonTearsheet pojo;
    pojo = new CarbonTearsheet();

    tearsheet.accept(pojo);

    return pojo;
  }

  /// Configures the creation of a tearsheet action.
  sealed interface Action permits CarbonButton {

    /// Sets the `data-on-click` attribute value to the specified script.
    /// @param value the Objectos Script to execute
    void dataOnClick(Consumer<? super Script> value);

    /// Sets the `id` attribute for the `<button>` element.
    /// @param value the `id` attribute value
    void id(Html.Id value);

    /// Sets the kind of this button, defaults to `[PRIMARY][#PRIMARY]` when not specified.
    /// @param value the kind of this button
    void kind(ButtonKind value);

    /// Sets the single text node to be rendered as a child of this button.
    /// @param value the text value
    void text(String value);

    /// Sets the type of this button, defaults to `[BUTTON][#BUTTON]` when not specified.
    /// @param value the type of this button
    void type(ButtonType value);

  }

  /// Returns a script that can close the tearsheet represented by the specified id.
  /// @param id the tearsheet `id` attribute
  /// @return a script that can close the tearsheet
  static Consumer<? super Script> closeScript(Html.Id id) {
    return CarbonTearsheet.closeImpl(id);
  }

  /// Returns a script that can open the tearsheet represented by the specified id.
  /// @param id the tearsheet `id` attribute
  /// @return a script that can open the tearsheet
  static Consumer<? super Script> openScript(Html.Id id) {
    return CarbonTearsheet.openImpl(id);
  }

}
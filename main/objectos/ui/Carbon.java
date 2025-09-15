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

import java.util.Locale;
import java.util.function.Consumer;
import objectos.ui.carbon.CarbonIcon;
import objectos.ui.carbon.CarbonLayer;
import objectos.ui.carbon.CarbonPage;
import objectos.ui.carbon.CarbonTearsheet;
import objectos.ui.carbon.CarbonTextInput;
import objectos.ui.carbon.CarbonTheme;
import objectos.way.Css;
import objectos.way.Html;

/// The **Objectos Carbon** main class.
/// Objectos Carbon is an implementation of IBM's Carbon Design System for Objectos Way applications.
public final class Carbon {

  private Carbon() {}

  // ##################################################################
  // # BEGIN: Icon
  // ##################################################################

  /// Configures the creation of an icon.
  public sealed interface Icon permits CarbonIcon {

    void css(String value);

    void size16();

    void size20();

    void size24();

    void size32();

    // START generated code

    void iconWarningAlt();

    void iconWarningFilled();

    // END generated code

  }

  public static Html.Component icon(Consumer<? super Icon> icon) {
    final CarbonIcon pojo;
    pojo = new CarbonIcon();

    icon.accept(pojo);

    return pojo;
  }

  // ##################################################################
  // # END: Icon
  // ##################################################################

  // ##################################################################
  // # BEGIN: Layer
  // ##################################################################

  /// Configures the creation of a layer.
  public sealed interface Layer permits CarbonLayer {

    /// Adds a child component to the end of this layer.
    ///
    /// @param value the child component
    void add(Html.Component value);

  }

  public static Html.Component layer(Consumer<? super Layer> layer) {
    final CarbonLayer pojo;
    pojo = new CarbonLayer();

    layer.accept(pojo);

    return pojo;
  }

  // ##################################################################
  // # END: Layer
  // ##################################################################

  // ##################################################################
  // # BEGIN: Page
  // ##################################################################

  public sealed interface Page permits CarbonPage {

    void add(Html.Component value);

    void bodyFrame(String name);

    void bodyFrame(String name, String value);

    void headEnd(Html.Component value);

    void theme(Theme value);

    void title(String value);

  }

  public static Html.Component page(Consumer<? super Page> page) {
    final CarbonPage pojo;
    pojo = new CarbonPage();

    page.accept(pojo);

    return pojo;
  }

  // ##################################################################
  // # END: Page
  // ##################################################################

  // ##################################################################
  // # BEGIN: Tearsheet
  // ##################################################################

  /// Configures the creation of a tearsheet.
  public sealed interface Tearsheet permits CarbonTearsheet {

    /// A description of the flow, displayed in the header area of the tearsheet.
    ///
    /// @param value the tearsheet description
    void description(String value);

    /// The HTML component to be rendered as the main section of the tearsheet.
    ///
    /// @param value the HTML component
    void main(Html.Component value);

    /// Specifies whether the tearsheet is currently open.
    ///
    /// @param value `true` if the tearsheet is currently open; `false` otherwise
    void open(boolean value);

    /// The main title of the tearsheet, displayed in the header area.
    ///
    /// @param value the title value
    void title(String value);

  }

  /// Creates a new tearsheet with the specified options.
  ///
  /// A tearsheet is a mostly full-screen type of dialog that keeps users
  /// in-context and focused by bringing actionable content front and center
  /// while revealing parts of the UI behind it.
  ///
  /// @param tearsheet allows for setting the options
  ///
  /// @return a newly created tearsheet with the specified options
  public static Html.Component tearsheet(Consumer<? super Tearsheet> tearsheet) {
    final CarbonTearsheet pojo;
    pojo = new CarbonTearsheet();

    tearsheet.accept(pojo);

    return pojo;
  }

  // ##################################################################
  // # END: Tearsheet
  // ##################################################################

  // ##################################################################
  // # BEGIN: TextInput
  // ##################################################################

  /// Configures the creation of a text input.
  public sealed interface TextInput permits CarbonTextInput {

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
  /// Text inputs enable users to enter free-form text data.
  ///
  /// @param textInput allows for setting the options
  ///
  /// @return a newly created text input with the specified options
  public static Html.Component textInput(Consumer<? super TextInput> textInput) {
    final CarbonTextInput pojo;
    pojo = new CarbonTextInput();

    textInput.accept(pojo);

    return pojo;
  }

  // ##################################################################
  // # END: TextInput
  // ##################################################################

  // ##################################################################
  // # BEGIN: Theme
  // ##################################################################

  public sealed interface Theme permits CarbonTheme {

    Theme WHITE = CarbonTheme.WHITE;

    Theme G10 = CarbonTheme.G10;

    Theme G90 = CarbonTheme.G90;

    Theme G100 = CarbonTheme.G100;

    static Theme of(String name) {
      final String upper;
      upper = name.toUpperCase(Locale.US);

      return CarbonTheme.valueOf(upper);
    }

  }

  // ##################################################################
  // # END: Theme
  // ##################################################################

  public static void configureStyleSheet(Css.StyleSheet.Options opts) {
    final CarbonStyles sheet;
    sheet = new CarbonStyles();

    sheet.accept(opts);
  }

}
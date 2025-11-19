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
import objectos.ui.impl.CarbonButton;
import objectos.ui.impl.CarbonTearsheet;
import objectos.ui.impl.CarbonTextInput;
import objectos.way.Css;
import objectos.way.Html;
import objectos.way.Script;

/// The **Objectos Carbon** main class.
/// Objectos Carbon is an implementation of IBM's Carbon Design System for Objectos Way applications.
public final class Carbon {

  private Carbon() {}

  // ##################################################################
  // # BEGIN: Page
  // ##################################################################

  /// Configures the creation of a Carbon UI page component.
  public sealed interface Page permits CarbonPage {

    /// Applies the specified Objectos CSS to the `<body>` element of the page.
    /// @param value the Objectos CSS to apply
    void css(String value);

    /// Sets the component that will be applied to the end of the `<head>` section of this page.
    /// @param value the component to apply
    void head(Html.Component value);

    /// Sets the components of the main section of this page, i.e., the `<body>` element.
    /// @param elements the main section components
    void main(Html.Component... elements);

    /// Sets the page Carbon theme.
    /// @param value the page theme
    void theme(Theme value);

    /// Sets the page title.
    /// @param value the page title
    void title(String value);

  }

  /// Creates a new Carbon page with the specified options.
  ///
  /// @param page allows for setting the options
  ///
  /// @return a newly created page with the specified options
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
  // # BEGIN: Spacing
  // ##################################################################

  /// Represents a spacing token.
  public sealed interface Spacing permits CarbonSpacing {}

  /// The zero value spacing.
  public static final Spacing SPACING_00 = CarbonSpacing.SPACING_00;

  /// The `$spacing-01` token.
  public static final Spacing SPACING_01 = CarbonSpacing.SPACING_01;

  /// The `$spacing-02` token.
  public static final Spacing SPACING_02 = CarbonSpacing.SPACING_02;

  /// The `$spacing-03` token.
  public static final Spacing SPACING_03 = CarbonSpacing.SPACING_03;

  /// The `$spacing-04` token.
  public static final Spacing SPACING_04 = CarbonSpacing.SPACING_04;

  /// The `$spacing-05` token.
  public static final Spacing SPACING_05 = CarbonSpacing.SPACING_05;

  /// The `$spacing-06` token.
  public static final Spacing SPACING_06 = CarbonSpacing.SPACING_06;

  /// The `$spacing-07` token.
  public static final Spacing SPACING_07 = CarbonSpacing.SPACING_07;

  /// The `$spacing-08` token.
  public static final Spacing SPACING_08 = CarbonSpacing.SPACING_08;

  /// The `$spacing-09` token.
  public static final Spacing SPACING_09 = CarbonSpacing.SPACING_09;

  /// The `$spacing-10` token.
  public static final Spacing SPACING_10 = CarbonSpacing.SPACING_10;

  /// The `$spacing-11` token.
  public static final Spacing SPACING_11 = CarbonSpacing.SPACING_11;

  /// The `$spacing-12` token.
  public static final Spacing SPACING_12 = CarbonSpacing.SPACING_12;

  /// The `$spacing-13` token.
  public static final Spacing SPACING_13 = CarbonSpacing.SPACING_13;

  // ##################################################################
  // # END: Spacing
  // ##################################################################

  // ##################################################################
  // # BEGIN: Stack
  // ##################################################################

  /// Configures the creation of a stack.
  public sealed interface Stack permits CarbonStack {

    /// Sets the gap to the specified spacing.
    /// @param value the spacing to used as gap
    void gap(Spacing value);

    /// Sets the components of the main section of this stack.
    /// @param elements the main section components
    void main(Html.Component... elements);

  }

  /// Creates a new stack with the specified options.
  ///
  /// @param stack allows for setting the options
  ///
  /// @return a newly created stack with the specified options
  public static Html.Component stack(Consumer<? super Stack> stack) {
    final CarbonStack pojo;
    pojo = new CarbonStack();

    stack.accept(pojo);

    return pojo;
  }

  /// Creates a new stack with the specified gap and elements.
  ///
  /// @param gap the spacing to be used as gap
  /// @param elements the components of the main section of the stack
  ///
  /// @return a newly created stack with the specified options
  public static Html.Component stack(Spacing gap, Html.Component... elements) {
    return stack(s -> {
      s.gap(gap);

      s.main(elements);
    });
  }

  // ##################################################################
  // # END: Stack
  // ##################################################################

  // ##################################################################
  // # BEGIN: Styles
  // ##################################################################

  /// Provides the Objectos Carbon `Css.StyleSheet` configuration.
  public sealed interface Styles extends Css.Library permits CarbonStyles {

    /// Creates a new `Styles` instance with the default options.
    ///
    /// @return a new `Styles` instance.
    static Styles create() {
      return new CarbonStyles();
    }

  }

  // ##################################################################
  // # END: Styles
  // ##################################################################

  // ##################################################################
  // # BEGIN: Tearsheet
  // ##################################################################

  /// Configures the creation of a tearsheet.
  public sealed interface Tearsheet permits CarbonTearsheet {

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

    static Consumer<? super Script> close(Html.Id id) {
      return CarbonTearsheet.closeImpl(id);
    }

    static Consumer<? super Script> openAction(Html.Id id) {
      return CarbonTearsheet.openImpl(id);
    }

    void actions(Consumer<? super Action> action);

    void actions(Consumer<? super Action> action1, Consumer<? super Action> action2);

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

}
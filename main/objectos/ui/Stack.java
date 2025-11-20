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
import objectos.ui.impl.UiStack;

/// The `Stack` component allows components to not use margin
/// and instead delegate the responsibility of positioning and layout to parent components.
public sealed interface Stack extends Html.Component permits UiStack {

  /// Configures the creation of a stack.
  sealed interface Options permits UiStack {

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
  static Stack create(Consumer<? super Options> stack) {
    final UiStack pojo;
    pojo = new UiStack();

    stack.accept(pojo);

    return pojo;
  }

  /// Creates a new stack with the specified gap and elements.
  ///
  /// @param gap the spacing to be used as gap
  /// @param elements the components of the main section of the stack
  ///
  /// @return a newly created stack with the specified options
  static Stack of(Spacing gap, Html.Component... elements) {
    return create(s -> {
      s.gap(gap);

      s.main(elements);
    });
  }

}
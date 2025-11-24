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
import objectos.ui.impl.UiVertical;
import objectos.way.Html.Component;

/// The `Vertical` component place child components vertically in a column.
public sealed interface Vertical extends Html.Component permits UiVertical {

  /// Configures the creation of a vertical component.
  sealed interface Options permits UiVertical {

    /// Adds a child component to the end of this vertical component.
    /// @param value the child component
    void add(Html.Component value);

    /// Renders this vertical component as the specified HTML element.
    /// @param value the HTML element name
    void as(Html.ElementName value);

    /// Sets the gap to the specified spacing.
    /// @param value the spacing to used as gap
    void gap(Spacing value);

  }

  /// Creates a new vertical component with the specified options.
  ///
  /// @param vert allows for setting the options
  ///
  /// @return a newly created vertical component with the specified options
  static Vertical create(Consumer<? super Options> vert) {
    final UiVertical pojo;
    pojo = new UiVertical();

    vert.accept(pojo);

    return pojo;
  }

  /// Returns the Objectos CSS for a vertical component with the specified gap.
  /// @param gap the spacing to be used as gap
  /// @param more additional CSS class names appended to the result
  /// @return the Objectos CSS
  static String cssOf(Spacing gap, String... more) {
    return UiVertical.cssOf0(gap, more);
  }

  /// Creates a new vertical component with the specified gap and children.
  ///
  /// @param gap the spacing to be used as gap
  /// @param children the child components of this vertical component
  ///
  /// @return a newly created vertical component with the specified options
  static Vertical of(Spacing gap, Html.Component... children) {
    final Spacing g;
    g = Objects.requireNonNull(gap, "gap == null");

    final List<Component> main;
    main = List.of(children);

    return new UiVertical(g, main);
  }

}
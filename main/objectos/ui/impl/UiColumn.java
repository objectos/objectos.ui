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
package objectos.ui.impl;

import module java.base;
import module objectos.ui;

public final class UiColumn extends UiComponent implements Column, Column.Options {

  private final Html.ElementName as = Html.ElementName.DIV;

  private String css;

  private List<Html.Component> main = EMPTY_MAIN;

  private Map<UiBreakpoint, UiColumnProps> props;

  @Override
  public final void add(Html.Component value) {
    main = add(main, value);
  }

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void props(Consumer<? super Props> props) {
    props0(UiBreakpoint.XS, props);
  }

  @Override
  public final void props(Breakpoint breakpoint, Consumer<? super Props> props) {
    final UiBreakpoint impl;
    impl = (UiBreakpoint) breakpoint;

    props0(impl, props);
  }

  private void props0(UiBreakpoint breakpoint, Consumer<? super Props> opts) {
    final UiColumnProps pojo;
    pojo = new UiColumnProps(breakpoint);

    opts.accept(pojo);

    if (props == null) {
      props = new EnumMap<>(UiBreakpoint.class);
    }

    props.put(breakpoint, pojo);
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        m.css("""
        --grid-mode-start:var(--grid-gutter-start)
        --grid-mode-end:var(--grid-gutter-end)
        margin-inline:var(--grid-gutter-start)_var(--grid-gutter-end)
        """),

        css != null ? m.css(css) : m.noop(),

        props != null ? m.flatten(props.values()) : m.noop(),

        m.c(main)
    );
  }

}

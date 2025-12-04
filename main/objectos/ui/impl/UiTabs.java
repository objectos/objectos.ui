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
import java.util.ArrayList;
import module objectos.ui;

public final class UiTabs extends UiComponent
    implements Tabs, Tabs.Options {

  private int selectedIndex = 0;

  private List<UiTabsTab> tabs = List.of();

  @Override
  public final void selectedIndex(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("The selectedIndex value must not be negative");
    }

    selectedIndex = value;
  }

  @Override
  public final void tab(Consumer<? super Tab> tab) {
    final UiTabsTab pojo;
    pojo = new UiTabsTab();

    tab.accept(pojo);

    if (tabs.isEmpty()) {
      tabs = new ArrayList<>();
    }

    tabs.add(pojo);
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.div(
        m.css("""
        block-size:var(--spacing-08)
        display:flex
        inline-size:100%
        overflow-x:hidden
        """),

        // tab--list
        m.div(
            m.css("""
            display:flex
            inline-size:auto
            overflow-x:auto
            scroll-behavior:smooth
            scrollbar-width:none
            will-change:scroll-position
            """),

            m.f(this::tabs, m)
        )
    );
  }

  private void tabs(Html.Markup m) {
    final int selected;

    final int size;
    size = tabs.size();

    if (selectedIndex < size) {
      selected = selectedIndex;
    } else {
      selected = size - 1;
    }

    for (int idx = 0; idx < size; idx++) {
      final UiTabsTab tab;
      tab = tabs.get(idx);

      if (idx == selected) {
        tab.selected = true;
      } else {
        tab.selected = false;
      }

      m.c(tab);
    }
  }

}

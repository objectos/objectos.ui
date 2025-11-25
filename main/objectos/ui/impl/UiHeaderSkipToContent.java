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

import java.util.Objects;
import module objectos.ui;

public final class UiHeaderSkipToContent extends UiComponent implements Header.SkipToContent {

  private String href = "#main-content";

  private Html.Component main = m -> m.text("Skip to main content");

  private String tabindex = "0";

  @Override
  public final void href(String value) {
    href = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void set(String value) {
    final String t;
    t = Objects.requireNonNull(value, "value == null");

    main = m -> m.text(t);
  }

  @Override
  public final void tabindex(int value) {
    if (value > 32767) {
      throw new IllegalArgumentException("The maximum value for tabindex is 32767");
    }

    tabindex = Integer.toString(value);
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.a(
        m.css("""
        block-size:1px
        clip:rect(0,0,0,0)
        inline-size:1px
        margin:-1px
        overflow:hidden
        position:absolute
        text-decoration:underline
        visibility:inherit
        white-space:nowrap

        focus/align-items:center
        focus/display:flex
        focus/border:4px_solid_var(--color-focus)
        focus/background-color:var(--color-background)
        focus/block-size:48rx
        focus/clip:auto
        focus/color:var(--color-text-secondary)
        focus/inline-size:auto
        focus/inset-block-start:0
        focus/inset-inline-start:0
        focus/outline:none
        focus/padding:0_16rx
        focus/z-index:9999
        """),

        m.href(href),

        m.tabindex(tabindex),

        m.c(main)
    );
  }

}

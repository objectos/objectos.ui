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

public final class UiHeader extends UiComponent implements Header, Header.Options {

  private UiHeaderName name;

  private UiHeaderSkipToContent skipToContent;

  @Override
  public final void name(Consumer<? super Header.Name> name) {
    final UiHeaderName pojo;
    pojo = new UiHeaderName();

    name.accept(pojo);

    this.name = pojo;
  }

  @Override
  public final void skipToContent(Consumer<? super Header.SkipToContent> skip) {
    final UiHeaderSkipToContent pojo;
    pojo = new UiHeaderSkipToContent();

    skip.accept(pojo);

    skipToContent = pojo;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.header(
        m.css("""
        align-items:center
        background-color:var(--color-background)
        block-size:48rx
        border-block-end:1px_solid_var(--color-border-subtle)
        -webkit-border-after:1px_solid_var(--color-border-subtle)
        display:flex
        inset-block-start:0
        inset-inline:0
        position:fixed
        z-index:8000
        """),

        skipToContent != null ? m.c(skipToContent) : m.noop(),

        name != null ? m.c(name) : m.noop()
    );
  }

}

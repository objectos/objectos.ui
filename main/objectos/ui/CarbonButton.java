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
import java.util.Objects;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
final class CarbonButton extends CarbonComponent implements Carbon.Button {

  enum Kind {

    PRIMARY;

  }

  enum Size implements Carbon.Button.Size {

    SM,

    MD,

    LG,

    XL,

    X2L;

  }

  enum Type implements Carbon.Button.Type {

    BUTTON,

    RESET,

    SUBMIT;

    final String value = name().toLowerCase(Locale.US);

  }

  private final Html.ElementName as = Html.ElementName.BUTTON;

  private Html.Id id;

  private final Kind kind = Kind.PRIMARY;

  private CarbonButton.Size size = CarbonButton.Size.LG;

  private String text;

  private Type type = Type.BUTTON;

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void size(Carbon.Button.Size value) {
    size = (CarbonButton.Size) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void text(String value) {
    text = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void type(Carbon.Button.Type value) {
    type = (Type) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        m.css("""
        cursor:pointer
        display:inline-flex
        flex-shrink:0
        font-size:var(--carbon-body-compact-01-font-size,0.875rem)
        font-weight:var(--carbon-body-compact-01-font-weight,400)
        inline-size:max-content
        justify-content:space-between
        letter-spacing:var(--carbon-body-compact-01-letter-spacing,0.16px)
        line-height:var(--carbon-body-compact-01-line-height,1.28572)
        max-inline-size:20rem
        outline:none
        padding-inline:15rx_63rx
        position:relative
        text-decoration:none
        text-align:start
        transition:background_70ms_cubic-bezier(0,0,0.38,0.9),box-shadow_70ms_cubic-bezier(0,0,0.38,0.9),border-color_70ms_cubic-bezier(0,0,0.38,0.9),outline_70ms_cubic-bezier(0,0,0.38,0.9)
        vertical-align:top
        """),

        // kind
        m.css(switch (kind) {
          case PRIMARY -> """
          background-color:button-primary
          border:1px_solid_rgba(0,0,0,0)
          color:text-on-color

          active:background-color:button-primary-active
          focus:border-color:focus
          focus:box-shadow:inset_0_0_0_1px_focus,inset_0_0_0_2px_background
          hover:background-color:button-primary-hover
          """;
        }),

        // size
        m.css(switch (size) {
          case SM -> """
          min-block-size:32rx
          padding-block:6rx
          """;

          case MD -> """
          min-block-size:40rx
          padding-block:10rx
          """;

          default -> """
          min-block-size:48rx
          padding-block:14rx
          """;

          case XL -> """
          min-block-size:64rx
          padding-block:14rx
          """;

          case X2L -> """
          min-block-size:80rx
          padding-block:14rx
          """;
        }),

        id != null ? m.attr(id) : m.noop(),

        m.type(type.value),

        text != null ? m.text(text) : m.noop()
    );
  }

}

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
import java.util.function.Consumer;
import objectos.ui.Button;
import objectos.ui.Carbon;
import objectos.ui.ButtonKind;
import objectos.ui.ButtonSize;
import objectos.ui.ButtonType;
import objectos.way.Html;
import objectos.way.Script;

public final class CarbonButton extends CarbonComponent implements Button, Button.Options, Carbon.Tearsheet.Action {

  static final Html.ClassName BASE = Html.ClassName.ofText("""
  cursor:pointer
  display:inline-flex
  justify-content:space-between
  max-inline-size:20rem
  outline:none
  padding-inline:15rx_63rx
  position:relative
  text-decoration:none
  text-align:start
  transition:background_70ms_cubic-bezier(0,0,0.38,0.9),box-shadow_70ms_cubic-bezier(0,0,0.38,0.9),border-color_70ms_cubic-bezier(0,0,0.38,0.9),outline_70ms_cubic-bezier(0,0,0.38,0.9)
  vertical-align:top
  """);

  static final String PRODUCTIVE = """
  flex-shrink:0
  font-size:var(--type-body-compact-01-font-size)
  font-weight:var(--type-body-compact-01-font-weight)
  inline-size:max-content
  letter-spacing:var(--type-body-compact-01-letter-spacing)
  line-height:var(--type-body-compact-01-line-height)
  """;

  static final String EXPRESSIVE = """
  flex-shrink:0
  font-size:var(--type-body-compact-02-font-size)
  font-weight:var(--type-body-compact-02-font-weight)
  inline-size:max-content
  letter-spacing:var(--type-body-compact-02-letter-spacing)
  line-height:var(--type-body-compact-02-line-height)
  """;

  private final Html.ElementName as = Html.ElementName.BUTTON;

  private Consumer<? super Script> dataOnClick;

  private Html.Instruction id = Html.Instruction.noop();

  private CarbonButtonKind kind = CarbonButtonKind.PRIMARY;

  private CarbonButtonSize size = CarbonButtonSize.LG;

  private Html.AttributeObject style = CarbonButtonSize.NOOP;

  private String text;

  private Html.AttributeObject type = CarbonButtonType.BUTTON;

  @Override
  public final void dataOnClick(Consumer<? super Script> value) {
    dataOnClick = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void expressive() {
    size = size.expressive();
  }

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void kind(ButtonKind value) {
    kind = (CarbonButtonKind) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void size(ButtonSize value) {
    size = (CarbonButtonSize) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void text(String value) {
    text = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void type(ButtonType value) {
    type = (CarbonButtonType) Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.elem(
        as,

        // class attributes must be together
        BASE, kind, size, style,

        dataOnClick != null ? m.dataOnClick(dataOnClick) : m.noop(),

        id,

        type,

        text != null ? m.text(text) : m.noop()
    );
  }

  final boolean isGhost() {
    return kind.isGhost();
  }

  final void internalStyle(Html.AttributeObject value) {
    style = value;
  }

}

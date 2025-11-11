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
import java.util.function.Consumer;
import objectos.way.Css;
import objectos.way.Html;
import objectos.way.Script;

final class CarbonButton extends CarbonComponent implements Carbon.Button, Carbon.Tearsheet.Action {

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

  @Css.Source
  enum Kind implements Carbon.Button.Kind, Html.AttributeObject {

    PRIMARY("""
    background-color:var(--color-button-primary)
    border:1px_solid_rgba(0,0,0,0)
    color:var(--color-text-on-color)

    active/background-color:var(--color-button-primary-active)
    focus/border-color:var(--color-focus)
    focus/box-shadow:inset_0_0_0_1px_var(--color-focus),inset_0_0_0_2px_var(--color-background)
    hover/background-color:var(--color-button-primary-hover)
    """),

    SECONDARY("""
    background-color:var(--color-button-secondary)
    border:1px_solid_rgba(0,0,0,0)
    color:var(--color-text-on-color)

    active/background-color:var(--color-button-secondary-active)
    focus/border-color:var(--color-focus)
    focus/box-shadow:inset_0_0_0_1px_var(--color-focus),inset_0_0_0_2px_var(--color-background)
    hover/background-color:var(--color-button-secondary-hover)
    """),

    GHOST("""
    background-color:rgba(0,0,0,0)
    border:1px_solid_rgba(0,0,0,0)
    color:var(--color-link-primary)
    padding-inline-end:15rx

    active/background-color:var(--color-background-active)
    focus/border-color:var(--color-focus)
    focus/box-shadow:inset_0_0_0_1px_var(--color-focus),inset_0_0_0_2px_var(--color-background)
    hover/background-color:var(--color-background-hover)
    """);

    private final String attrValue;

    private Kind(String css) {
      attrValue = Html.formatAttrValue(css);
    }

    @Override
    public final Html.AttributeName attrName() {
      return Html.AttributeName.CLASS;
    }

    @Override
    public final String attrValue() {
      return attrValue;
    }

    final boolean isGhost() {
      return this == GHOST;
    }

  }

  private static final String PRODUCTIVE = """
  flex-shrink:0
  font-size:var(--type-body-compact-01-font-size)
  font-weight:var(--type-body-compact-01-font-weight)
  inline-size:max-content
  letter-spacing:var(--type-body-compact-01-letter-spacing)
  line-height:var(--type-body-compact-01-line-height)
  """;

  private static final String EXPRESSIVE = """
  flex-shrink:0
  font-size:var(--type-body-compact-02-font-size)
  font-weight:var(--type-body-compact-02-font-weight)
  inline-size:max-content
  letter-spacing:var(--type-body-compact-02-letter-spacing)
  line-height:var(--type-body-compact-02-line-height)
  """;

  @Css.Source
  enum Size implements Carbon.Button.Size, Html.AttributeObject {

    NOOP(""),

    SM(PRODUCTIVE, """
    min-block-size:32rx
    padding-block:6rx
    """),

    SM_EXPRESIVE(EXPRESSIVE, """
    min-block-size:32rx
    padding-block:4rx
    """),

    MD(PRODUCTIVE, """
    min-block-size:40rx
    padding-block:10rx
    """),

    MD_EXPRESIVE(EXPRESSIVE, """
    min-block-size:40rx
    padding-block:8rx
    """),

    LG(PRODUCTIVE, """
    min-block-size:48rx
    padding-block:14rx
    """),

    LG_EXPRESIVE(EXPRESSIVE, """
    min-block-size:48rx
    padding-block:12rx
    """),

    XL(PRODUCTIVE, """
    min-block-size:64rx
    padding-block:14rx
    """),

    XL_EXPRESIVE(EXPRESSIVE, """
    min-block-size:64rx
    padding-block:12.7rx
    """),

    X2L(PRODUCTIVE, """
    min-block-size:80rx
    padding-block:14rx
    """),

    X2L_EXPRESIVE(EXPRESSIVE, """
    min-block-size:80rx
    padding-block:12.7rx
    """);

    private final String attrValue;

    private Size(String attrValue) {
      this.attrValue = attrValue;
    }

    private Size(String fonts, String css) {
      attrValue = Html.formatAttrValue(fonts + css);
    }

    @Override
    public final Html.AttributeName attrName() {
      return Html.AttributeName.CLASS;
    }

    @Override
    public final String attrValue() {
      return attrValue;
    }

    final Size expressive() {
      return switch (this) {
        case SM -> SM_EXPRESIVE;
        case MD -> MD_EXPRESIVE;
        case LG -> LG_EXPRESIVE;
        case XL -> XL_EXPRESIVE;
        case X2L -> X2L_EXPRESIVE;
        default -> this;
      };
    }

  }

  enum Type implements Carbon.Button.Type, Html.AttributeObject {

    BUTTON,

    RESET,

    SUBMIT;

    private final String value = name().toLowerCase(Locale.US);

    @Override
    public final Html.AttributeName attrName() {
      return Html.AttributeName.TYPE;
    }

    @Override
    public final String attrValue() {
      return value;
    }

  }

  private final Html.ElementName as = Html.ElementName.BUTTON;

  private Consumer<? super Script> dataOnClick;

  private Html.Instruction id = Html.Instruction.noop();

  private CarbonButton.Kind kind = Kind.PRIMARY;

  private CarbonButton.Size size = CarbonButton.Size.LG;

  private Html.AttributeObject style = Size.NOOP;

  private String text;

  private Html.AttributeObject type = Type.BUTTON;

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
  public final void kind(Carbon.Button.Kind value) {
    kind = (Kind) Objects.requireNonNull(value, "value == null");
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

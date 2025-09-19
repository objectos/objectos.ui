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
final class CarbonButton extends CarbonComponent implements Carbon.Button, Carbon.Tearsheet.Action {

  static final Html.ClassName BASE = Html.ClassName.ofText("""
  cursor:pointer
  display:inline-flex
  flex-shrink:0
  inline-size:max-content
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
    background-color:button-primary
    border:1px_solid_rgba(0,0,0,0)
    color:text-on-color

    active:background-color:button-primary-active
    focus:border-color:focus
    focus:box-shadow:inset_0_0_0_1px_focus,inset_0_0_0_2px_background
    hover:background-color:button-primary-hover
    """),

    SECONDARY("""
    background-color:button-secondary
    border:1px_solid_rgba(0,0,0,0)
    color:text-on-color

    active:background-color:button-secondary-active
    focus:border-color:focus
    focus:box-shadow:inset_0_0_0_1px_focus,inset_0_0_0_2px_background
    hover:background-color:button-secondary-hover
    """),

    GHOST("""
    background-color:rgba(0,0,0,0)
    border:1px_solid_rgba(0,0,0,0)
    color:link-primary
    padding-inline-end:15rx

    active:background-color:background-active
    focus:border-color:focus
    focus:box-shadow:inset_0_0_0_1px_focus,inset_0_0_0_2px_background
    hover:background-color:background-hover
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

  }

  private static final String PRODUCTIVE = """
  font-size:var(--carbon-body-compact-01-font-size,0.875rem)
  font-weight:var(--carbon-body-compact-01-font-weight,400)
  letter-spacing:var(--carbon-body-compact-01-letter-spacing,0.16px)
  line-height:var(--carbon-body-compact-01-line-height,1.28572)
  """;

  private static final String EXPRESSIVE = """
  font-size:var(--carbon-body-compact-02-font-size,1rem)
  font-weight:var(--carbon-body-compact-02-font-weight,400)
  letter-spacing:var(--carbon-body-compact-02-letter-spacing,0)
  line-height:var(--carbon-body-compact-02-line-height,1.375)
  """;

  @Css.Source
  enum Size implements Carbon.Button.Size, Html.AttributeObject {

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

  private Html.Instruction id = Html.Instruction.noop();

  private Html.AttributeObject kind = Kind.PRIMARY;

  private CarbonButton.Size size = CarbonButton.Size.LG;

  private String text;

  private Html.AttributeObject type = Type.BUTTON;

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

        // class attributes should be together
        BASE, kind, size,

        id,

        type,

        text != null ? m.text(text) : m.noop()
    );
  }

}

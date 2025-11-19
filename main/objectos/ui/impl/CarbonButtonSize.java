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

import objectos.ui.ButtonSize;
import objectos.way.Html;

public enum CarbonButtonSize implements ButtonSize, Html.AttributeObject {

  NOOP(""),

  SM(CarbonButton.PRODUCTIVE, """
  min-block-size:32rx
  padding-block:6rx
  """),

  SM_EXPRESIVE(CarbonButton.EXPRESSIVE, """
  min-block-size:32rx
  padding-block:4rx
  """),

  MD(CarbonButton.PRODUCTIVE, """
  min-block-size:40rx
  padding-block:10rx
  """),

  MD_EXPRESIVE(CarbonButton.EXPRESSIVE, """
  min-block-size:40rx
  padding-block:8rx
  """),

  LG(CarbonButton.PRODUCTIVE, """
  min-block-size:48rx
  padding-block:14rx
  """),

  LG_EXPRESIVE(CarbonButton.EXPRESSIVE, """
  min-block-size:48rx
  padding-block:12rx
  """),

  XL(CarbonButton.PRODUCTIVE, """
  min-block-size:64rx
  padding-block:14rx
  """),

  XL_EXPRESIVE(CarbonButton.EXPRESSIVE, """
  min-block-size:64rx
  padding-block:12.7rx
  """),

  X2L(CarbonButton.PRODUCTIVE, """
  min-block-size:80rx
  padding-block:14rx
  """),

  X2L_EXPRESIVE(CarbonButton.EXPRESSIVE, """
  min-block-size:80rx
  padding-block:12.7rx
  """);

  private final String attrValue;

  private CarbonButtonSize(String attrValue) {
    this.attrValue = attrValue;
  }

  private CarbonButtonSize(String fonts, String css) {
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

  final CarbonButtonSize expressive() {
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
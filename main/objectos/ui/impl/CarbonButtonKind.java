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

import objectos.ui.ButtonKind;
import objectos.way.Html;

public enum CarbonButtonKind implements ButtonKind, Html.AttributeObject {

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

  private CarbonButtonKind(String css) {
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
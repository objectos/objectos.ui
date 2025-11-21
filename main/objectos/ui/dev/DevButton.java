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
package objectos.ui.dev;

import module objectos.ui;

public final class DevButton extends AbstractDevUi {

  public static final Html.Id BTN = Html.Id.of("btn");
  public static final Html.Id BTN_SM = Html.Id.of("btn-sm");
  public static final Html.Id BTN_MD = Html.Id.of("btn-md");
  public static final Html.Id BTN_XL = Html.Id.of("btn-xl");
  public static final Html.Id BTN_X2L = Html.Id.of("btn-x2l");

  DevButton() {
    super("button");
  }

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Button - Default",

          button(ButtonKind.PRIMARY)
      );

      case "secondary" -> ok(
          http,

          "Button - Secondary",

          button(ButtonKind.SECONDARY)
      );

      case "ghost" -> ok(
          http,

          "Button - Ghost",

          button(ButtonKind.GHOST)
      );
    }
  }

  private Html.Component button(ButtonKind kind) {
    return Stack.of(
        Spacing.SPACING_04,

        Button.create(b -> {
          b.id(BTN);
          b.kind(kind);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_SM);
          b.kind(kind);
          b.size(ButtonSize.SM);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_MD);
          b.kind(kind);
          b.size(ButtonSize.MD);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_XL);
          b.kind(kind);
          b.size(ButtonSize.XL);
          b.text("Button");
        }),

        Button.create(b -> {
          b.id(BTN_X2L);
          b.kind(kind);
          b.size(ButtonSize.X2);
          b.text("Button");
        })
    );
  }

}

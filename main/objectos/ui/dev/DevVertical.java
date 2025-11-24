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
import objectos.ui.impl.UiSpacing;

public final class DevVertical extends AbstractDevUi {

  DevVertical() {
    super("vertical");
  }

  @Override
  final void handle(Http.Exchange http) {
    final int raw;
    raw = http.queryParamAsInt("gap", 0);

    final UiSpacing gap;
    gap = 0 <= raw && raw <= 13 ? UiSpacing.values()[raw] : UiSpacing.SPACING_00;

    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Vertical - Default",

          Vertical.of(
              gap,

              m -> m.div("Item 1"),
              m -> m.div("Item 2"),
              m -> m.div("Item 3")
          )
      );
    }
  }

}

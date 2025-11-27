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

import java.util.List;
import module objectos.ui;

public final class DevContainedList extends AbstractDevUi {

  DevContainedList() {
    super("contained-list");
  }

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Contained list - Default",

          ContainedList.create(list -> {
            list.label("List title");

            final List<String> data;
            data = List.of("Item 1", "Item 2", "Item 3");

            for (String v : data) {
              list.add(ContainedListItem.create(item -> {
                item.set(m -> m.text(v));
              }));
            }
          })
      );
    }
  }

}

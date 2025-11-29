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

public final class DevBreadcrumb extends AbstractDevUi {

  DevBreadcrumb() {
    super("breadcrumb");
  }

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> ok(
          http,

          "Breadcrumb - Default",

          Breadcrumb.create(bc -> {
            bc.ariaLabel("Breadcrumb");

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-1"));
              item.href("#");
              item.set("Breadcrumb 1");
            }));

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-2"));
              item.href("#");
              item.set("Breadcrumb 2");
            }));

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-3"));
              item.href("#");
              item.set("Breadcrumb 3");
            }));
          })
      );

      case "sm" -> ok(
          http,

          "Breadcrumb - Small",

          Breadcrumb.create(bc -> {
            bc.ariaLabel("Breadcrumb");

            bc.size(BreadcrumbSize.SM);

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-1"));
              item.href("#");
              item.set("Breadcrumb 1");
            }));

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-2"));
              item.href("#");
              item.set("Breadcrumb 2");
            }));

            bc.add(BreadcrumbItem.create(item -> {
              item.id(Html.Id.of("id-3"));
              item.href("#");
              item.set("Breadcrumb 3");
            }));
          })
      );
    }
  }

}

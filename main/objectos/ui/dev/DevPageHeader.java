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

public final class DevPageHeader extends AbstractDevUi {

  DevPageHeader() {
    super("page-header");
  }

  @Override
  final void handle(Http.Exchange http) {
    switch (http.pathParam("id")) {
      case "default" -> http.ok(
          page(http, page -> {
            page.title("Page Header - Default");

            page.add(PageHeader.create(header -> {
              header.add(PageHeaderBreadcrumbRow.create(bar -> {
                bar.add(Breadcrumb.create(bc -> {
                  bc.ariaLabel("Breadcrumb");

                  bc.add(BreadcrumbItem.create(item -> {
                    item.href("#");
                    item.set("Breadcrumb 1");
                  }));

                  bc.add(BreadcrumbItem.create(item -> {
                    item.href("#");
                    item.set("Breadcrumb 2");
                  }));
                }));
              }));

              header.add(PageHeaderTitleRow.create(row -> {
                row.title("""
                A very long page title with a short version in breadcrumbs; \
                this will almost certainly be truncated at some point
                """);
              }));
            }));
          })
      );
    }
  }

}

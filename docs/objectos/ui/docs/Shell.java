/*
 * Objectos UI
 * Copyright (C) 2025 Objectos Software LTDA.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package objectos.ui.docs;

import java.util.Objects;
import java.util.function.Consumer;
import objectos.way.Html;

public final class Shell extends Html.Template {

  public static final class Builder {

    public String title;

    private Builder() {}

    final Shell build() {
      Objects.requireNonNull(title, "title == null");

      return new Shell(this);
    }

  }

  private final Builder options;

  private Shell(Builder options) {
    this.options = options;
  }

  public static Shell create(Consumer<? super Builder> options) {
    final Builder builder;
    builder = new Builder();

    options.accept(builder);

    return builder.build();
  }

  @Override
  protected final void render() {
    doctype();
    html(
        head(
            link(rel("stylesheet"), type("text/css"), href("/ui/docs/styles.css")),
            script(src("/ui/docs/script.js")),
            title(options.title)
        ),

        body(
            h1("Objectos UI")
        )
    );
  }

}
/*
 * Copyright (C) 2024-2025 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package objectos.ui;

import static objectos.way.Http.Method.GET;

import java.nio.file.Path;
import objectos.ui.docs.Shell;
import objectos.way.App;
import objectos.way.Css;
import objectos.way.Http;
import objectos.way.Note;
import objectos.way.Script;

public final class DocsModule implements Http.Routing.Module {

  private final App.Injector injector;

  public DocsModule(App.Injector injector, Module original) {
    this.injector = injector;

    final Class<? extends DocsModule> self;
    self = getClass();

    final Module module;
    module = self.getModule();

    module.addReads(original);
  }

  @Override
  public final void configure(Http.Routing routing) {
    routing.path("/ui/docs", path -> {
      path.allow(GET, this::home);
    });

    routing.path("/ui/docs/script.js", path -> {
      path.allow(GET, http -> http.ok(Script.Library.of()));
    });

    routing.path("/ui/docs/styles.css", path -> {
      path.allow(GET, this::styles);
    });

    routing.handler(Http.Handler.notFound());
  }

  private void home(Http.Exchange http) {
    http.ok(Shell.create(page -> {
      page.title = "Objectos UI Documentation";
    }));
  }

  private void styles(Http.Exchange http) {
    http.ok(Css.StyleSheet.create(opts -> {
      final Note.Sink noteSink;
      noteSink = injector.getInstance(Note.Sink.class);

      opts.noteSink(noteSink);

      final Path classOutput;
      classOutput = Path.of("work", "docs");

      opts.scanDirectory(classOutput);

      opts.theme("""
          --color-bg: var(--color-gray-100);
          --color-fg: var(--color-gray-900);
          """);

      opts.theme("@media (prefers-color-scheme: dark)", """
          --color-bg: var(--color-gray-900);
          --color-fg: var(--color-gray-100);
          """);
    }));
  }

}
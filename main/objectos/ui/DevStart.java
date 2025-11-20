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

import java.io.IOException;
import java.lang.reflect.Constructor;
import objectos.way.App;
import objectos.way.Css;
import objectos.way.Http;
import objectos.way.Lang;
import objectos.way.Note;
import objectos.way.Web;

/// This class is not part of the Objectos UI JAR file.
/// It is placed in the main source tree to ease its development.
public final class DevStart extends App.Bootstrap {

  static final Lang.Key<Css.Library> CARBON_PLEX = Lang.Key.of("Carbon.Plex");
  static final Lang.Key<Css.Library> CARBON_STYLES = Lang.Key.of("Carbon.Styles");

  public static final int TESTING_HTTP_PORT = 8007;

  public static void main(String[] args) {
    final DevStart start;
    start = new DevStart();

    start.start(args);
  }

  @Override
  protected final void bootstrap() {
    final long startTime;
    startTime = System.currentTimeMillis();

    final App.Injector injector;
    injector = App.Injector.create(this::injector);

    final Note.Sink noteSink;
    noteSink = injector.getInstance(Note.Sink.class);

    final App.ShutdownHook shutdownHook;
    shutdownHook = injector.getInstance(App.ShutdownHook.class);

    // Http.Handler
    final Http.Handler serverHandler;
    serverHandler = serverHandler(injector);

    shutdownHook.registerIfPossible(serverHandler);

    // Http.Server
    try {
      final Http.Server httpServer;
      httpServer = Http.Server.create(opts -> {
        opts.bufferSize(8192, 8192);

        opts.handler(serverHandler);

        opts.noteSink(noteSink);

        opts.port(TESTING_HTTP_PORT);
      });

      shutdownHook.register(httpServer);

      httpServer.start();
    } catch (IOException e) {
      throw App.serviceFailed("Http.Server", e);
    }

    // bootstrap end event
    final Note.Long1 totalTimeNote;
    totalTimeNote = Note.Long1.create(getClass(), "BT1", Note.INFO);

    final long totalTime;
    totalTime = System.currentTimeMillis() - startTime;

    noteSink.send(totalTimeNote, totalTime);
  }

  private void injector(App.Injector.Options ctx) {
    // Note.Sink
    final Note.Sink noteSink;
    noteSink = App.NoteSink.ofAppendable(System.out, opts -> {
      opts.filter(note -> {
        final String source;
        source = note.source();

        if (source.startsWith("objectos.ui")) {
          return true;
        }

        return note.hasAny(Note.ERROR, Note.WARN, Note.INFO);
      });
    });

    ctx.putInstance(Note.Sink.class, noteSink);

    // bootstrap start event
    final Note.Ref0 startNote;
    startNote = Note.Ref0.create(getClass(), "BT0", Note.INFO);

    noteSink.send(startNote);

    // App.ShutdownHook
    final App.ShutdownHook shutdownHook;
    shutdownHook = App.ShutdownHook.create(opts -> {
      opts.noteSink(noteSink);
    });

    shutdownHook.registerIfPossible(noteSink);

    ctx.putInstance(App.ShutdownHook.class, shutdownHook);

    // Carbon.Styles
    final Styles carbon;
    carbon = Styles.create();

    ctx.putInstance(CARBON_STYLES, carbon);

    // Carbon.Plex
    final Plex plex;
    plex = Plex.create(opts -> {
      opts.prefix("/carbon/fonts");
    });

    ctx.putInstance(CARBON_PLEX, plex);

    // Web.Resources
    final Web.Resources webResources;

    try {
      webResources = Web.Resources.create(opts -> {
        opts.include(plex);

        opts.contentTypes("""
        .woff2: font/woff2
        """);
      });
    } catch (IOException e) {
      throw App.serviceFailed("Web.Resources", e);
    }

    shutdownHook.register(webResources);

    ctx.putInstance(Web.Resources.class, webResources);
  }

  private record Reloader(App.Injector injector) implements App.Reloader.HandlerFactory {
    @Override
    public final Http.Handler reload(ClassLoader loader) throws Exception {
      final Class<?> bootClass;
      bootClass = loader.loadClass("objectos.ui.DevModule");

      final Constructor<?> constructor;
      constructor = bootClass.getConstructor(App.Injector.class, Module.class);

      final Class<? extends Reloader> self;
      self = getClass();

      final Module original;
      original = self.getModule();

      final Object instance;
      instance = constructor.newInstance(injector, original);

      final Http.Routing.Module module;
      module = (Http.Routing.Module) instance;

      return Http.Handler.of(module);
    }
  }

  private Http.Handler serverHandler(App.Injector injector) {
    try {
      return App.Reloader.create(opts -> {
        final Reloader reloader;
        reloader = new Reloader(injector);

        opts.handlerFactory(reloader);

        opts.moduleOf(DevStart.class);

        final Note.Sink noteSink;
        noteSink = injector.getInstance(Note.Sink.class);

        opts.noteSink(noteSink);
      });
    } catch (IOException e) {
      throw App.serviceFailed("App.Reloader", e);
    }
  }

}

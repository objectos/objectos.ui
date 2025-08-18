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

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import java.io.IOException;
import java.lang.reflect.Constructor;
import objectos.way.App;
import objectos.way.Http;
import objectos.way.Note;

public class YStart extends App.Bootstrap {

  public static final int TESTING_HTTP_PORT = 9007;

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

    // Playwright
    final Playwright playwright;
    playwright = Playwright.create();

    shutdownHook.register(playwright);

    final BrowserType chromium;
    chromium = playwright.chromium();

    final boolean headless;
    headless = Boolean.getBoolean("playwright.headless");

    final LaunchOptions launchOptions;
    launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);

    Y.BROWSER = chromium.launch(launchOptions);

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
    noteSink = App.NoteSink.sysout();

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
  }

  private record Reloader(App.Injector injector) implements App.Reloader.HandlerFactory {
    @Override
    public final Http.Handler reload(ClassLoader loader) throws Exception {
      final Class<?> bootClass;
      bootClass = loader.loadClass("objectos.ui.YModule");

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

        opts.moduleOf(YStart.class);

        final Note.Sink noteSink;
        noteSink = injector.getInstance(Note.Sink.class);

        opts.noteSink(noteSink);
      });
    } catch (IOException e) {
      throw App.serviceFailed("App.Reloader", e);
    }
  }

}

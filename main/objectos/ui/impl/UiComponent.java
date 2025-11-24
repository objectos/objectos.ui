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

import module java.base;
import module objectos.ui;

public abstract class UiComponent implements Html.Component {

  static final List<Html.Component> EMPTY_MAIN = List.of();

  enum MessageLevel {
    NONE,
    INFO,
    WARN,
    ERROR;
  }

  @Override
  public final Html.Markup newHtmlMarkup() {
    return new UiMarkup();
  }

  final List<Html.Component> add(List<Html.Component> list, Html.Component value) {
    final Html.Component child;
    child = Objects.requireNonNull(value, "value == null");

    final List<Html.Component> main;

    if (list == EMPTY_MAIN) {
      main = new ArrayList<>();
    } else {
      main = list;
    }

    main.add(child);

    return main;
  }

  final Html.Instruction.OfElement icon16(Html.Markup m, UiIcon icon) {
    throw new UnsupportedOperationException("Implement me");
  }

  final String id(Html.Markup m, String userId) {
    if (userId != null) {
      return userId;
    }

    if (m instanceof UiMarkup cm) {
      return cm.nextId();
    }

    if (m instanceof Html.Markup.OfTestable) {
      return "id-testable";
    }

    return IdGen.next();
  }

  final MessageLevel msg(String info, String warn, String error) {
    if (error != null) {
      return MessageLevel.ERROR;
    }

    if (warn != null) {
      return MessageLevel.WARN;
    }

    if (info != null) {
      return MessageLevel.INFO;
    }

    return MessageLevel.NONE;
  }

  private static final class IdGen {

    static final AtomicInteger NEXT = new AtomicInteger(1);

    public static String next() {
      return "id-" + NEXT.getAndIncrement();
    }

  }

}

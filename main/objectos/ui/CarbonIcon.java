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

import java.util.Objects;
import objectos.way.Html;

final class CarbonIcon extends CarbonComponent implements Carbon.Icon {

  private String css;

  private int kind;

  private String size = "16";

  private int sizeInt = 16;

  private String viewBox = "0 0 16 16";

  @Override
  public final void css(String value) {
    css = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void size16() {
    size = "16";
    sizeInt = 16;
    viewBox = "0 0 16 16";
  }

  @Override
  public final void size20() {
    size = "20";
    sizeInt = 20;
    viewBox = "0 0 20 20";
  }

  @Override
  public final void size24() {
    size = "24";
    sizeInt = 24;
    viewBox = "0 0 24 24";
  }

  @Override
  public final void size32() {
    size = "32";
    sizeInt = 32;
    viewBox = "0 0 32 32";
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.svg(
        css != null ? m.css(css) : m.noop(),
        m.fill("currentColor"),
        m.width(size), m.height(size), m.viewBox(viewBox),
        m.xmlns("http://www.w3.org/2000/svg"),
        m.raw(paths())
    );
  }

  // START generated code

  private String paths() {
    return switch (kind) {
      // WarningAlt
      case 0 -> switch (sizeInt) {
        case 16
             -> """
        <style type="text/css">.st0{fill:none;}</style><title>warning</title><path d="M8,1C4.1,1,1,4.1,1,8s3.1,7,7,7s7-3.1,7-7S11.9,1,8,1z M8,14c-3.3,0-6-2.7-6-6s2.7-6,6-6s6,2.7,6,6S11.3,14,8,14z"/><rect x="7.5" y="4" width="1" height="5"/><path d="M8,10.2c-0.4,0-0.8,0.3-0.8,0.8s0.3,0.8,0.8,0.8c0.4,0,0.8-0.3,0.8-0.8S8.4,10.2,8,10.2z"/><rect id="_Transparent_Rectangle_" class="st0" width="16" height="16"/>""";

        default -> "";
      };

      // WarningFilled
      case 1 -> switch (sizeInt) {
        case 16
             -> """
        <style type="text/css">.st0{fill:none;}.st1{opacity:0;}</style><rect id="Transparent_Rectangle" class="st0" width="16" height="16"/><path id="Compound_Path" d="M8,1C4.2,1,1,4.2,1,8s3.2,7,7,7s7-3.1,7-7S11.9,1,8,1z M7.5,4h1v5h-1C7.5,9,7.5,4,7.5,4z M8,12.2 c-0.4,0-0.8-0.4-0.8-0.8s0.3-0.8,0.8-0.8c0.4,0,0.8,0.4,0.8,0.8S8.4,12.2,8,12.2z"/><path id="inner-path" class="st1" d="M7.5,4h1v5h-1C7.5,9,7.5,4,7.5,4z M8,12.2c-0.4,0-0.8-0.4-0.8-0.8s0.3-0.8,0.8-0.8 c0.4,0,0.8,0.4,0.8,0.8S8.4,12.2,8,12.2z"/>""";

        default -> "";
      };

      default -> "";
    };
  }

  @Override
  public final void iconWarningAlt() {
    kind = 0;
  }

  @Override
  public final void iconWarningFilled() {
    kind = 1;
  }

  // END generated code

}

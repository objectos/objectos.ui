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
package objectos.ui.carbon;

import objectos.ui.Carbon;
import objectos.way.Css;
import objectos.way.Html;

@Css.Source
public final class CarbonTearsheet implements Carbon.Tearsheet, Html.Component {

  static final String CONTAINER = """
  block-size:100%
  inset-block-start:auto
  max-block-size:calc(100%-3rem)

  transform:translate3d(0,min(95vh,500px),0)
  """;

  private boolean visible;

  @Override
  public final void visible(boolean value) {
    visible = value;
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    m.div(
        m.css(CarbonModal.MODAL),
        visible ? m.css(CarbonModal.IS_VISIBLE) : m.noop(),

        m.div(
            m.css(CarbonModal.CONTAINER),
            m.css(CONTAINER),

            m.div(
            )
        )
    );
  }

}

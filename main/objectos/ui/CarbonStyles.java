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

import objectos.ui.impl.CarbonButton;
import objectos.ui.impl.CarbonButtonKind;
import objectos.ui.impl.CarbonButtonSize;
import objectos.ui.impl.CarbonFormGroup;
import objectos.ui.impl.CarbonLayer;
import objectos.ui.impl.CarbonPage;
import objectos.ui.impl.CarbonStack;
import objectos.ui.impl.CarbonTearsheet;
import objectos.ui.impl.CarbonTextInput;
import objectos.way.Css;

final class CarbonStyles extends CarbonStylesGenerated implements Carbon.Styles {

  @Override
  public final void configure(Css.Library.Options opts) {
    super.configure(opts);

    opts.scanClasses(
        CarbonButton.class,
        CarbonButtonKind.class,
        CarbonButtonSize.class,
        CarbonFormGroup.class,
        CarbonLayer.class,
        CarbonPage.class,
        CarbonStack.class,
        CarbonTearsheet.class,
        CarbonTextInput.class
    );

    opts.theme("""
    @keyframes opacity-fade-in {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }
    @keyframes opacity-fade-out {
      from {
        opacity: 1;
      }
      to {
        opacity: 0;
      }
    }

    @keyframes tearsheet-enter {
      from {
        transform: translateY(min(95vh,500px));
      }
      to {
        transform: translateY(0);
      }
    }
    @keyframes tearsheet-exit {
      from {
        transform: translateY(0);
      }
      to {
        transform: translateY(min(95vh,500px));
      }
    }
    """);
  }

}

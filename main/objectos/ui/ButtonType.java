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

import objectos.ui.impl.CarbonButtonType;

/// Represents a value of the `<button>` element `type` attribute.
public sealed interface ButtonType permits CarbonButtonType {

  /// The `button` button type.
  ButtonType BUTTON = CarbonButtonType.BUTTON;

  /// The `reset` button type.
  ButtonType RESET = CarbonButtonType.RESET;

  /// The `submit` button type.
  ButtonType SUBMIT = CarbonButtonType.SUBMIT;

}

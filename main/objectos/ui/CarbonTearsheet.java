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

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import objectos.way.Css;
import objectos.way.Html;
import objectos.way.Script;

@Css.Source
final class CarbonTearsheet extends CarbonComponent implements Carbon.Tearsheet, Html.Component {

  enum Kind {
    NARROW,

    WIDE;
  }

  private List<CarbonButton> actions = List.of();

  private String description = "";

  private Html.Instruction id = Html.Instruction.noop();

  private final Kind kind = Kind.WIDE;

  private Html.Component main;

  private boolean open;

  private String title = "";

  @Override
  public final void actions(Consumer<? super Action> action) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void actions(Consumer<? super Action> action1, Consumer<? super Action> action2) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void actions(Consumer<? super Action> action1, Consumer<? super Action> action2, Consumer<? super Action> action3) {
    actions = actions(
        action(action1, "action1 == null"),
        action(action2, "action2 == null"),
        action(action3, "action3 == null")
    );
  }

  private List<CarbonButton> actions(CarbonButton... btns) {
    return List.of(btns);
  }

  private CarbonButton action(Consumer<? super Action> action, String message) {
    final Consumer<? super Action> a;
    a = Objects.requireNonNull(action, message);

    final CarbonButton btn;
    btn = new CarbonButton();

    a.accept(btn);

    return btn;
  }

  @Override
  public final void description(String value) {
    description = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void id(Html.Id value) {
    id = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void main(Html.Component value) {
    main = Objects.requireNonNull(value, "value == null");
  }

  @Override
  public final void open(boolean value) {
    open = value;
  }

  @Override
  public final void title(String value) {
    title = Objects.requireNonNull(value, "value == null");
  }

  private static final String CLOSE = "animation-name:tearsheet-exit backdrop:animation-name:opacity-fade-out";

  public static Consumer<? super Script> closeImpl(Html.Id id) {
    return script -> {
      var dialog = script.elementById(id);
      dialog.toggleClass(CLOSE);
      script.delay(240, () -> {
        dialog.close();
        dialog.toggleClass(CLOSE);
      });
    };
  }

  private static final String OPEN = "animation-name:tearsheet-enter backdrop:animation-name:opacity-fade-in";

  public static Consumer<? super Script> openImpl(Html.Id id) {
    return script -> {
      var dialog = script.elementById(id);
      dialog.showModal();
      dialog.toggleClass(OPEN);
      script.delay(240, () -> {
        dialog.toggleClass(OPEN);
      });
    };
  }

  @Override
  public final void renderHtml(Html.Markup m) {
    // tearsheet
    m.dialog(
        m.css("""
        animation-duration:240ms
        background-color:layer
        block-size:100%
        border:none
        color:text-primary
        grid-template-columns:100%
        grid-template-rows:auto_1fr_auto
        inline-size:100%
        inset-block-start:auto
        margin:auto
        max-block-size:100%
        max-inline-size:100%
        outline:none
        transform-origin:top_center
        z-index:9000

        backdrop:animation-duration:240ms
        backdrop:background-color:overlay
        backdrop:opacity:0

        [open]:display:grid
        [open]:backdrop:opacity:1

        md:max-block-size:calc(100%_-_48rx)
        md:max-inline-size:calc(100%_-_128rx)
        """),

        m.ariaLabel(title),

        id,

        open ? m.dataOnLoad(script -> {
          var dialog = script.element();
          dialog.showModal();
          dialog.toggleClass(OPEN);
          script.delay(240, () -> {
            dialog.toggleClass(OPEN);
          });
        }) : m.noop(),

        header(m),

        body(m)
    );
  }

  private Html.Instruction header(Html.Markup m) {
    return m.div(
        m.css("""
        background-color:layer
        border-block-end:1px_solid_var(--color-border-subtle-01)
        grid-column:1/-1
        grid-row:1/1
        margin:0
        max-block-size:50vh
        padding:1.5rem_2rem
        overflow-y:auto
        """),

        // header-content
        m.div(
            CarbonLayer.LAYER_1,

            m.css("""
            display:flex
            justify-content:space-between
            """),

            // fields
            m.div(
                // title
                m.h3(
                    m.css("""
                    border:0
                    font-size:var(--carbon-heading-04-font-size,1.75rem)
                    font-weight:var(--carbon-heading-04-font-weight,400)
                    letter-spacing:var(--carbon-heading-04-letter-spacing,0)
                    line-height:var(--carbon-heading-04-line-height,1.28572)
                    margin:0
                    padding:0
                    padding-inline-end:calc(20%_-_3rem)
                    vertical-align:baseline
                    """),

                    m.text(title)
                ),

                // description
                m.div(
                    m.css("""
                    display:inline-flex
                    font-size:var(--carbon-body-compact-01-font-size,.875rem)
                    font-weight:var(--carbon-body-compact-01-font-weight,400)
                    margin-block-start:1rem
                    max-inline-size:100%
                    letter-spacing:var(--carbon-body-compact-01-letter-spacing,.16px)
                    line-height:var(--carbon-body-compact-01-line-height,1.28572)

                    md:max-inline-size:60%
                    """),

                    m.text(description)
                )
            ),

            // actions
            m.noop()
        ),

        // header-navigation
        m.noop(),

        // modal-close-button
        m.noop()
    );
  }

  private Html.Instruction body(Html.Markup m) {
    return m.div(
        m.css("""
        color:text-primary
        display:flex
        flex-direction:row
        font-size:var(--carbon-body-01-font-size,0.875rem)
        font-weight:var(--carbon-body-01-font-weight,400)
        grid-column:1/-1
        grid-row:2/-2
        letter-spacing:var(--carbon-body-01-letter-spacing,0.16px)
        line-height:var(--carbon-body-01-line-height,1.42857)
        margin:0
        overflow-y:auto
        padding:0
        position:relative
        """),

        // right
        m.div(
            m.css("""
            display:grid
            flex-grow:1
            grid-template-columns:100%
            grid-template-rows:1fr_auto
            """),

            // main
            m.div(
                m.css("""
                background-color:background
                display:flex
                flex-direction:row
                grid-column:1/-1
                grid-row:1/-1
                """),

                // content
                m.section(
                    m.css("""
                    flex-grow:1
                    overflow:auto
                    """),

                    main != null ? m.c(main) : m.noop()
                )
            ),

            // button-container
            !actions.isEmpty() ? m.div(
                m.css("""
                grid-column:1/-1
                grid-row:-1/-1
                overflow-x:auto
                """),

                // buttons
                m.div(
                    m.css("""
                    align-items:stretch
                    background-color:background
                    border-block-start:1px_solid_border-subtle-01
                    display:inline-flex
                    justify-content:flex-end
                    min-inline-size:100%
                    """),

                    m.role("presentation"),

                    m.c(buttons())
                )
            ) : m.noop()
        )
    );
  }

  private static final String WIDE_BASE_BTN = """
  align-items:center
  block-size:80rx
  box-shadow:-1rx_0_0_0_button-separator
  padding-block:16rx_32rx

  first-child:box-shadow:inherit
  """;

  @Css.Source
  private enum ButtonStyle implements Html.AttributeObject {
    HALF_WIDTH(WIDE_BASE_BTN, """
    flex:1_1_50%
    padding-inline-start:32rx
    max-inline-size:none
    """),

    REGULAR(WIDE_BASE_BTN, """
    flex:0_1_25%
    max-inline-size:232rx
    """);

    private final String attrValue;

    private ButtonStyle(String base, String css) {
      attrValue = Html.formatAttrValue(base + css);
    }

    @Override
    public final Html.AttributeName attrName() {
      return Html.AttributeName.CLASS;
    }

    @Override
    public final String attrValue() {
      return attrValue;
    }

    final ButtonStyle narrow() {
      return switch (this) {
        default -> this;
      };
    }
  }

  private class Buttons implements Iterable<CarbonButton>, Iterator<CarbonButton> {

    private int cursor;

    @Override
    public final boolean hasNext() {
      return cursor < actions.size();
    }

    @Override
    public final CarbonButton next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      final int idx;
      idx = cursor++;

      final CarbonButton next;
      next = actions.get(idx);

      ButtonStyle style;
      style = ButtonStyle.REGULAR;

      if (idx == 0 && next.isGhost()) {
        style = ButtonStyle.HALF_WIDTH;
      }

      if (kind == Kind.NARROW) {
        style = style.narrow();
      }

      next.size(CarbonButton.Size.NOOP);

      next.internalStyle(style);

      return next;
    }

    @Override
    public final Iterator<CarbonButton> iterator() {
      return this;
    }

  }

  private Iterable<CarbonButton> buttons() {
    return new Buttons();
  }

}

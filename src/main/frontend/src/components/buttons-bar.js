import { html, css, LitElement } from 'lit';

/**
 * @class ButtonsBarElement
 * @extends LitElement
 *
 * @description
 * A reusable web component that provides a flexible horizontal bar
 * for organizing buttons and informational elements.
 *
 * It supports three slot regions:
 *  - `left`: for primary or navigation buttons
 *  - `info`: for contextual information or status text
 *  - `right`: for secondary or action buttons
 *
 * The layout adapts responsively to smaller screens and includes
 * subtle shadow transitions when content is scrollable.
 *
 * @example
 * ```html
 * <buttons-bar>
 *   <vaadin-button slot="left">Cancel</vaadin-button>
 *   <span slot="info">3 items selected</span>
 *   <vaadin-button slot="right" theme="primary">Save</vaadin-button>
 * </buttons-bar>
 * ```
 *
 * @author Francisco Monteiro
 * @version 1.0
 */
class ButtonsBarElement extends LitElement {

  /**
   * @description
   * Defines the CSS styling for the component, including layout,
   * spacing, and responsive design rules.
   *
   * @returns {CSSResult} The CSS styles for the component.
   */
  static get styles() {
    return css`
      :host {
        flex: none;
        display: flex;
        flex-wrap: wrap;
        transition: box-shadow 0.2s;
        justify-content: space-between;
        padding-top: var(--lumo-space-s);
        align-items: baseline;
        box-shadow: 0 -3px 3px -3px var(--lumo-shade-20pct);
      }

      :host([no-scroll]) {
        box-shadow: none;
      }

      :host ::slotted([slot='info']),
      .info {
        text-align: right;
        flex: 1;
      }

      ::slotted(vaadin-button) {
        margin: var(--lumo-space-xs);
      }

      @media (max-width: 600px) {
        :host ::slotted([slot='info']) {
          order: -1;
          min-width: 100%;
          flex-basis: 100%;
        }
      }
    `;
  }

  /**
   * @description
   * Defines the component’s HTML structure using three named slots:
   * `left`, `info`, and `right`. Each slot can contain elements such
   * as buttons or text from the host page.
   *
   * @returns {TemplateResult} The HTML template for rendering.
   */
  render() {
    return html`
      <slot name="left"></slot>
      <slot name="info"><div class="info"></div></slot>
      <slot name="right"></slot>
    `;
  }

  /**
   * @description
   * Provides the custom element tag name used to define and register
   * this component in the browser’s Custom Elements registry.
   *
   * @returns {string} The tag name of this web component.
   */
  static get is() {
    return 'buttons-bar';
  }
}

customElements.define(ButtonsBarElement.is, ButtonsBarElement);
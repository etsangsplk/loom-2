/*******************************************************************************
 * (c) Copyright 2017 Hewlett Packard Enterprise Development LP Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/
define(function (require) {

  "use strict";

  /** @type BaseView */
  var BaseView = require('./BaseView');

  /**
   * MenuNotificationControllers help Menus display notifications that get removed when the Menu gets expanded
   * @backbone no-initialize
   * @class MenuNotificationController
   * @namespace views
   * @module  weaver
   * @submodule views
   * @extends BaseView
   * @constructor
   */
  return BaseView.extend({

    /**
     * @property className
     * @type {String}
     */
    className: 'has-notification',

    /**
     * @property events
     * @type {Object}
     */
    events: {
      'willExpand': 'clearNotification'
    },

    /**
     * @method showNotification
     */
    showNotification: function () {
      if (this.$el.hasClass('is-collapsed')) {
        this.$el.addClass(this.className);
      }
    },

    /**
     * This overridden version must not initialize the $el element, otherwise iot breaks the unit tests
     * @method initialize
     * @param options
     */
    initialize: function (options) {
      this._applyBackboneOptionsFix(options);
    },

    /**
     * @method clearNotification
     */
    clearNotification: function () {
      this.$el.removeClass(this.className);
    }
  });

});

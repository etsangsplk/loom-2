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
define([], function () {

  "use strict";

  /** @type BaseView */
  var BaseView = require('weaver/views/BaseView');

  /**
   * @class RemovedElementsView
   * @namespace views
   * @module  weaver
   * @submodule views
   * @extends BaseView
   * @constructor
   */
  var RemovedElementsView = BaseView.extend({

    /**
     * @property constructorName
     * @type {String}
     * @default LOOM_RemovedElementsView
     * @final
     */
    constructorName: 'LOOM_RemovedElementsView',

    /**
     * @property className
     * @type {String}
     */
    className: 'mas-removeElements',

    /**
     * @method initialize
     */
    initialize: function () {
      BaseView.prototype.initialize.apply(this, arguments);
      this.listenTo(this.model, 'change:numberOfRemovedElements', this.render);
      this.render();
    },

    /**
     * @method render
     */
    render: function () {
      this.el.textContent = this.model.get('numberOfRemovedElements');
    }
  });

  return RemovedElementsView;

});

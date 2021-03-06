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

  var _ = require('lodash');
  var FilterService = require('weft/services/FilterService');
  var EventBus = require('weaver/utils/EventBus');

  /**
   * RelationshipHighlightFilterService handles which related elements are currently highlighted.
   * @class RelationshipHighlightFilterService
   * @namespace services
   * @module weaver
   * @submodule services
   * @extends Backbone.Model
   */
  var RelationshipHighlightFilterService = FilterService.extend({

    constructorName: 'LOOM_RelationshipHighlightFilterService',

    /**
     * An message that is sent out on the EventBus when the relationType changes
     * @property {String} RelationTypeChangeEvent
     */
    RelationTypeChangeEvent: 'relationship-highlight-filter-service:relation-type:change',

    /**
     * Global event bus for inter module communication
     * @type Backbone.Events
     */
    EventBus: EventBus,

    defaults: function () {
      return _.defaults({

        /**
         * The flag that will be set on elements part of the filter.
         * Set to undefined if you don't want these elements to be flagged.
         * @property filterFlag
         * @type {string}
         */
        filterFlag: null,

        /**
         * The flag that will be set on elements matching the filter
         * @property filteredElementsFlag
         * @type {string}
         */
        filteredElementsFlag: 'highlighted',

        /**
         * A flag setting if the service is active or not
         * @property active
         * @type {boolean}
         */
        active: false
      }, FilterService.prototype.defaults());
    },
    initialize: function () {
      FilterService.prototype.initialize.apply(this, arguments);
      this.listenTo(this.EventBus, 'element:will:remove', function (event) {
        if (event.view.model.get('selected')) {
          this.setFilter([]);
        }
      });
      this.listenTo(this.EventBus, 'fiber:selected', function (event) {
        this.setFilter([event.fiber]);
      });
      this.listenTo(this.EventBus, 'fiber:unselected', function () {
        this.setFilter([]);
      });
      this.listenTo(this, 'change:relationType', function() {
        EventBus.trigger(RelationshipHighlightFilterService.RelationTypeChangeEvent, this.get('relationType'));
      });

    }
  });

  return RelationshipHighlightFilterService;
});

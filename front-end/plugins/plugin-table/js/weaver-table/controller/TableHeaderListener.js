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
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
define(["require", "exports", 'backbone'], function (require, exports, Backbone) {
    var TableHeaderMonitor = (function (_super) {
        __extends(TableHeaderMonitor, _super);
        function TableHeaderMonitor(el) {
            _super.call(this, { el: el });
            this.listeningTo = {};
        }
        TableHeaderMonitor.prototype.listenToColumn = function (class_col, col) {
            var _this = this;
            if (!this.listeningTo[class_col]) {
                this.$el.on('click', '.' + class_col + ':not(.is-collapsed)', function () {
                    _this.busEvent.trigger('collapse:column', col);
                });
                this.$el.on('click', '.is-collapsed.' + class_col, function () {
                    _this.busEvent.trigger('expand:column', col);
                });
                this.listeningTo[class_col] = true;
            }
        };
        TableHeaderMonitor.prototype.remove = function () {
            var _this = this;
            _.forEach(_.keys(this.listeningTo), function (class_col) {
                _this.$el.off('click', '.' + class_col + ':not(.is-collapsed)');
                _this.$el.off('click', '.is-collapsed.' + class_col);
            });
            _super.prototype.remove.call(this);
            return this;
        };
        return TableHeaderMonitor;
    })(Backbone.View);
    return TableHeaderMonitor;
});

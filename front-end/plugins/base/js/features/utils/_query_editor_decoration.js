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
define(["require", "exports", 'weaver/views/QueryEditor'], function (require, exports, QueryEditor) {
    var originalGenerateQueryFromPipeline = QueryEditor.prototype.generateQueryFromPipeline;
    var originalInitialize = QueryEditor.prototype.initialize;
    QueryEditor.prototype.generateQueryFromPipeline = function () {
        var query = originalGenerateQueryFromPipeline.apply(this, arguments);
        query = this.queryValidator.validateQuery(query);
        return query;
    };
    QueryEditor.prototype.initialize = function (options) {
        originalInitialize.apply(this, arguments);
        this.queryValidator = options.queryValidator;
    };
    var decorate = function () {
    };
    return decorate;
});
//# sourceMappingURL=_query_editor_decoration.js.map
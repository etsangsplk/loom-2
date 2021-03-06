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
package com.hp.hpl.loom.openstack.cinder.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.loom.openstack.OpenstackApi;
import com.hp.hpl.loom.openstack.PagingBase;
import com.hp.hpl.loom.openstack.common.Pagination;
import com.hp.hpl.loom.openstack.keystonev3.model.JsonEndpoint;

/**
 * Base class for the nova API.
 *
 * @param <R> the type that this API returns.
 */
public abstract class CinderBase<R extends Pagination, T> extends PagingBase<R, T> {
    private static final Log LOG = LogFactory.getLog(CinderBase.class);


    /**
     * @param openstackApp the openstackApp used to access the rest templates and token holder
     * @param jsonEndpoint the end point to access
     */
    public CinderBase(final OpenstackApi openstackApp, final JsonEndpoint jsonEndpoint) {
        super(openstackApp, jsonEndpoint);
    }
}

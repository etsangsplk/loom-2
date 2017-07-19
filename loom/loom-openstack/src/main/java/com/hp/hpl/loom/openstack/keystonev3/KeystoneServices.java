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
package com.hp.hpl.loom.openstack.keystonev3;

import com.hp.hpl.loom.openstack.OpenstackApi;
import com.hp.hpl.loom.openstack.keystonev3.model.JsonServices;

/**
 * The keystone Services implementation.
 */
public class KeystoneServices extends KeystoneBase<JsonServices> {
    /**
     * An implementation of the Keystone Services API.
     *
     * @param openstackApp the openstackApi for looking up tokens
     */
    public KeystoneServices(final OpenstackApi openstackApp) {
        super(openstackApp);
    }

    @Override
    protected String getUriSuffix() {
        return "service";
    }

    @Override
    protected Class<JsonServices> getTypeClass() {
        return JsonServices.class;
    }
}

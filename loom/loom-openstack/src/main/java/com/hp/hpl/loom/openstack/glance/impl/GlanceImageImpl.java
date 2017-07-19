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
package com.hp.hpl.loom.openstack.glance.impl;

import java.util.List;

import com.hp.hpl.loom.openstack.OpenstackApi;
import com.hp.hpl.loom.openstack.glance.GlanceImage;
import com.hp.hpl.loom.openstack.glance.model.JsonImage;
import com.hp.hpl.loom.openstack.glance.model.JsonImages;
import com.hp.hpl.loom.openstack.keystonev3.model.JsonEndpoint;

/**
 * The glance image implementation.
 */
public class GlanceImageImpl extends GlanceBase<JsonImages, JsonImage> implements GlanceImage {
    /**
     * Constructor that takes the end point to use and an openstackApp to lookup the RestTemplate.
     *
     * @param openstackApp the openstackApp used to access the rest templates and token holder
     * @param jsonEndpoint the end point to access
     */
    public GlanceImageImpl(final OpenstackApi openstackApp, final JsonEndpoint jsonEndpoint) {
        super(openstackApp, jsonEndpoint);
    }

    @Override
    protected Class<JsonImages> getTypeClass() {
        return JsonImages.class;
    }

    @Override
    protected String getUriSuffix() {
        return "image";
    }

    @Override
    public String getUri() {
        String resourcesUri = jsonEndpoint.getUrl() + getUriSuffix() + "s";
        return resourcesUri;
    }

    @Override
    public void addToResult(final JsonImages result, final JsonImages nextResults) {
        result.getImages().addAll(nextResults.getImages());
        result.setLinks(null);
    }

    @Override
    public List<JsonImage> getResults(final JsonImages result) {
        return result.getImages();
    }

}

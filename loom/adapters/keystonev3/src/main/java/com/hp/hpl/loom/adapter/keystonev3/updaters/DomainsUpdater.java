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
package com.hp.hpl.loom.adapter.keystonev3.updaters;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.hp.hpl.loom.adapter.BaseAdapter;
import com.hp.hpl.loom.adapter.ConnectedItem;
import com.hp.hpl.loom.adapter.keystonev3.KeystoneCollector;
import com.hp.hpl.loom.adapter.keystonev3.items.Domain;
import com.hp.hpl.loom.adapter.keystonev3.items.DomainItemAttributes;
import com.hp.hpl.loom.adapter.keystonev3.rest.resources.JsonDomain;
import com.hp.hpl.loom.adapter.keystonev3.rest.resources.JsonDomains;
import com.hp.hpl.loom.exceptions.NoSuchItemTypeException;
import com.hp.hpl.loom.model.Aggregation;
import com.hp.hpl.loom.model.CoreItemAttributes.ChangeStatus;

public class DomainsUpdater extends KeystoneUpdater<Domain, DomainItemAttributes, JsonDomain> {
    private static final Log LOG = LogFactory.getLog(DomainsUpdater.class);

    public DomainsUpdater(final Aggregation aggregation, final BaseAdapter adapter, final String itemTypeLocalId,
            final KeystoneCollector keystoneCollector) throws NoSuchItemTypeException {
        super(aggregation, adapter, itemTypeLocalId, keystoneCollector);
    }


    @Override
    protected String getItemId(final JsonDomain resource) {
        return resource.getId();
    }

    @Override
    protected Iterator<JsonDomain> getResourceIterator() {
        return this.getJsonResources("domain").iterator();
        // return getJsonDomains().getDomains().iterator();
    }

    @Override
    protected List<JsonDomain> getResourcesFromGet(final RestTemplate rt, final URI targetURI)
            throws HttpStatusCodeException, UpdaterHttpException {
        ResponseEntity<JsonDomains> resp = rt.getForEntity(targetURI, JsonDomains.class);
        if (resp != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("response is not null: " + resp.getStatusCode());
            }
            if (resp.getStatusCode() == HttpStatus.OK) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("response is OK");
                }
                return resp.getBody().getDomains();
            } else {
                throw new UpdaterHttpException(
                        "unable to collect domains - status code: " + resp.getStatusCode().toString());
            }
        } else {
            throw new UpdaterHttpException("unable to collect domains - HTTP response was null");
        }
    }

    @Override
    protected Domain createEmptyItem(final String logicalId) {
        return new Domain(logicalId, itemType);
    }

    @Override
    protected DomainItemAttributes createItemAttributes(final JsonDomain resource) {
        DomainItemAttributes dia = new DomainItemAttributes();
        dia.setEnabled(resource.isEnabled());
        dia.setItemDescription(resource.getDescription());
        dia.setItemId(resource.getId());
        dia.setItemName(resource.getName());
        return dia;
    }

    @Override
    protected ChangeStatus compareItemAttributesToResource(final DomainItemAttributes dia, final JsonDomain resource) {
        if (dia.isEnabled() != resource.isEnabled()) {
            return ChangeStatus.CHANGED_UPDATE;
        } else {
            return ChangeStatus.UNCHANGED;
        }
    }

    @Override
    protected void setRelationships(final ConnectedItem item, final JsonDomain resource) {
        // no rels
    }

}

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
package com.hp.hpl.loom.openstack.keystonev3.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Object to model the users.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonUsers {

    private List<JsonUser> users = new ArrayList<>(0);

    /**
     * @return the users
     */
    public List<JsonUser> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(final List<JsonUser> users) {
        this.users = users;
    }
}

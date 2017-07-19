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
package com.hp.hpl.loom.adapter.keystonev3.items;

import com.hp.hpl.loom.adapter.AdapterItem;
import com.hp.hpl.loom.adapter.annotations.ConnectedTo;
import com.hp.hpl.loom.adapter.annotations.ItemTypeInfo;
import com.hp.hpl.loom.adapter.annotations.Root;
import com.hp.hpl.loom.model.ItemType;

// root is neded here to make sure that users are not related through sharing a role
@Root
@ItemTypeInfo(RoleType.TYPE_LOCAL_ID)
@ConnectedTo(toClass = Project.class)
@ConnectedTo(toClass = User.class)
public class Role extends AdapterItem<RoleItemAttributes> {

    public Role(final String logicalId, final ItemType itemType) {
        super(logicalId, itemType);
    }
}

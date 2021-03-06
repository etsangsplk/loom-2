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
package com.hp.hpl.loom.adapter.twitter.items;

import com.hp.hpl.loom.adapter.AdapterItem;
import com.hp.hpl.loom.model.ItemType;

public abstract class TwItem<A extends TwItemAttributes> extends AdapterItem<A> {
    // ------------------------------------------------------------------ //
    // PUBLIC INTERFACE //
    // ------------------------------------------------------------------ //

    public TwItem() {
        super();
    }

    public TwItem(final String logicalId, final ItemType type) {
        super(logicalId, type);
    }

}

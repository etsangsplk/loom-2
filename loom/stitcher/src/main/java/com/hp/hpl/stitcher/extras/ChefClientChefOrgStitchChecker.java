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
package com.hp.hpl.stitcher.extras;

import com.hp.hpl.loom.adapter.hpcloud.item.ChefClientItem;
import com.hp.hpl.loom.adapter.hpcloud.item.ChefOrgItem;
import com.hp.hpl.stitcher.StitchChecker;

public class ChefClientChefOrgStitchChecker implements StitchChecker<ChefClientItem, ChefOrgItem> {

    public double checkStitch(ChefClientItem baseElement, ChefOrgItem candidateElement) {
        String chefClientOrgGuid = (String) baseElement.getCore().getPayload().getAttribute("org_guid");
        String chefOrgGuid = (String) candidateElement.getCore().getPayload().getAttribute("guid");

        if (chefClientOrgGuid.equals(chefOrgGuid)) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

}

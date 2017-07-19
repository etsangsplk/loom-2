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
package com.hp.hpl.loom.openstack;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.hp.hpl.loom.openstack.keystonev3.TokenManager;

/**
 * Interceptor for the client call that adds in the X-Auth-Token scoped token to the call.
 */
public class ScopedClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Log LOG = LogFactory.getLog(ScopedClientHttpRequestInterceptor.class);
    private TokenManager tokenManager;
    private String projectId;

    /**
     * Constructor that takes the tokenManager to look the X-Auth-Token from.
     *
     * @param projectId projectId to get the scoped token for
     * @param tokenManager the tokenManager to look the token up from
     */
    public ScopedClientHttpRequestInterceptor(final String projectId, final TokenManager tokenManager) {
        this.projectId = projectId;
        this.tokenManager = tokenManager;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
            final ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("X-Auth-Token", tokenManager.getTokenHolder().getScopedToken(projectId));
        if (LOG.isInfoEnabled()) {
            LOG.info("X-Auth-Token: " + tokenManager.getTokenHolder().getScopedToken(projectId));
        }
        return execution.execute(request, body);
    }
}

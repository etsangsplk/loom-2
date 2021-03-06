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
package com.hp.hpl.loom.openstack.common;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utils class for the rest calls.
 */
public final class RestUtils {
    /**
     * Protected constructor as this is a utility class.
     */
    protected RestUtils() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    /**
     * Get the rest template with a proxy host and port.
     *
     * @param proxyHost proxy host
     * @param proxyPort proxy port
     * @return the RestTemplate
     */
    public static RestTemplate createRestTemplate(final String proxyHost, final int proxyPort) {
        HttpComponentsClientHttpRequestFactory reqFactory;
        CloseableHttpClient httpClient;
        if ((proxyHost != null) && (proxyPort != -1)) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            httpClient = HttpClients.custom().setProxy(proxy).build();
        } else {
            httpClient = HttpClients.custom().build();
        }
        reqFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(reqFactory);
    }

    // public static RestTemplate createRestTemplateWithJsonSupport(final String proxyHost, final
    // int proxyPort) {
    // RestTemplate rt = createRestTemplate(proxyHost, proxyPort);
    // List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    // messageConverters.add(new MappingJackson2HttpMessageConverter());
    // rt.setMessageConverters(messageConverters);
    // return rt;
    // }

    /**
     * Helper method to convert a json object to a string.
     *
     * @param object object to convert to a string
     * @return the string
     */
    public static String toJson(final Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonRep = "";
        try {
            jsonRep = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonRep;
    }
}

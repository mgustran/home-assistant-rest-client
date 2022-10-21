package com.mgustran.homeassistant.client.voicea;

import com.mgustran.homeassistant.client.base.BaseClient;
import com.mgustran.homeassistant.client.base.BaseResponse;
import com.mgustran.homeassistant.client.exception.HaException;
import com.mgustran.homeassistant.client.util.HeadersUtils;
import com.mgustran.homeassistant.client.util.PropertiesProvider;
import com.mgustran.homeassistant.model.converters.OriginalDomainToOptimizedDomainConverter;
import com.mgustran.homeassistant.model.optimized.HaDomain;
import com.mgustran.homeassistant.model.original.OriginalHaDomain;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.mgustran.homeassistant.client.util.HeadersUtils.Header.JSON;


class HomeAssistantApiClientBase {

    final BaseClient baseClient = new BaseClient();
    final HeadersUtils headersUtils = new HeadersUtils();

    protected final String token;
    public final String host;

    public HomeAssistantApiClientBase() {

        final PropertiesProvider propertiesProvider = new PropertiesProvider();

        final String host = propertiesProvider.getProperty("homeassistant.host");
        final String token = propertiesProvider.getProperty("homeassistant.token");
        try {
            URL u = new URL(host);
            u.toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        this.host = host.endsWith("/") ? host.substring(0, host.length() - 1) : host;
        this.token = token.replace("Bearer", "").trim();
    }

    public List<HaDomain> getServicesByDomain() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<OriginalHaDomain[]> test = this.baseClient.execute(
                this.host + "/api/services", "GET",
                headers,null, OriginalHaDomain[].class);

        return new OriginalDomainToOptimizedDomainConverter().convertList(Arrays.asList(test.getResponse()));
    }
}

package com.mgustran.homeassistant.client.voicea;

import com.mgustran.homeassistant.client.base.BaseClient;
import com.mgustran.homeassistant.client.util.HeadersUtils;
import com.mgustran.homeassistant.client.util.PropertiesProvider;

import java.net.URL;
import java.time.ZoneId;


class HomeAssistantApiClientBase {

    final BaseClient baseClient = new BaseClient();
    final HeadersUtils headersUtils = new HeadersUtils();

    protected final String token;
    public final String host;
    public final ZoneId tz;

    public HomeAssistantApiClientBase() {

        final PropertiesProvider propertiesProvider = new PropertiesProvider();

        final String host = propertiesProvider.getProperty("homeassistant.host");
        final String token = propertiesProvider.getProperty("homeassistant.token");
        final String tz = propertiesProvider.getProperty("homeassistant.tz");
        try {
            URL u = new URL(host);
            u.toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        this.host = host.endsWith("/") ? host.substring(0, host.length() - 1) : host;
        this.token = token.replace("Bearer", "").trim();
        this.tz = (tz != null && tz.isBlank()) ? ZoneId.of(tz) : ZoneId.systemDefault();
    }
}

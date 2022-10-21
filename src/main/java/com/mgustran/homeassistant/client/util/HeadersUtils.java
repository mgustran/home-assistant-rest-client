package com.mgustran.homeassistant.client.util;

import lombok.Getter;

import java.util.HashMap;

public class HeadersUtils {

    public HashMap<String, String> generateHeaders(final String homeAssistantToken, final Header accept, final Header contentType) {
        HashMap<String, String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer " + homeAssistantToken);

        if (contentType != null) {
            headers.put("Content-Type", contentType.getKey());
        }

        if (accept != null) {
            headers.put("Accept", accept.getKey());
        }

        return headers;
    }

    public HashMap<String, String> generateHeaders(final String homeAssistantToken, final Header accept) {
        return generateHeaders(homeAssistantToken, accept, null);
    }

    public HashMap<String, String> generateHeaders(final String homeAssistantToken) {
        return generateHeaders(homeAssistantToken, null, null);
    }


    @Getter
    public enum Header {
        JSON("application/json"),
        TEXT("plain/text")
        ;

        private final String key;
        Header(String key) {
            this.key = key;
        }
    }
}

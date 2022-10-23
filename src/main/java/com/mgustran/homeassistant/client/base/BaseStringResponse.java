package com.mgustran.homeassistant.client.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
class BaseStringResponse {

    Integer responseCode;
    String responseString;
}

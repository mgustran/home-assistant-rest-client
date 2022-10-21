package com.mgustran.homeassistant.model.original;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.LinkedHashMap;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class OriginalHaDomain {

    String domain;
    LinkedHashMap<String, LinkedHashMap<String, Object>> services;
}

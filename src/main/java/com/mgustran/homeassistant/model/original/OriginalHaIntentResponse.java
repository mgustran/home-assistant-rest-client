package com.mgustran.homeassistant.model.original;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.LinkedHashMap;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalHaIntentResponse {

    OriginalHaIntentResponseSpeech speech;
    LinkedHashMap<String, Object> card;
}

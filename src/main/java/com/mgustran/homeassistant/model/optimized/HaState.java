package com.mgustran.homeassistant.model.optimized;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaState {

    @JsonProperty("entity_id")
    String entityId;
    String state;
    LinkedHashMap<String, Object> attributes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    @JsonProperty("last_changed")
    LocalDateTime lastChanged;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    @JsonProperty("last_updated")
    LocalDateTime lastUpdated;

    HaStateContext context;

}

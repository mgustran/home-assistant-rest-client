package com.mgustran.homeassistant.model.optimized;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaCalendar {

    @JsonProperty("entity_id")
    String entityId;

    String name;
}

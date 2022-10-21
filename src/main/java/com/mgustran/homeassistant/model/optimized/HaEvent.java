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
public class HaEvent {

    String event;

    @JsonProperty("listener_count")
    Integer listenerCount;
}

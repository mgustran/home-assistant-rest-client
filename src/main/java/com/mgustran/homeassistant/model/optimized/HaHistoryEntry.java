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
public class HaHistoryEntry {

    @JsonProperty("entity_id")
    public String entityId;
    public String state;
    @JsonProperty("attributes")
    public HaAttributes haAttributes;
    @JsonProperty("last_changed")
    public String lastChanged;
    @JsonProperty("last_updated")
    public String lastUpdated;

}

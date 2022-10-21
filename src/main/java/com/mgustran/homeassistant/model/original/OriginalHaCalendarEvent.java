package com.mgustran.homeassistant.model.original;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class OriginalHaCalendarEvent {

    @JsonProperty("summary")
    public String summary;
    @JsonProperty("description")
    public String description;
    @JsonProperty("location")
    public Object location;
    @JsonProperty("start")
    public OriginalHaDate start;
    @JsonProperty("end")
    public OriginalHaDate end;

}

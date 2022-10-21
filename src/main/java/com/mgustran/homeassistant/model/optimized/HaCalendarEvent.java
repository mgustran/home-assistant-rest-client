package com.mgustran.homeassistant.model.optimized;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaCalendarEvent {

    @JsonProperty("summary")
    public String summary;
    @JsonProperty("description")
    public String description;
    @JsonProperty("location")
    public Object location;

    @JsonProperty("start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate start;

    @JsonProperty("end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate end;

}

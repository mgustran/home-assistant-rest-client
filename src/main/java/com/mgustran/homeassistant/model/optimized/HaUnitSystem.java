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
public class HaUnitSystem {

    public String length;
    @JsonProperty("accumulated_precipitation")
    public String accumulatedPrecipitation;
    public String mass;
    public String pressure;
    public String temperature;
    public String volume;
    @JsonProperty("wind_speed")
    public String windSpeed;

}
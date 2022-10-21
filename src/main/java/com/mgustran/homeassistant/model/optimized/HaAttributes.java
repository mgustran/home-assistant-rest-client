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
public class HaAttributes {

@JsonProperty("unit_of_measurement")
public String unitOfMeasurement;
@JsonProperty("device_class")
public String deviceClass;
@JsonProperty("friendly_name")
public String friendlyName;

}


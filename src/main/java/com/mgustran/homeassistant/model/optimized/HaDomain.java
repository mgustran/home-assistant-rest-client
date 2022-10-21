package com.mgustran.homeassistant.model.optimized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaDomain {

    String domain;
    List<HaService> services;
}

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
public class HaService {
    String id;
    String name;
    String description;
    List<HaField> fields;
    Object target;
}

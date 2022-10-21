package com.mgustran.homeassistant.model.optimized;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.LinkedHashMap;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaField {

    String id;
    String name;
    String description;
    Boolean required;
    Boolean advanced;
    String exampleStr;
    Integer exampleInt;
    LinkedHashMap<String, Object> selector;

}

package com.mgustran.homeassistant.model.optimized;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

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
    @JsonProperty("icon")
    public String icon;
    @JsonProperty("message")
    public String message;
    @JsonProperty("all_day")
    public String allDay;
    @JsonProperty("start_time")
    public String startTime;
    @JsonProperty("end_time")
    public String endTime;
    public String location;
    public String offset_reached;
    public String source_type;
    public String color_mode;
    public String source_entity_id;
    public String is_group;
    public List<Object> group_members;
    public String group_leader;
    public String active_queue;
    public String items_in_queue;
    public String queue_index;
    public String volume_level;

}


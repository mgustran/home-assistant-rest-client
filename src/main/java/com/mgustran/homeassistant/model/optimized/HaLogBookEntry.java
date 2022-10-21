package com.mgustran.homeassistant.model.optimized;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;


@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaLogBookEntry {

    @JsonProperty("when")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    LocalDateTime when;
    @JsonProperty("state")
    public String state;
    @JsonProperty("entity_id")
    public String entityId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("message")
    public String message;
    @JsonProperty("icon")
    public String icon;
    @JsonProperty("domain")
    public String domain;
    @JsonProperty("context_user_id")
    public String contextUserId;
    @JsonProperty("context_domain")
    public String contextDomain;
    @JsonProperty("context_service")
    public String contextService;
    @JsonProperty("context_event_type")
    public String contextEventType;
}
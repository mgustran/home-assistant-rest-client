package com.mgustran.homeassistant.model.optimized;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class HaConfig {

    Double latitude;
    Double longitude;
    Integer elevation;
    @JsonProperty("unit_system")
    HaUnitSystem unitSystem;
    @JsonProperty("location_name")
    String locationName;
    @JsonProperty("time_zone")
    String timeZone;
    List<String> components = null;
    @JsonProperty("config_dir")
    String configDir;
    @JsonProperty("whitelist_external_dirs")
    List<String> whitelistExternalDirs = null;
    @JsonProperty("allowlist_external_dirs")
    List<String> allowlistExternalDirs = null;
    @JsonProperty("allowlist_external_urls")
    List<Object> allowlistExternalUrls = null;
    String version;
    @JsonProperty("config_source")
    String configSource;
    @JsonProperty("safe_mode")
    Boolean safeMode;
    String state;
    @JsonProperty("external_url")
    Object externalUrl;
    @JsonProperty("internal_url")
    Object internalUrl;
    String currency;
}


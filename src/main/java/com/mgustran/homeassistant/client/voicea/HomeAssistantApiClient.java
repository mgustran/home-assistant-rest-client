package com.mgustran.homeassistant.client.voicea;

import com.mgustran.homeassistant.client.base.BaseResponse;
import com.mgustran.homeassistant.client.exception.HaException;
import com.mgustran.homeassistant.model.converters.OriginalCalendarEventToOptimizedConverter;
import com.mgustran.homeassistant.model.converters.OriginalDomainToOptimizedDomainConverter;
import com.mgustran.homeassistant.model.converters.OriginalIntentResponseToOptimizedConverter;
import com.mgustran.homeassistant.model.optimized.*;
import com.mgustran.homeassistant.model.original.OriginalHaCalendarEvent;
import com.mgustran.homeassistant.model.original.OriginalHaDomain;
import com.mgustran.homeassistant.model.original.OriginalHaIntentResponse;
import lombok.NonNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.mgustran.homeassistant.client.util.HeadersUtils.Header.JSON;
import static com.mgustran.homeassistant.client.util.HeadersUtils.Header.TEXT;

public class HomeAssistantApiClient extends HomeAssistantApiClientBase {

    public HomeAssistantApiClient() {
        super();
    }

    public boolean isApiRunning() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, TEXT);
        final BaseResponse<String> response = this.baseClient.execute(
                this.host + "/api/", "GET",
                headers,null);

        return response.getResponseCode() == 200;
    }

    public HaConfig getConfig() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaConfig> response = this.baseClient.execute(
                this.host + "/api/config", "GET",
                headers,null, HaConfig.class);

        return response.getResponse();
    }

    public List<HaEvent> getEvents() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaEvent[]> response = this.baseClient.execute(
                this.host + "/api/events", "GET",
                headers,null, HaEvent[].class);

        return List.of(response.getResponse());
    }

    public List<HaDomain> getServicesGroupedByDomain() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<OriginalHaDomain[]> response = this.baseClient.execute(
                this.host + "/api/services", "GET",
                headers,null, OriginalHaDomain[].class);

        return new OriginalDomainToOptimizedDomainConverter().convertList(List.of(response.getResponse()));
    }

    public List<HaHistoryEntry> getHistory(final LocalDateTime dateFrom, final LocalDateTime dateTo, final List<String> entityIds) throws HaException {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        final String dateFromStr = dateFrom != null ? ("/" + ZonedDateTime.of(dateFrom, this.tz).format(dateFormat)) : "";
        String query = "?" + (dateTo != null ? "end_time=" + URLEncoder.encode(ZonedDateTime.of(dateTo, this.tz).format(dateFormat), StandardCharsets.UTF_8) + "&" : "");
        query = query + (entityIds != null && !entityIds.isEmpty() ? "filter_entity_id=" + String.join(",", entityIds) : "");
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaHistoryEntry[][]> response = this.baseClient.execute(
                this.host + "/api/history/period" + dateFromStr + query, "GET",
                headers,null, HaHistoryEntry[][].class);

        return List.of(response.getResponse()[0]);
    }

    public List<HaLogBookEntry> getLogBook(final LocalDateTime dateFrom, final LocalDateTime dateTo, final String entityId) throws HaException {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        final String dateFromStr = dateFrom != null ? ("/" + ZonedDateTime.of(dateFrom, this.tz).format(dateFormat)) : "";
        String query = "?" + (dateTo != null ? "end_time=" + URLEncoder.encode(ZonedDateTime.of(dateTo, this.tz).format(dateFormat), StandardCharsets.UTF_8) + "&" : "");
        query = query + (entityId != null && !entityId.isEmpty() ? "entity=" + entityId : "");
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaLogBookEntry[]> response = this.baseClient.execute(
                this.host + "/api/logbook" + dateFromStr + query, "GET",
                headers,null, HaLogBookEntry[].class);

        return List.of(response.getResponse());
    }

    public List<HaState> getStates() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaState[]> response = this.baseClient.execute(
                this.host + "/api/states", "GET",
                headers,null, HaState[].class);

        return Arrays.asList(response.getResponse());
    }

    public HaState getState(@NonNull final String uniqueId) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaState> response = this.baseClient.execute(
                this.host + "/api/states/" + uniqueId, "GET",
                headers,null, HaState.class);

        return response.getResponse();
    }

    public String getErrorLog() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, TEXT);
        final BaseResponse<String> response = this.baseClient.execute(
                this.host + "/api/error_log", "GET",
                headers,null);

        return response.getResponse();
    }

    public String getImageFromCamera(@NonNull final String uniqueId) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token);
        final BaseResponse<String> response = this.baseClient.execute(
                this.host + "/api/camera_proxy/" + uniqueId, "GET",
                headers,null);

        return response.getResponse();
    }

    public List<HaCalendar> getCalendars() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<HaCalendar[]> response = this.baseClient.execute(
                this.host + "/api/calendars", "GET",
                headers,null, HaCalendar[].class);

        return Arrays.asList(response.getResponse());
    }

    public List<HaCalendarEvent> getCalendarEvents(@NonNull final LocalDateTime dateFrom, @NonNull final LocalDateTime dateTo, final String entityId) throws HaException {
        final OriginalCalendarEventToOptimizedConverter converter = new OriginalCalendarEventToOptimizedConverter();
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        final String dateFromStr = ZonedDateTime.of(dateFrom, this.tz).format(dateFormat);
        final String dateToStr = ZonedDateTime.of(dateTo, this.tz).format(dateFormat);
        String query = "?start=" + URLEncoder.encode(dateFromStr, StandardCharsets.UTF_8);
        query = query + "&end=" + URLEncoder.encode(dateToStr, StandardCharsets.UTF_8);
        query = query + (entityId != null && !entityId.isEmpty() ? "&entity=" + entityId : "");
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON);
        final BaseResponse<OriginalHaCalendarEvent[]> response = this.baseClient.execute(
                this.host + "/api/calendars/" + entityId + query, "GET",
                headers,null, OriginalHaCalendarEvent[].class);

        return converter.convertList(List.of(response.getResponse()));
    }




//    Returns 200 if updated or 201 if created
    public BaseResponse<HaState> createOrUpdateState(@NonNull final HaState haState) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON, JSON);

        return this.baseClient.execute(
                this.host + "/api/states/" + haState.getEntityId(), "POST",
                headers, haState, HaState.class);
    }

    public BaseResponse<String> fireEvent(@NonNull final String eventType, final LinkedHashMap<String, Object> eventData) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON, JSON);

        return this.baseClient.execute(
                this.host + "/api/events/" + eventType, "POST",
                headers, eventData);
    }

    public List<HaState> callService(@NonNull final String domainAndService, @NonNull final LinkedHashMap<String, Object> serviceData) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON, JSON);
        final BaseResponse<HaState[]> response = this.baseClient.execute(
                this.host + "/api/services/" + domainAndService, "POST",
                headers, serviceData, HaState[].class);

        return List.of(response.getResponse());
    }

    public List<HaState> callServiceByEntityId(@NonNull final String domainAndService, @NonNull final String serviceData) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, JSON, JSON);
        final LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("entity_id", serviceData);
        final BaseResponse<HaState[]> response = this.baseClient.execute(
                this.host + "/api/services/" + domainAndService, "POST",
                headers, data, HaState[].class);

        return List.of(response.getResponse());
    }

    public String renderTemplate(@NonNull final String templateMessage) throws HaException {
        final LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("template", templateMessage);
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, TEXT, JSON);
        final BaseResponse<String> response = this.baseClient.execute(
                this.host + "/api/template", "POST",
                headers, body);

        return response.getResponse();
    }

    // return null if everything is okay else return error content
    public String checkConfigurationAndReturnErrors() throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, TEXT, JSON);
        final BaseResponse<HaConfigCheck> response = this.baseClient.execute(
                this.host + "/api/config/core/check_config", "POST",
                headers, null, HaConfigCheck.class);

        return response.getResponse().getResult().equals("valid") ? null : response.getResponse().getErrors();
    }

    // return null if everything is okay else return error content
    public HaIntentResponse handleIntent(@NonNull final HaIntent intent) throws HaException {
        final HashMap<String, String> headers = this.headersUtils.generateHeaders(this.token, TEXT, JSON);
        final BaseResponse<OriginalHaIntentResponse> response = this.baseClient.execute(
                this.host + "/api/intent/handle", "POST",
                headers, intent, OriginalHaIntentResponse.class);

        return new OriginalIntentResponseToOptimizedConverter().convert(response.getResponse());
    }
}

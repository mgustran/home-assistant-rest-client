package com.mgustran.homeassistant.client;


import com.mgustran.homeassistant.client.base.BaseResponse;
import com.mgustran.homeassistant.client.exception.HaException;
import com.mgustran.homeassistant.client.voicea.HomeAssistantApiClient;
import com.mgustran.homeassistant.model.optimized.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeAssistantApiClientTest {

    private final HomeAssistantApiClient homeAssistantApiClient = new HomeAssistantApiClient();

    @Test
    @DisplayName("/api/  ->  Health check")
    void checkHealth() throws HaException {
        boolean ok = this.homeAssistantApiClient.isApiRunning();
        assertTrue(ok, "Response must be true");
    }

    @Test
    @DisplayName("/api/config  ->  Config check")
    void getConfig() throws HaException {
        final HaConfig config = this.homeAssistantApiClient.getConfig() ;
        assertNotNull(config, "Response must be true");
    }

    @Test
    @DisplayName("/api/events  ->  Retrieve all events")
    void getEvents() throws HaException {
        List<HaEvent> events = this.homeAssistantApiClient.getEvents();
        assertNotNull(events, "Response list must not be null");
        assertTrue(events.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/services  ->  Retrieve services list grouped by domain")
    void getServices() throws HaException {
        List<HaDomain> states = this.homeAssistantApiClient.getServicesGroupedByDomain();
        assertNotNull(states, "Response list must not be null");
        assertTrue(states.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/history/period/<timestamp>  ->  Retrieve history with default params")
    void getHistoryDefaultParams() throws HaException {
        List<HaHistoryEntry> history = this.homeAssistantApiClient.getHistory(null, null, null);
        assertNotNull(history, "Response list must not be null");
        assertTrue(history.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/history/period/<timestamp>  ->  Retrieve history for entity with default params")
    void getHistoryByEntityDefaultDate() throws HaException {
        final List<String> entityList = List.of("zone.home", "sun.sun");
        List<HaHistoryEntry> history = this.homeAssistantApiClient.getHistory(null, null, entityList);
        assertNotNull(history, "Response list must not be null");
        assertTrue(history.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/history/period/<timestamp>  ->  Retrieve history from date")
    void getHistoryByFromDate() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 10, 22, 0, 0);
        List<HaHistoryEntry> history = this.homeAssistantApiClient.getHistory(dateFrom, null, null);
        assertNotNull(history, "Response list must not be null");
        assertTrue(history.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/history/period/<timestamp>  ->  Retrieve history from - to date")
    void getHistoryByFromToDate() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 9, 1, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2022, 11, 1, 0, 0);
        List<HaHistoryEntry> history = this.homeAssistantApiClient.getHistory(dateFrom, dateTo, null);
        assertNotNull(history, "Response list must not be null");
        assertTrue(history.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/history/period/<timestamp>  ->  Retrieve history from - to date by entity")
    void getHistoryByFromToDateForAnEntity() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 9, 1, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2022, 11, 1, 0, 0);
        final List<String> entityList = List.of("zone.home", "sun.sun");
        List<HaHistoryEntry> history = this.homeAssistantApiClient.getHistory(dateFrom, dateTo, entityList);
        assertNotNull(history, "Response list must not be null");
        assertTrue(history.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/logbook/period/<timestamp>  ->  Retrieve LogBook with default params")
    void getLogbookDefaultParams() throws HaException {
        List<HaLogBookEntry> logBook = this.homeAssistantApiClient.getLogBook(null, null, null);
        assertNotNull(logBook, "Response list must not be null");
        assertTrue(logBook.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/logbook/period/<timestamp>  ->  Retrieve LogBook for entity with default params")
    void getLogbookByEntityDefaultDate() throws HaException {
        final String entity = "light.bedroom_light";
        List<HaLogBookEntry> logBook = this.homeAssistantApiClient.getLogBook(null, null, entity);
        assertNotNull(logBook, "Response list must not be null");
        assertTrue(logBook.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/logbook/period/<timestamp>  ->  Retrieve LogBook from date")
    void getLogbookByFromDate() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 10, 22, 0, 0);
        List<HaLogBookEntry> logBook = this.homeAssistantApiClient.getLogBook(dateFrom, null, null);
        assertNotNull(logBook, "Response list must not be null");
        assertTrue(logBook.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/logbook/period/<timestamp>  ->  Retrieve LogBook from - to date")
    void getLogbookByFromToDate() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 9, 1, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2022, 11, 1, 0, 0);
        List<HaLogBookEntry> logBook = this.homeAssistantApiClient.getLogBook(dateFrom, dateTo, null);
        assertNotNull(logBook, "Response list must not be null");
        assertTrue(logBook.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/logbook/period/<timestamp>  ->  Retrieve LogBook from - to date by entity")
    void getLogbookByFromToDateForAnEntity() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 10, 22, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2022, 11, 1, 0, 0);
        final String entity = "light.bedroom_light";
        List<HaLogBookEntry> logBook = this.homeAssistantApiClient.getLogBook(dateFrom, dateTo, entity);
        assertNotNull(logBook, "Response list must not be null");
        assertTrue(logBook.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/states  ->  Retrieve state list")
    void getStates() throws HaException {
        List<HaState> states = this.homeAssistantApiClient.getStates();
        assertNotNull(states, "Response list must not be null");
        assertTrue(states.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/states/<entity_id>  ->  Retrieve state by entity")
    void getStatesByEntity() throws HaException {
        final String entity = "light.bedroom_light";
        HaState state = this.homeAssistantApiClient.getState(entity);
        assertNotNull(state, "Response must not be null");
    }

    @Test
    @DisplayName("/api/error_log  ->  Retrieve error log")
    void getErrorLog() throws HaException {
        String errorLog = this.homeAssistantApiClient.getErrorLog();
        assertNotNull(errorLog, "Response must not be null");
    }

    @Test
    @DisplayName("/api/camera_proxy/<camera entity_id>  ->  Retrieve camera image")
    void getCameraProxyImage() throws HaException {
        final String entity = "camera.mqtt_camera";
        String imageFromCamera = this.homeAssistantApiClient.getImageFromCamera(entity);
        assertNotNull(imageFromCamera, "Response must not be null");
    }

    @Test
    @DisplayName("/api/calendars  ->  Retrieve calendar list")
    void getCalendars() throws HaException {
        List<HaCalendar> calendars = this.homeAssistantApiClient.getCalendars();
        assertNotNull(calendars, "Response list must not be null");
        assertTrue(calendars.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/calendars/<entity_id>  ->  Retrieve calendar events for calendar entity")
    void getCalendarsByEntity() throws HaException {
        final LocalDateTime dateFrom = LocalDateTime.of(2022, 9, 1, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2022, 11, 1, 0, 0);
        final String entity = "calendar.miquel_gustran";
        List<HaCalendarEvent> calendars = this.homeAssistantApiClient.getCalendarEvents(dateFrom, dateTo, entity);
        assertNotNull(calendars, "Response list must not be null");
        assertTrue(calendars.size() > 0, "Response list must not be empty");
    }

    @Test
    @DisplayName("/api/states/<entity_id>  ->  Update state for an entity")
    void createOrUpdateState() throws HaException {
        // does NOT communicate with device, for that use callService()
        final HaState state = HaState.builder()
                .state("off")
                .entityId("light.bedroom_light")
                .build();
        BaseResponse<HaState> response = this.homeAssistantApiClient.createOrUpdateState(state);
        assertNotNull(response, "Response must not be null");
        assertEquals(200, response.getResponseCode(), "Response code must be 200");
    }

    @Test
    @DisplayName("/api/events/<event_type>  ->  Fire event by event type")
    void fireEvent() throws HaException {
        final String eventType = "state_changed";
        BaseResponse<String> response = this.homeAssistantApiClient.fireEvent(eventType, null);
        assertNotNull(response, "Response must not be null");
        assertEquals(200, response.getResponseCode(), "Response code must be 200");
    }

    @Test
    @DisplayName("/api/services/<domain>/<service>  ->  Call service with <domain/service> and service_data")
    void callService() throws HaException {
        final String domainAndService = "light/toggle";
        final LinkedHashMap<String, Object> serviceData = new LinkedHashMap<>();
        serviceData.put("entity_id", "light.bedroom_light");
        List<HaState> states = this.homeAssistantApiClient.callService(domainAndService, serviceData);
        assertNotNull(states, "Response must not be null");
    }

    @Test
    @DisplayName("/api/services/<domain>/<service>  ->  Fire event by event type")
    void callServiceByEntity() throws HaException {
        final String domainAndService = "light/toggle";
        final String entity = "light.bedroom_light";
        List<HaState> states = this.homeAssistantApiClient.callServiceByEntityId(domainAndService, entity);
        assertNotNull(states, "Response must not be null");
    }

    @Test
    @DisplayName("/api/services/<domain>/<service>  ->  Fire event by event type")
    void renderTemplate() throws HaException {
        final String template = "It is {{ now() }}!";
        String text = this.homeAssistantApiClient.renderTemplate(template);
        assertNotNull(text, "Response must not be null");
        assertFalse(text.isBlank(), "Response must not be null");
    }

    @Test
    @DisplayName("/api/config/core/check_config  ->  Config check and return errors")
    void checkConfig() throws HaException {
        String errors = this.homeAssistantApiClient.checkConfigurationAndReturnErrors() ;
        assertNull(errors, "Response must be true");
    }

    @Test
    @DisplayName("/api/intent/handle  ->  Handle an intent")
    void handleIntent() throws HaException {
        final LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Bedroom Light");
        final HaIntent intent = HaIntent.builder()
                .name("HassTurnOn")
                .data(data)
                .build();
        HaIntentResponse intentResponse = this.homeAssistantApiClient.handleIntent(intent) ;
        assertNotNull(intentResponse, "Response must be not null");
    }
}

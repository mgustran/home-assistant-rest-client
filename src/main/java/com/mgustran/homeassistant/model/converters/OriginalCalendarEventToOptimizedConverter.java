package com.mgustran.homeassistant.model.converters;

import com.mgustran.homeassistant.model.optimized.HaCalendarEvent;
import com.mgustran.homeassistant.model.original.OriginalHaCalendarEvent;

import java.util.List;
import java.util.stream.Collectors;

public class OriginalCalendarEventToOptimizedConverter {

    public HaCalendarEvent convert(final OriginalHaCalendarEvent calendarEvent) {
        return HaCalendarEvent.builder()
                .summary(calendarEvent.getSummary())
                .description(calendarEvent.getDescription())
                .location(calendarEvent.getLocation())
                .start(calendarEvent.getStart().getDate())
                .end(calendarEvent.getEnd().getDate())
                .build();
    }

    public List<HaCalendarEvent> convertList(final List<OriginalHaCalendarEvent> calendarEventList) {
        return calendarEventList.stream().map(this::convert).collect(Collectors.toList());
    }
}

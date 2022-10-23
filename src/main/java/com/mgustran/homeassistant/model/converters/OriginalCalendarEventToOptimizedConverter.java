package com.mgustran.homeassistant.model.converters;

import com.mgustran.homeassistant.model.optimized.HaCalendarEvent;
import com.mgustran.homeassistant.model.original.OriginalHaCalendarEvent;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class OriginalCalendarEventToOptimizedConverter {

    public HaCalendarEvent convert(final OriginalHaCalendarEvent calendarEvent) {
        final HaCalendarEvent.HaCalendarEventBuilder builder = HaCalendarEvent.builder();
        if (calendarEvent.getStart().getDateTime() != null) {
            builder.start(calendarEvent.getStart().getDateTime());
        } else if (calendarEvent.getStart().getDate() != null) {
            builder.start(LocalDateTime.of(calendarEvent.getStart().getDate(), LocalTime.of(0, 0)));
        }
        if (calendarEvent.getEnd().getDateTime() != null) {
            builder.end(calendarEvent.getEnd().getDateTime());
        } else if (calendarEvent.getEnd().getDate() != null) {
            builder.end(LocalDateTime.of(calendarEvent.getEnd().getDate(), LocalTime.of(0, 0)));
        }
        return builder
                .summary(calendarEvent.getSummary())
                .description(calendarEvent.getDescription())
                .location(calendarEvent.getLocation())
                .build();
    }

    public List<HaCalendarEvent> convertList(final List<OriginalHaCalendarEvent> calendarEventList) {
        return calendarEventList.stream().map(this::convert).collect(Collectors.toList());
    }
}

package com.epam.spring.core.loggers;

import com.epam.spring.core.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}

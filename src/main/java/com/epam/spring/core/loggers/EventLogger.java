package com.epam.spring.core.loggers;

import com.epam.spring.core.Event;

public interface EventLogger {

    void logEvent(Event event);
}

package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;

public interface EventLogger {

    void logEvent(Event event);
}

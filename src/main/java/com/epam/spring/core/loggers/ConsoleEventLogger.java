package com.epam.spring.core.loggers;

import com.epam.spring.core.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}

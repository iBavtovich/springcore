package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("combinedEventLogger")
public class CombinedEventLogger implements EventLogger {

    @Resource(name="combinedLoggers")
    List<EventLogger> loggers;

    CombinedEventLogger() {}

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

package com.epam.spring.core;

import com.epam.spring.core.loggers.CacheFileEventLogger;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.FileEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    public static void main(String[] args) {

        App app = (App) ctx.getBean("app");

        app.logEvent(null, "HELLO");
        app.logEvent(EventType.INFO, "HELLO INFO");
        app.logEvent(EventType.ERROR, "HELLO Error");

        ctx.close();
    }

    public App(Client cli, EventLogger log, Map<EventType, EventLogger> loggers) {
        this.client = cli;
        this.defaultLogger = log;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        Event event = (Event) ctx.getBean("event");
        event.setMsg(msg);
        logger.logEvent(event);
    }
}

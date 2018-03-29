package com.epam.spring.core;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.beans.Client;
import com.epam.spring.core.spring.AppConfig;
import com.epam.spring.core.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggersMap")
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("com.epam.spring.core");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    public App() {
    }

    public App(Client cli, EventLogger log, Map<EventType, EventLogger> loggers) {
        this.client = cli;
        this.defaultLogger = log;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, Event ev, String msg) {
        String msg2 = msg.replaceAll(client.getId(), client.getFullName());
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        ev.setMsg(msg2);
        logger.logEvent(ev);
    }
}

package com.epam.spring.core;

import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.FileEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger logger;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        EventLogger logger = (FileEventLogger) ctx.getBean("fileEventLogger");

        app.logEvent((Event) ctx.getBean("event"));
        app.logEvent((Event) ctx.getBean("event"));
        app.logEvent((Event) ctx.getBean("event"));
        app.logEvent((Event) ctx.getBean("event"));
        app.logEvent((Event) ctx.getBean("event"));

        ctx.close();
    }

    public App(Client cli, EventLogger log) {
        this.client = cli;
        this.logger = log;

    }

    private void logEvent(Event ev) {
        logger.logEvent(ev);
    }
}

package com.epam.spring.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;
import java.util.Date;

@ToString
public class Event {

    private static int currentId = 0;

    int id;

    @Getter
    @Setter
    private String msg;

    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.id = ++currentId;
        this.date = date;
        this.df = df;
    }
}

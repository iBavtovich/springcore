package com.epam.spring.core.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
@Scope("prototype")
@ToString
public class Event {

    private static int currentId = 0;

    int id;

    @Getter
    @Setter
    private String msg;

    @Autowired
    @Qualifier("newDate")
    private Date date;

    @Autowired
    private DateFormat df;

    public static boolean isDay() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return ((calendar.get(Calendar.HOUR_OF_DAY) <= 17) && (8 <= calendar.get(Calendar.HOUR_OF_DAY))) ? true : false;
    }

    public Event(Date date, DateFormat df) {
        this.id = ++currentId;
        this.date = date;
        this.df = df;
    }
}

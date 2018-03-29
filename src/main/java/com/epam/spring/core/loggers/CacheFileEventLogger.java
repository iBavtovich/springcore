package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component("cacheFileEventLogger")
public class CacheFileEventLogger extends FileEventLogger {

    @Setter
    @Getter
    @Value("${cache.size:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger(){}

    public CacheFileEventLogger(int cacheSize, String fn) {
        super(fn);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event ev) {
        cache.add(ev);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event ev: cache) {
            super.logEvent(ev);
        }
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}

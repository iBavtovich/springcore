package com.epam.spring.core.loggers;

import com.epam.spring.core.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    @Setter
    @Getter
    private int cacheSize;

    private List<Event> cache;

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

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}

package com.epam.spring.core.loggers;

import com.epam.spring.core.Event;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FileEventLogger implements EventLogger{

    @Getter
    @Setter
    private String fileName;

    private File file;

    public FileEventLogger(String fn) {
        this.fileName = fn;
    }

    private void init() throws IOException {
        this.file = new File(fileName);
        file.getAbsolutePath();
        if (!file.canWrite()) {
            throw new IOException();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", "utf-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

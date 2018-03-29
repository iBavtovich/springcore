package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger{

    @Getter
    @Setter
    @Value("C:\\Users\\iBavtovich\\IdeaProjects\\SpringCore\\src\\temp.txt")
    private String fileName;

    private File file;

    public FileEventLogger(){}

    public FileEventLogger(String fn) {
        this.fileName = fn;
    }

    @PostConstruct
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

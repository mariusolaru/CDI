package service;

import entity.Document;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;

@ApplicationScoped
public class FileLoggingService implements Serializable {

    private Path pathLogFile;

    @PostConstruct
    public void init(){
        pathLogFile = Paths.get("/Users/mariusol/Documents/Faculty/AEA/AEA/logs/log.txt");
    }

    public void onUploadDocument(@Observes Document document){
        try{
            Files.write(pathLogFile, Collections.singleton("New document added: " + document.getName() + " at " + new Date() + "\n"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

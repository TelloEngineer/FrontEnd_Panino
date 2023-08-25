package Analyzers.Classes.Supporters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    public String fileToString(String pathfile){
        File file = new File(pathfile);
        if(!file.exists()){ 
           return null;
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathfile)));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}

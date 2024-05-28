package pt.ipbeja.po2.app.model;

import java.io.*;

public class WSRead {
    public String Read(File file){
        StringBuilder formattedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/words.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                formattedContent.append(line.trim()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!formattedContent.isEmpty()) {
            formattedContent.setLength(formattedContent.length() - 1);
        }
        return formattedContent.toString();
    }
}

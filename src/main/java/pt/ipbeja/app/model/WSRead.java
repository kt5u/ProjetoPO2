package pt.ipbeja.app.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WSRead {
    static JFileChooser fileChooser = new JFileChooser();
    public static final String RESOURCES_PATH = "src/main/resources/";
    static List<String> words = new ArrayList<>();

    /*
     *  Asks user to provide a text file
     */
    public static File chooseFile() {
        fileChooser.setDialogTitle("Select a Text File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /*
    *  Copies the file itself to /src/main/resources
    */
    public static void copyFileToResources(File source) {
        if (source == null) {
            throw new IllegalArgumentException("Source file is null");
        }
        File dest = new File(RESOURCES_PATH + source.getName());
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Writes all the words found in the file into a String List
    */
    public static List<String> words(File file) {
        WSRead.copyFileToResources(file);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+"); // Split by whitespace
                Collections.addAll(words, wordsInLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<String> getWords() {
        return words;
    }
}
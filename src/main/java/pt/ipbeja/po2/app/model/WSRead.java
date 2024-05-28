package pt.ipbeja.po2.app.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.Scanner;

public class WSRead {
    public static final String EOL = System.lineSeparator();

    public static String Read(File file){
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine()).append(EOL);
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File not found!");
            alert.setContentText("Error opening file ");
            alert.showAndWait();
        }
        return s.toString();
    }
}

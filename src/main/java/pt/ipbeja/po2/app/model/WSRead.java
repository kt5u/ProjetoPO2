package pt.ipbeja.po2.app.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WSRead {
    public static final String EOL = System.getProperty("line.separator");

    public String read(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append(EOL);
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File not found!");
            alert.setContentText("Error opening file");
            alert.showAndWait();
            Platform.exit(); // System.exit(1);
        }
        return fileContent.toString();
    }
}

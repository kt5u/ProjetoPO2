package pt.ipbeja.po2.app.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.app.model.WSModel;
import pt.ipbeja.po2.app.model.WSRead;

import java.io.File;

public class StartWordSearch extends Application {
    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Fetching textfiles");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", ".tex"));
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            String signText = WSRead.Read(file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select a text file");
            alert.setHeaderText("File content:");
            alert.setContentText(signText);
            alert.showAndWait();
        }

        String boardContent = "ABCDXX\nEFGHXX\nIJKLXX\nMNOPXX";

        WSModel WSModel = new WSModel(boardContent);
        WSBoard WSBoard = new WSBoard(WSModel);

        primaryStage.setScene(new Scene(WSBoard));
        WSModel.registerView(WSBoard);
        WSBoard.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

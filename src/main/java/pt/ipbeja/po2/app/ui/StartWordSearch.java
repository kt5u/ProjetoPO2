package pt.ipbeja.po2.app.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.app.model.BoardContent;
import pt.ipbeja.po2.app.model.WSModel;
import pt.ipbeja.po2.app.model.WSRead;
import java.io.File;
import java.util.List;

public class StartWordSearch extends Application {
    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser(); // Text file fetch
        fileChooser.setTitle("Fetching text files");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", ".tex"));
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {                         // In case text file is blank
            List<String> signText = WSRead.words(file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Text file fetched");
            alert.setHeaderText("File content:");
            alert.setContentText(signText.toString());
            alert.showAndWait();
        }

        BoardContent content = new BoardContent();
        WSRead read = new WSRead();
        WSModel WSModel = new WSModel(content.createLettersGrid(read.words(file), 7, 7)); // board creation with the text file fetched
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
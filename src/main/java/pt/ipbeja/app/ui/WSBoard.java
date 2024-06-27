package pt.ipbeja.app.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import pt.ipbeja.app.model.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Game interface. Just a GridPane of buttons. No images. No menu.
 * @author 25442, 25441
 * @version 2024/04/14
 */
public class WSBoard extends GridPane implements WSView {

    private final WSModel wsModel;
    private final int SQUARE_SIZE = 80;
    WSRead read = new WSRead();
    public Button[][] buttons;
    public Button begin;
    public Button end;
    public List<Button> pressedButtons = new ArrayList<>();

    /*
     * Create a board with letters
     */
    public WSBoard(WSModel wsModel) {
        this.wsModel = wsModel;
        this.buttons = new Button[wsModel.nLines()][wsModel.nCols()];
        this.buildGUI();
    }

    /**
     * Build the interface
     */
    public void buildGUI() {
        // create one button for each position
        for (int line = 0; line < this.wsModel.nLines(); line++) {
            for (int col = 0; col < this.wsModel.nCols(); col++) {
                String textForButton = this.wsModel.textInPosition(new Position(line, col));
                Button button = new Button(textForButton);
                button.setMinWidth(SQUARE_SIZE);
                button.setMinHeight(SQUARE_SIZE);
                button.setOnAction(createButtonClickHandler(button, line, col));
                this.add(button, col, line); // adds button to GridPane
                buttons[line][col] = button; // store the button in the 2D array
            }
        }
        this.requestFocus();
    }

    /*
     * Forms a word from buttons pressed
     */
    public String wordFormed(List<Button> buttons) {
        StringBuilder wordFormed = new StringBuilder();
        for (Button button : buttons) {
            wordFormed.append(button.getText());
        }
        return wordFormed.toString();
    }

    public boolean containsWord(String word) {
        return read.getWords().contains(word);
    }

    /*
     * When button is clicked (color)
     */
    private EventHandler<ActionEvent> createButtonClickHandler(Button button, int line, int col) {
        return event -> {
            button.setStyle("-fx-background-color: yellow");
            pressedButtons.add(button);
            System.out.println(new Position(line, col));
            if (pressedButtons.size() == 1) {
                begin = button;
            } else if (pressedButtons.size() == 2) {
                end = button;
                List<Button> pathButtons = getPathButtons(begin, end);
                String formedWord = wordFormed(pathButtons);
                System.out.println("Formed word: " + formedWord);
                if (containsWord(formedWord)) {
                    System.out.println("Word found: " + formedWord);
                    setButtonsColor(pathButtons, "green");
                } else {
                    System.out.println("Word not found");
                    setButtonsColor(pathButtons, "grey");
                }
                // Reset for next word selection
                resetButtons();
            }
        };
    }

    /*
     * Finds the path to the first button clicked to the second one
     */
    public List<Button> getPathButtons(Button start, Button end) {
        List<Button> pathButtons = new ArrayList<>();

        if (start == null || end == null) {
            return pathButtons;
        }

        int startRow = GridPane.getRowIndex(start);
        int startCol = GridPane.getColumnIndex(start);
        int endRow = GridPane.getRowIndex(end);
        int endCol = GridPane.getColumnIndex(end);

        // Adding start button to the path
        pathButtons.add(start);

        if (startRow == endRow) { // horizontal
            int step = (startCol < endCol) ? 1 : -1; // Determine the direction
            for (int col = startCol + step; col != endCol + step; col += step) {
                pathButtons.add(getButtonAt(startRow, col));
            }
        } else if (startCol == endCol) { // vertical
            int step = (startRow < endRow) ? 1 : -1; // Determine the direction
            for (int row = startRow + step; row != endRow + step; row += step) {
                pathButtons.add(getButtonAt(row, startCol));
            }
        }
        return pathButtons;
    }

    // Helper method to get the button at a specific row and column
    public Button getButtonAt(int line, int col) {
        return buttons[line][col];
    }

    private void setButtonsColor(List<Button> buttons, String color) {
        for (Button btn : buttons) {
            btn.setStyle("-fx-background-color: " + color);
        }
    }

    private void resetButtons() {
        // Reset the list for the next word selection
        for (Button button : pressedButtons) {
            if (!button.getStyle().equals("-fx-background-color: green")) { // if button is not green
                button.setStyle("");
            }
        }

        pressedButtons.clear();
        begin = null;
        end = null;
    }

    @Override
    public void update(MessageToUI messageToUI) {
        // Handle updates from the model if necessary
    }
}
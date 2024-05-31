package pt.ipbeja.app.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import pt.ipbeja.app.model.*;

import java.util.List;

/*
 * Game interface. Just a GridPane of buttons. No images. No menu.
 * @author 25442, 25441
 * @version 2024/04/14
 */
public class WSBoard extends GridPane implements WSView {
    private final WSModel wsModel;
    private static final int SQUARE_SIZE = 80;
    WSRead read = new WSRead();
    private List<Button> buttons;
    private Button firstClickedButton;

    /*
     * Create a board with letters
     */
    public WSBoard(WSModel wsModel) {
        this.wsModel = wsModel;
        this.buildGUI();
    }

    /**
     * Build the interface
     */
        private void buildGUI() {
            // create one label for each position
            for (int line = 0; line < this.wsModel.nLines(); line++) {
                for (int col = 0; col < this.wsModel.nCols(); col++) {
                    String textForButton = this.wsModel.textInPosition(new Position(line, col));
                    Button button = new Button(textForButton);
                    button.setMinWidth(SQUARE_SIZE);
                    button.setMinHeight(SQUARE_SIZE);
                    button.setOnAction(createButtonClickHandler(button));
                    this.add(button, col, line); // adds button to GridPane
                }
            }
            this.requestFocus();
        }

        /*
         * Checks if when all the buttons in between the ones pressed
         * form a word present in the file
         */
        public boolean formsWord(List<Button> buttons, Button begin, Button end) {
            int beginIndex = buttons.indexOf(begin);
            int endIndex = buttons.indexOf(end);

            if (beginIndex == -1 || endIndex == -1 || beginIndex >= endIndex) {
                return false;
            }

            StringBuilder wordFormed = new StringBuilder();
            for (int i = beginIndex + 1; i < endIndex; i++) {
                wordFormed.append(buttons.get(i).getText());
            }

            String wordFormedString = wordFormed.toString();
            return read.getWords().contains(wordFormedString);
        }

    private EventHandler<ActionEvent> createButtonClickHandler(Button button) {
        return event -> {
            button.setStyle("-fx-background-color: yellow");

        };
    }

    @Override
    public void update(MessageToUI messageToUI) {

    }
}

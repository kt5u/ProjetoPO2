package pt.ipbeja.app.ui;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import pt.ipbeja.app.model.MessageToUI;
import pt.ipbeja.app.model.WSView;
import pt.ipbeja.app.model.Position;
import pt.ipbeja.app.model.WSModel;
import javax.swing.*;
import java.awt.*;


/**
 * Game interface. Just a GridPane of buttons. No images. No menu.
 * @author 25442, 25441
 * @version 2024/04/14
 */
public class WSBoard extends GridPane implements WSView {
    private final WSModel wsModel;
    private static final int SQUARE_SIZE = 80;

    /**
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
        assert (this.wsModel != null);

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

    private EventHandler<ActionEvent> createButtonClickHandler(Button button) {
        return event -> {};
    }

    @Override
    public void update(MessageToUI messageToUI) {

    }
}

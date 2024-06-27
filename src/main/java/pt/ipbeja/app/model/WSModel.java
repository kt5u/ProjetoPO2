package pt.ipbeja.app.model;

import javafx.scene.control.Button;
import pt.ipbeja.app.ui.WSBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Game model
 * @author anonymized
 * @version 2024/04/14
 */
public class WSModel {

    private final List<List<String>> lettersGrid;
    WSRead read = new WSRead();
    static WSBoard board;
    private int nLines;
    private int nCols;

    /*
    * Creates the game using a string (this function is mainly used for testing)
    */
    public WSModel(String boardContent) {
        this.lettersGrid = new ArrayList<>();
        lettersGrid.add(new ArrayList<>());
        for(char c : boardContent.toCharArray()) {
            if (c == '\n') lettersGrid.add(new ArrayList<>());
            else lettersGrid.get(lettersGrid.size() - 1).add(c + "");
        }
    }

    /*
    * Creates the game using a list of a String list (2-dimensional List)
    */
    public WSModel(List<List<String>> lettersGrid) {
        this.lettersGrid = lettersGrid;
        for (int i = 0; i < nLines; i++) {
            lettersGrid.add(new ArrayList<>());
            for (int j = 0; j < nCols; j++) {
                lettersGrid.get(i).add("");
            }
        }
    }

    public int nLines() {
        return this.lettersGrid.size();
    }

    public int nCols() {
        return this.lettersGrid.get(0).size();
    }

    public void registerView(WSView wsView) {
    }

    public static int wordsFoundCounter(List<Button> buttons) {

        StringBuilder wordFormed = new StringBuilder();
        int counter = 0;
        for (Button button : buttons) {
            wordFormed.append(button.getText());
        }

        if(WSRead.getWords().contains(wordFormed.toString())) {
            counter++;
        }
        return counter;
    }

    /**
     * Get the text in a position
     * @param position  position
     * @return  the text in the position
     */
    public String textInPosition(Position position) {
        return this.lettersGrid.get(position.line()).get(position.col());
    }

    /**
     * Check if all words were found
     * @return  true if all words were found
     */
    public static boolean allWordsWereFound(List<String> words) {
        return wordsFoundCounter(board.pressedButtons) == words.size();
    }

    /*
     * Check if the word is in the board
    */
     public String wordFound(List<Button> buttons, List<String> words) {
         String word = board.wordFormed(buttons);
         if(words.contains(word)) {
             return word;
         } else {
             return null;
         }
    }
}
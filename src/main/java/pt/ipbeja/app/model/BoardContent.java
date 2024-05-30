package pt.ipbeja.app.model;

import java.util.*;

public class BoardContent {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RAND = new Random();

    /**
     * Generates a random char using a string
     * containing the alphabet, indexing with a random number.
     */
    private static char randomChar() {
        int randomNumber = RAND.nextInt(ALPHABET.length());
        return ALPHABET.charAt(randomNumber);
    }

    /**
     * Creates the board using the words found in the file (inputString)
     * and writes a random char in the blank cells.
     */
    public static List<List<String>> createLettersGrid(List<String> words, int nLines, int nCols) {
        List<List<String>> grid = initializeGrid(nLines, nCols);

        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = RAND.nextBoolean();
                int row = RAND.nextInt(nLines);
                int col = RAND.nextInt(nCols);

                if (canPlaceWord(grid, word, row, col, horizontal)) {
                    placeWord(grid, word, row, col, horizontal);
                    placed = true;
                }
            }
        }
        fillEmptySpaces(grid);
        return grid;
    }

    /*
    * Initializes the button grid using a specified number of lines and columns
    */
    private static List<List<String>> initializeGrid(int nLines, int nCols) {
        List<List<String>> grid = new ArrayList<>(nLines);
        for (int i = 0; i < nLines; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(nCols, ""));
            grid.add(row);
        }
        return grid;
    }

    /*
    * Checks if a word can be placed, either horizontally or vertically
    */
    private static boolean canPlaceWord(List<List<String>> grid, String word, int row, int col, boolean horizontal) {
        int length = word.length();
        if (horizontal) {
            if (col + length > grid.get(0).size()) return false;
            for (int i = 0; i < length; i++) {
                String cell = grid.get(row).get(col + i);
                if (!cell.isEmpty() && !cell.equals(Character.toString(word.charAt(i)))) {
                    return false;
                }
            }
        } else {
            if (row + length > grid.size()) return false;
            for (int i = 0; i < length; i++) {
                String cell = grid.get(row + i).get(col);
                if (!cell.isEmpty() && !cell.equals(Character.toString(word.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * Places the word in the button grid
    */
    private static void placeWord(List<List<String>> grid, String word, int row, int col, boolean horizontal) {
        int length = word.length();
        if (horizontal) {
            for (int i = 0; i < length; i++) {
                grid.get(row).set(col + i, Character.toString(word.charAt(i)));
            }
        } else {
            for (int i = 0; i < length; i++) {
                grid.get(row + i).set(col, Character.toString(word.charAt(i)));
            }
        }
    }

    /*
    * Fills the spaces where words don't fit with random chars
    */
    private static void fillEmptySpaces(List<List<String>> grid) {
        for (List<String> row : grid) {
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).isEmpty()) {
                    row.set(j, Character.toString(randomChar()));
                }
            }
        }
    }
}
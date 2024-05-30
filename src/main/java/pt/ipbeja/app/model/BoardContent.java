package pt.ipbeja.app.model;

import java.util.*;

public class BoardContent {
    private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /*
    * Generates a random char using a string
    * containing the alphabet, indexing with a random number
    */
    private static char RandomChar() {
        Random rand = new Random();
        int RandomNumber = rand.nextInt(alphabet.length());
        return alphabet.charAt(RandomNumber);
    }

    /*
     * Creates the board using the words found in the file (inputString)
     * and writes a random char in the blank cells
     */

    public static List<List<String>> createLettersGrid(List<String> words, int nLines, int nCols) {
        List<List<String>> grid = initializeGrid(nLines, nCols);
        Random rand = new Random();

        for (String word : words) {

            boolean placed = false;
            while (!placed) {
                boolean horizontal = rand.nextBoolean();
                int row = rand.nextInt(nLines);
                int col = rand.nextInt(nCols);

                if (canPlaceWord(grid, word, row, col, horizontal)) {
                    placeWord(grid, word, row, col, horizontal);
                    placed = true;
                }
            }
        }

        fillEmptySpaces(grid);
        return grid;
    }

    private static List<List<String>> initializeGrid(int nLines, int nCols) {
        List<List<String>> grid = new ArrayList<>();
        for (int i = 0; i < nLines; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(nCols, ""));
            grid.add(row);
        }
        return grid;
    }

    private static boolean canPlaceWord(List<List<String>> grid, String word, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + word.length() > grid.get(0).size()) return false;
            for (int i = 0; i < word.length(); i++) {
                if (!grid.get(row).get(col + i).equals("") && !grid.get(row).get(col + i).equals(Character.toString(word.charAt(i)))) {
                    return false;
                }
            }
        } else {
            if (row + word.length() > grid.size()) return false;
            for (int i = 0; i < word.length(); i++) {
                if (!grid.get(row + i).get(col).equals("") && !grid.get(row + i).get(col).equals(Character.toString(word.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeWord(List<List<String>> grid, String word, int row, int col, boolean horizontal) {
        for (int i = 0; i < word.length(); i++) {
            if (horizontal) {
                grid.get(row).set(col + i, Character.toString(word.charAt(i)));
            } else {
                grid.get(row + i).set(col, Character.toString(word.charAt(i)));
            }
        }
    }

    private static void fillEmptySpaces(List<List<String>> grid) {
        Random rand = new Random();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j).equals("")) {
                    grid.get(i).set(j, Character.toString(RandomChar()));
                }
            }
        }
    }
}
package pt.ipbeja.po2.app.model;

import java.util.Random;

public class BoardContent {

    private char RandomChar(){
        Random rand = new Random();
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int RandomNumber = rand.nextInt(alphabet.length() + 1);
        return alphabet.charAt(RandomNumber);
    }

    private String boardContent(String inputString, int size) {
        StringBuilder boardContent = new StringBuilder();
        String[] wordsFoundInFile = inputString.split(" ");

        for(int row = 0; row < size; row++){

        }

        return boardContent.toString();
    }
}

package pt.ipbeja.po2.app.model;

import java.util.Random;

public class BoardContent {

    private String boardcontent;
    private WSRead wsRead;
    private final String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private char getRandomLetter(){
        Random rand = new Random();
        int RandomNumber = rand.nextInt(Alphabet.length() + 1);
        return Alphabet.charAt(RandomNumber);
    }

}

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

        for(int i = 0; i < wordsFoundInFile.length; i++){
            for(int row = 0; row < size; row++){
                if(wordsFoundInFile[i].length() > size){
                    boardContent.append(RandomChar());
                } else {
                    boardContent.append(wordsFoundInFile[i]);
                }
            }
        }
        return boardContent.toString();
    }
}
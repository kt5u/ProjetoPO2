package pt.ipbeja.po2.po2.app.model;


import org.junit.jupiter.api.Test;
import pt.ipbeja.app.model.*;
import pt.ipbeja.app.ui.WSBoard;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WSModelTest {

    @Test
    void testWordFound() {
        WSRead read = new WSRead();
        WSModel model = new WSModel(read.getWords().toString());
        WSBoard board = new WSBoard(model);
        assertEquals("GATO", model.wordFound(board.getPathButtons(board.begin, board.end), read.getWords()));
    }

    @Test
    void testallWordsWereFound() {
       /*
        WSModel model = new WSModel("MALA\nECGH\nIAKL\nMSOP");
        this.registerEmptyView(model);
        assertEquals("MALA", model.wordFound("MALA"));
        assertEquals("CA", model.wordFound("CA"));
       // assertTrue(model.allWordsWereFound());
       */
    }

    private void registerEmptyView(WSModel model) {
        model.registerView(new WSView() {
            @Override
            public void update(MessageToUI message) {
            }
        });
    }
}


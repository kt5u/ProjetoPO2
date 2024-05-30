package pt.ipbeja.po2.po2.app.model;

import org.junit.jupiter.api.Test;
import pt.ipbeja.app.model.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WSModelTest {

    @Test
    void testWordFound() {
        JFileChooser fileChooser = new JFileChooser();
        WSModel model = new WSModel(BoardContent.createLettersGrid(WSRead.words(fileChooser.getSelectedFile()), 7, 7));
        this.registerEmptyView(model);
        assertEquals("PEA", model.wordFound("PEA"));
    }

    @Test
    void testWordWithWildcardFound() {
        WSModel model = new WSModel("MA*A\nEAGH\nISKL\nMSOP");
        this.registerEmptyView(model);
        assertEquals("MALA", model.wordWithWildcardFound("MALA"));
    }

    @Test
    void testallWordsWereFound() {
        WSModel model = new WSModel("MALA\nECGH\nIAKL\nMSOP");
        this.registerEmptyView(model);
        assertEquals("MALA", model.wordFound("MALA"));
        assertEquals("CA", model.wordFound("CA"));
        assertTrue(model.allWordsWereFound());
    }




    private void registerEmptyView(WSModel model) {
        model.registerView(new WSView() {
            @Override
            public void update(MessageToUI message) {
                // do nothing
            }
        });
    }
}


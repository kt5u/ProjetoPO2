package pt.ipbeja.po2.po2.app.model;


import org.junit.jupiter.api.Test;
import pt.ipbeja.app.model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WSModelTest {

    @Test
    void testWordFound() {
        WSModel model = new WSModel(BoardContent.createLettersGrid(WSRead.words(WSRead.chooseFile()), 4, 4));
        this.registerEmptyView(model);
        assertEquals("MALA", model.wordFound("MALA"));
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
            }
        });
    }
}


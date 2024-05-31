package pt.ipbeja.po2.po2.app.model;


import org.junit.jupiter.api.Test;
import pt.ipbeja.app.model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WSModelTest {

    @Test
    void testWordFound() {
        BoardContent board = new BoardContent();
        WSModel model = new WSModel("a"); //TODO
        this.registerEmptyView(model);
        assertEquals("MALA", model.wordFound("MALA"));
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


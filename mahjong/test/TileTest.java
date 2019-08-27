import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    private static List<Tile> all;

    @Before
    @Test
    public void setUp() {
        all = Tile.TileFactory.getAll();
        assertNotNull(all);
    }

    @Test
    void testCreate() {
        assertEquals(34, all.size());
    }

    @Test
    void testNotNull() {
        for (Tile t : all) {
            assertNotNull(t);
        }
    }

    @Test
    void testM() {
        for (int i = 0; i < 9; i++) {
            Tile t = all.get(i);
            assertEquals(Tile.Simple.m, t.simple);
            assertEquals(i + 1, t.number);
        }
    }

    @Test
    void testP() {
        for (int i = 0; i < 9; i++) {
            Tile t = all.get(i + 9);
            assertEquals(Tile.Simple.p, t.simple);
            assertEquals(i + 1, t.number);
        }
    }

    @Test
    void testS() {
        for (int i = 0; i < 9; i++) {
            Tile t = all.get(i + 18);
            assertEquals(Tile.Simple.s, t.simple);
            assertEquals(i + 1, t.number);
        }
    }

    @Test
    void testZ() {
        for (int i = 0; i < 7; i++) {
            Tile t = all.get(i + 27);
            assertEquals(Tile.Simple.z, t.simple);
            assertEquals(i + 1, t.number);
        }
    }

    // TODO: Add the testGet method
}
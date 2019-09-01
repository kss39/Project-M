import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static List<Tile> all;

    // JUnit 5 has deprecated "@Before" and replaced it
    // with "@BeforeEach".
    @BeforeEach
    void setUp() {
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

    @Test
    void testGet() {
        for (int i = 0; i < 9; i++) {
            Tile m = Tile.TileFactory.get(Tile.Simple.m, i + 1);
            Tile p = Tile.TileFactory.get(Tile.Simple.p, i + 1);
            Tile s = Tile.TileFactory.get(Tile.Simple.s, i + 1);
            assertEquals(m, all.get(i));
            assertEquals(p, all.get(i + 9));
            assertEquals(s, all.get(i + 18));
        }

        for (int i = 0; i < 7; i++) {
            Tile z = Tile.TileFactory.get(Tile.Simple.z, i + 1);
            assertEquals(z, all.get(i + 27));
        }
    }

    @Test
    void testWrongIndex() {
        assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(-1));
        assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(34));
    }

    @Test
    void testWrongNumber() {
        assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(Tile.Simple.z, 8));
        assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(Tile.Simple.z, 10));
        assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(Tile.Simple.z, 10));
        for (Tile.Simple simple : Tile.Simple.values()) {
            assertThrows(IllegalArgumentException.class, () -> Tile.TileFactory.get(simple, 0));
        }
    }

    @Test
    void testToInt() {
        for (Tile t : all) {
            assertEquals(t, Tile.TileFactory.get(t.toInt()));
        }
    }

    @Test
    void testToString() {
        for (Tile t : all) {
            String expected = t.number + t.simple.toString();
            assertEquals(expected, t.toString());
        }
    }

    @Test
    void testCompareTo() {
        // Test reflexive
        for (Tile t : all) {
            assertEquals(0, t.compareTo(t));
        }

        // Test symmetric
        for (Tile t1 : all) {
            for (Tile t2 : all) {
                assertEquals(t1.compareTo(t2), -t2.compareTo(t1));
                assertEquals(t1.toInt() - t2.toInt(), t1.compareTo(t2));
            }
        }
    }

    @Test
    void testHashCode() {
        for (Tile t : all) {
            assertEquals(t.toInt(), t.hashCode());
        }
    }

    @Test
    void testEquals() {
        for (Tile t : all) {
            assertEquals(t, t);
            for (Tile t2 : all) {
                assertEquals(t.equals(t2), t2.equals(t));
            }
        }
    }
}
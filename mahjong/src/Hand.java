import java.util.ArrayList;
import java.util.List;

/**
 * {@code Hand} represents the hand of a single player.
 * It contains 13 tiles and 1 optional drawn tile.
 */
public class Hand {
    private final List<Tile> hand;

    public Hand(String tiles) {
        hand = parseString(tiles);
    }

    /**
     * Converts the String {@code str} to a list of {@code Tiles}.
     * The string must be in the format: "...m...p...s...z".
     * For example, "112233m406p333s11z".
     * @param str
     * @return
     */
    public static List<Tile> parseString(String str) {
        List<Tile> out = new ArrayList<>();
        return null;
        // TODO: Finish this parser
    }
}

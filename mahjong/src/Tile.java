import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tiles are the cards used in a Mahjong game.
 * Class Tile represents the immutable Tile ADT and stores
 * the {@code Simple} and the value in it.
 */
public class Tile implements Comparable<Tile>{

    // Since Mahjong has only 34 kinds of Tiles, it is resourceful
    // to use interning when acquiring Tiles instead of creating
    // new Tiles.
    /**
     * Class TileFactory stores all kinds of Tiles needed in
     * a Mahjong game and use interning to eliminate object
     * duplicates (since Tile is an immutable object)
     */
    public static class TileFactory {
        // Stores the Tiles.
        private static final List<Tile> tiles = init();

        // Initialize the tiles. This should be only called
        // once.
        // Returns the initialized list of Tile.
        private static List<Tile> init() {
            List<Tile> result = new ArrayList<>();
            for (Simple s : Simple.values()) {
                if (s == Simple.z) {
                    for (int i = 1; i <= 7; i++) {
                        result.add(new Tile(Simple.z, i));
                    }
                } else {
                    for (int i = 1; i <= 9; i++) {
                        result.add(new Tile(s, i));
                    }
                }
            }
            return result;
        }

        /**
         * Returns the Tile of {@code simple} and {@code number}.
         *
         * @param simple the Simple of the Tile
         * @param number the number of the Tile
         * @throws IllegalArgumentException if the {@code Tile} isn't
         *         valid
         * @return the Tile of {@code simple} and {@code number}
         */
        public static Tile get(Simple simple, int number) {
            if (Simple.checkInvalid(simple, number)) {
                throw new IllegalArgumentException("Invalid values");
            }
            return tiles.get(simple.ordinal() * 9 + number - 1);
        }

        /**
         * Returns the {@code Tile} of the given {@code Index}.
         *
         * @param index the index of the Tile
         * @throws IllegalArgumentException if the {@code index} of
         *         the {@code Tile} isn't valid
         * @return the {@code Tile} of the given {@code Index}.
         */
        public static Tile get(int index) {
            if (index < 0 || index > 33) {
                throw new IllegalArgumentException("Invalid index type");
            }
            return tiles.get(index);
        }

        /**
         * Returns all kinds of {@code Tiles}.
         * @return all kinds of {@code Tiles}.
         */
        public static List<Tile> getAll() {
            return Collections.unmodifiableList(tiles);
        }
    }

    /**
     * The {@code Simple} of the {@code Tile}.
     */
    public final Simple simple;

    /**
     * The number of the {@code Tile}.
     */
    public final int number;

    // Should only be used in the TileFactory.
    private Tile(Simple simple, int number) {
        if (Simple.checkInvalid(simple, number)) {
            throw new IllegalStateException("Invalid value");
        }
        this.simple = simple;
        this.number = number;
    }

    // Returns the official representation of the Tile.
    // For example, "4p" stands for 四饼.
    public String toString() {
        return number + simple.name();
    }

    // Returns the index of the Tile.
    public int toInt() {
        return simple.ordinal() * 9 + number - 1;
    }

    public int compareTo(Tile other) {
        return toInt() - other.toInt();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Tile)) {
            return false;
        }
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return this.toInt();
    }

    /**
     * {@code Simple} is the enum type used for four different
     * "simple" in Mahjong.
     * For your information,
     * m - 万子
     * p - 饼子
     * s - 索子
     * z - 字牌，东西南北中发白(1234567, respectively)
     */
    // Use Simple.m if you need
    // for Java Enum syntax.
    public enum Simple {
        m, p, s, z;

        /**
         * Returns {@code true} if the {@code Tile} is invalid;
         * {@code false} otherwise.
         *
         * @param simple the {@code Simple} of the {@code Tile}
         * @param number the number of the {@code Tile}
         * @return {@code true} if the {@code Tile} is invalid;
         *         {@code false} otherwise
         */
        public static boolean checkInvalid(Simple simple, int number) {
            if (simple == Simple.z) {
                return number <= 0 || number >= 8;
            } else {
                return number <= 0 || number >= 10;
            }
//            switch(simple) {
//                case z: return number > 0 && number < 8;
//                default: return number > 0 && number < 10;
//            }
        }
    }
}

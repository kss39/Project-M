public class Tile implements Comparable<Tile>{
    private Simple simple;
    private int number;

    public Tile(Simple simple, int number) {
        if (!Simple.checkValid(simple, number)) {
            throw new IllegalStateException("Invalid number value");
        }
        this.simple = simple;
        this.number = number;
    }

    public String toString() {
        return number + simple.name();
    }

    public int toInt() {
        return simple.ordinal() * 10 + number;
    }

    public int compareTo(Tile other) {
        return toInt() - other.toInt();
    }

    private static enum Simple {
        m, p, s, z;

        public static boolean checkValid(Simple simple, int number) {
            switch(simple) {
                case z: return number > 0 && number < 8;
                default: return number > 0 && number < 10;
            }
        }
    }
}


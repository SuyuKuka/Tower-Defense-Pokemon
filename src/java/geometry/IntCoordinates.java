package java.geometry;

public record IntCoordinates(int x, int y) {

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}

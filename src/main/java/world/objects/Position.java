package world.objects;


public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Checks if the instance of this class x and y coordinates
     * are within a box constraint
     * @param topLeftCorner The top left corner of the box.
     * @param bottomRightCorner The bottom right corner of the box.
     * @return true if it is else false.
     */
    public boolean isIn(Position topLeftCorner, Position bottomRightCorner) {
        int topX = topLeftCorner.getX();
        int topY = topLeftCorner.getY();
        int bottomX = bottomRightCorner.getX();
        int bottomY = bottomRightCorner.getY();
        return (topX < this.x && this.x < bottomX)
                && (bottomY < this.y && this.y < topY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

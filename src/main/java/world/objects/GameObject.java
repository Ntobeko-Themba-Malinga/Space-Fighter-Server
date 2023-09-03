package world.objects;

public abstract class GameObject {
    private final Position topLeftCorner;
    private final Position bottomRightCorner;

    /**
     * Makes sure all games objects have top left corner and bottom right corner positions
     * @param topLeftCorner An objects top left corner position.
     * @param bottomRightCorner An objects bottom right corner position.
     */
    public GameObject(Position topLeftCorner, Position bottomRightCorner) {
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }

    /**
     * Checks if a game object does or doesn't block the position of another game object.
     * @param pos The position to check if a game object blocks
     * @return true if it does else false
     */
    public boolean blocksPosition(Position pos) {
        int topX = topLeftCorner.getX();
        int topY = topLeftCorner.getY();
        int bottomX = bottomRightCorner.getX();
        int bottomY = bottomRightCorner.getY();
        int posX = pos.getX();
        int posY = pos.getY();
        return (topX <= posX && posX <= bottomX) && (bottomY <= posY && posY <= topY);
    }

    /**
     * Checks if a game object does or doesn't block the path of another game object.
     * @param pos1 The starting position of the path.
     * @param pos2 The ending position of the path.
     * @return true if it does else false.
     */
    public boolean blocksPath(Position pos1, Position pos2) {
        int topX = topLeftCorner.getX();
        int topY = topLeftCorner.getY();
        int bottomX = bottomRightCorner.getX();
        int bottomY = bottomRightCorner.getY();
        int pos1X = pos1.getX();
        int pos1Y = pos1.getY();
        int pos2X = pos2.getX();
        int pos2Y = pos2.getY();

        boolean topEdgeBlocked = (pos1Y <= topX && topX <= pos2.getY())
                && (topX <= pos1X && pos1X <= bottomX);

        boolean bottomEdgeBlocked = (pos2Y <= bottomY && bottomY <= pos1Y)
                && (topX <= pos1X && pos1X <= bottomX);

        boolean leftEdgeBlocked = (pos1.getX() <= topX && topX < pos2X)
                && (bottomY <= pos1Y && pos1Y <= topY);

        boolean rightEdgeBlocked = (pos2X <= topX && topX < pos1X)
                && (bottomY <= pos1Y && pos1Y <= topY);

        return topEdgeBlocked || bottomEdgeBlocked || leftEdgeBlocked || rightEdgeBlocked;

    }

    /**
     * Returns the top left corner position of a game object.
     * @return top left corner position instance
     */
    public Position getTopLeftCorner() {
        return topLeftCorner;
    }

    /**
     * Returns the bottom right corner position of a game object.
     * @return bottom right corner position instance
     */
    public Position getBottomRightCorner() {
        return bottomRightCorner;
    }
}

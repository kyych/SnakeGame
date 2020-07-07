package models;

import javafx.scene.layout.StackPane;

public class PieceOfSnakePane extends StackPane {
    private Position position;
    private boolean isHead = false;
    private Direction direction;

    public PieceOfSnakePane(Position position, boolean isHead, Direction direction) {
        this.position = position;
        this.isHead = isHead;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

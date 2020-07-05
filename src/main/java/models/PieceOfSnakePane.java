package models;

import javafx.scene.layout.StackPane;

public class PieceOfSnakePane extends StackPane {
    private Position position;
    private boolean isHead = false;
    private Direction direction;

    public PieceOfSnakePane(Position position, boolean isHead) {
        this.position = position;
        this.isHead = isHead;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

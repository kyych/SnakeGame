package models;

import javafx.scene.layout.StackPane;

public class PieceOfSnakePane extends StackPane {
    private Position position;
    private boolean isHead = false;

    public PieceOfSnakePane(Position position, boolean isHead) {
        this.position = position;
        this.isHead = isHead;
    }

    public Position getPosition() {
        return position;
    }
}

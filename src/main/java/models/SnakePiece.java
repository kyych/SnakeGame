package models;

public class SnakePiece {
    private Position position;

    public SnakePiece(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

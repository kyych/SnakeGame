package models;

import javafx.scene.layout.StackPane;

public class SnackField extends StackPane {
    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private String color = "-fx-background-color: red;";
    public SnackField(int x, int y) {
        super();
        this.y =y;
        this.x =x;
        this.setStyle(color);
    }
}

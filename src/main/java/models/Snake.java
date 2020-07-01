package models;

import java.util.Random;

public class Snake {
    private Position position;
    private Direction headDirection;    // which direction the snake is moving
    private int length;

    public Snake(Position startPosition, Direction headDirection, int length) {
        this.position = startPosition;
        this.headDirection = headDirection;
        this.length = length;
    }

    public void move(int x, int y){
        position.updatePositon(x,y);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

//    public Position randomizePosition(){
////        Random random = new Random();
////        move(random.nextInt()*);
//    }
}

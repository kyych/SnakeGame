package models;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private Position position;
    private Direction headDirection;    // which direction the snake is moving
    private String color = "-fx-background-color: forestgreen;";
//    private int length;

    private ArrayList<PieceOfSnakePane> snakePieces = new ArrayList<PieceOfSnakePane>();

    public ArrayList<PieceOfSnakePane> getSnakePieces() {
        return snakePieces;
    }

    //    public Snake(Position startPosition, Direction headDirection, int length) {
//        this.position = startPosition;
//        this.headDirection = headDirection;
//        this.length = length;
//    }

//    public Snake() {
//        randomizePosition();
//    }


//    public void move(int x, int y){
//        position.updatePositon(x,y);
//    }


    public Direction getHeadDirection() {
        return headDirection;
    }

    public void setHeadDirection(Direction headDirection) {
        this.headDirection = headDirection;
        setDirectionOfSnakePieces();
    }

    public void setDirectionOfSnakePieces(){
        for(PieceOfSnakePane piece: snakePieces){
            piece.setDirection(headDirection);
        }
    }

    public Snake(Position position) {
//        this.position = position;
        Direction headDirection= randomizeDirection();
        PieceOfSnakePane firstPiece = new PieceOfSnakePane(position,true, headDirection);  //  constructor, so I bet we are creating first piece
        firstPiece.setStyle(color);
        this.headDirection = headDirection;
        snakePieces.add(firstPiece);
    }


    public Direction randomizeDirection() {
        Random random = new Random();
        int randNumber = random.nextInt(4);
        switch (randNumber){
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.SOUTH;
            case 2:
                return Direction.EAST;
            case 3:
                return Direction.WEST;
            default:
                System.err.println("Cant randomize direction. " + this.getClass());
                return Direction.NORTH;
        }
    }


    public int getLength() {
        return snakePieces.size();
    }

    public void setLength(int length) {
        for(int i=0 ; i < length; i++){
//            snakePieces.add(new SnakePiece());
        }
    }

    public void grow(){
        int moveX=0, moveY=0;
        switch (getHeadDirection()){
            case NORTH:
                moveX=0;
                moveY=1;
                break;
            case SOUTH:
                moveX=0;
                moveY=-1;
                break;
            case WEST:
                moveX=-1;
                moveY=0;
                break;
            case EAST:
                moveX=1;
                moveY=0;
                break;
        }
        PieceOfSnakePane extraPiece = new PieceOfSnakePane(new Position(snakePieces.get(snakePieces.size()-1).getPosition().getX()+moveX,snakePieces.get(snakePieces.size()-1).getPosition().getY()+moveY),false, getSnakePieces().get(getLength()-1).getDirection());
        extraPiece.setStyle(color);
        snakePieces.add(extraPiece);
    }

    public  void randomizePosition(int xBoarder, int yBoarder){  // it should be used to generate snake on beggining of the game
        Random random = new Random();                               // snake then is made out of 1 piece.
//        move(random.nextInt(xBoarder), random.nextInt(yBoarder));
        position.updatePositon(random.nextInt(xBoarder), random.nextInt(yBoarder));
    }

    public void broadCastPositionToNodes(){
        for(int piece = getSnakePieces().size(); piece >1; piece--){
            getSnakePieces().get(piece-1).setPosition(getSnakePieces().get(piece-2).getPosition());
        }
    }

}

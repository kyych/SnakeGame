package models;

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


    public Snake(Position position) {
//        this.position = position;
        PieceOfSnakePane firstPiece = new PieceOfSnakePane(position,true);  //  constructor, so I bet we are creating first piece
        firstPiece.setStyle(color);
        snakePieces.add(firstPiece);
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
        PieceOfSnakePane extraPiece = new PieceOfSnakePane(new Position(snakePieces.get(snakePieces.size()-1).getPosition().getX(),snakePieces.get(snakePieces.size()-1).getPosition().getY()-1),false);
        extraPiece.setStyle(color);
        snakePieces.add(extraPiece);
    }

    public  void randomizePosition(int xBoarder, int yBoarder){  // it should be used to generate snake on beggining of the game
        Random random = new Random();                               // snake then is made out of 1 piece.
//        move(random.nextInt(xBoarder), random.nextInt(yBoarder));
        position.updatePositon(random.nextInt(xBoarder), random.nextInt(yBoarder));
    }
}

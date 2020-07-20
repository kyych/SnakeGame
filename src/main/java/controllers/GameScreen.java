package controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import models.*;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen{
    private int heigh=40, width=40;   //size of grid board that snake move on

    private ArrayList<Position> snakeFracturePositions = new ArrayList<>();

    private Snake snake;
    private ArrayList<SnackField> snacks = new ArrayList<SnackField>();

    @FXML
    private GridPane snakeGridPane;

    @FXML
    public void initialize(){
            initGridBoard(width,heigh);

        Random random = new Random();
        Position initPosition = new Position(random.nextInt(width), random.nextInt(heigh));
        Snake snakee = new Snake(initPosition);


        snakee.grow();
        snakee.grow();
        snakee.grow();
        snakee.grow();
        snakee.grow();
        snakee.grow();
        snakee.grow();
        snakee.grow();

//        placeSnakeOnBoard(new Snake(initPosition));
        placeSnakeOnBoard(snakee);
        placeSnaksOnMap(width,heigh);
        snakeMovingTask(snakee);
        this.snake= snakee;
    }

    private void placeSnaksOnMap(int width, int heigh) {
        int numberOfSnacks = (int)(width*heigh*0.02);
        Random random = new Random();
        for(int i=0; i < numberOfSnacks; i++){
            int x= random.nextInt(width-1);
            int y = random.nextInt(heigh-1);
//            System.out.println(x+", " + y);

            while(getNodeByRowColumnIndex(x,y,snakeGridPane).getStyle().equals("-fx-background-color: red;")){
                x=random.nextInt();
                y=random.nextInt();
            }
//            SnackField snackField = new SnackField(x,y);
//            System.out.println(snackField);
            snacks.add(new SnackField(x,y));
            snakeGridPane.getRowConstraints().remove(getNodeByRowColumnIndex(x,y,snakeGridPane));
            snakeGridPane.getColumnConstraints().remove(getNodeByRowColumnIndex(x,y,snakeGridPane));

            snakeGridPane.getChildren().remove(getNodeByRowColumnIndex(x,y,snakeGridPane));


            System.out.println(snacks.get(i));
//            snakeGridPane.setConstraints(snacks.get(i), snacks.get(i).getX(), snacks.get(i).getY());
            snakeGridPane.setRowIndex(snacks.get(i),snacks.get(i).getX());
            snakeGridPane.setColumnIndex(snacks.get(i),snacks.get(i).getY());

//            snakeGridPane.add(snacks.get(i), snacks.get(i).getX(),snacks.get(i).getY());

            snakeGridPane.getChildren().add(snacks.get(i));

//            System.out.println(getNodeByRowColumnIndex(snacks.get(i).getX(), snacks.get(i).getY(), snakeGridPane));
//            snakeGridPane.getChildren().add(snacks.get(i), snacks.get(i).getX(), snacks.get(i).getY());
//            snakeGridPane.getChildren().addAll(snackField);
//            System.out.println(getNodeByRowColumnIndex(x,y,snakeGridPane));
//            System.out.println(numberOfSnacks+" " + getNodeByRowColumnIndex(x,y,snakeGridPane));
//            snakeGridPane.clearConstraints(snakeGridPane);
        }
    }

    private void snakeMovingTask(Snake snake) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int moveX=0, moveY=0;

                            switch (snake.getHeadDirection()){
                                    case NORTH:
                                        moveX=0;
                                        moveY=-1;
                                        break;
                                    case SOUTH:
                                        moveX=0;
                                        moveY=1;
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

                                snake.broadCastPositionToNodes();

                            // snake eats snack!!!
//                                snakeGridPane.getChildren().remove(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY);
//                            System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane).getClass());
//                            System.out.println(snake.getSnakePieces().get(0).getPosition().getX()+" " +snake.getSnakePieces().get(0).getPosition().getY());
//                            System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane));
//                            System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane).getClass() );
//                            System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane)!=null);
//                            System.out.println(getNodeFromGridPane(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane)==null);
//                            System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getX()+moveX,snakeGridPane);



                            if(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane).getStyle().equals("-fx-background-color: red;")){
                                // nigdy tutaj nie wejddz... snake gdy sie porusza to zmienia klase stackPane na swoją snakePieces...
                                //getNodeByRowColumn zwraca node... TO DLATEGO
//                                System.out.println("ssss");
//                                System.out.println(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane));
                                snakeGridPane.getChildren().remove(getNodeByRowColumnIndex(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY,snakeGridPane));

                                System.out.println("snack");

                                StackPane squareOnGameBoard = new StackPane();
//                                squareOnGameBoard.setStyle("-fx-background-color: #d1c8ae");
                                squareOnGameBoard.setStyle("-fx-background-color: #e8aa00");
                                snakeGridPane.add(squareOnGameBoard, snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY);
                            }
//                            //

                            snake.getSnakePieces().get(0).setPosition(new Position(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY)); //update HEADSnakePiece Object position...

                            for(PieceOfSnakePane snakePiece : snake.getSnakePieces()){
                                snakeGridPane.getChildren().remove(snakePiece);
                                try {
                                    snakeGridPane.add(snakePiece, snakePiece.getPosition().getX(), snakePiece.getPosition().getY());
                                } catch (IllegalArgumentException ex){  // player has gone beyond the map boundary
                                    if (cancel()){
                                        break;
                                    }
                                    break;


                                }
                            }
                            if(snake.getSnakePieces().get(0).getPosition().getX()<0 ||snake.getSnakePieces().get(0).getPosition().getX()>width||
                                    snake.getSnakePieces().get(0).getPosition().getY()>heigh || snake.getSnakePieces().get(0).getPosition().getY()<0){
                                cancel();

                                System.out.println("INSIDE");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("U have reach the border");
                                alert.setContentText("Final score: " + snake.getLength());
                                alert.show();
                            }
                        }
                    });
                    Thread.sleep(70); // run once every 0,1s
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }



    private Node getNodeFromGridPane( int col, int row,GridPane gridPane) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node) == row
                    && GridPane.getColumnIndex(node) == col)
                return node;
        return null;
    }

    private void initGridBoard(int width, int height){
        this.heigh = height;
        this.width = width;

        for(int i=0; i < width; i++){
            for(int j = 0 ; j < height; j++){   //basic stackPane fields
                StackPane squareOnGameBoard = new StackPane();
                squareOnGameBoard.setStyle("-fx-border-color: #eaf5e6");
                snakeGridPane.add(squareOnGameBoard, i, j);
            }
            snakeGridPane.setStyle("-fx-background-color: #d1c8ae");
        }

        for (int i = 0; i < width; i++) {
            snakeGridPane.getColumnConstraints().add(new ColumnConstraints(1, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }

        for(int i = 0 ; i < height;i++) {
            snakeGridPane.getRowConstraints().add(new RowConstraints(1, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    private void placeSnakeOnBoard(Snake snake){
        for(int i=0; i < snake.getLength(); i++) {
            snakeGridPane.getChildren().remove(snake.getSnakePieces().get(i));
            snakeGridPane.add(snake.getSnakePieces().get(i), snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY());
//            snakeGridPane.setRowIndex(snake.getSnakePieces().get(i), snake.getSnakePieces().get(i).getPosition().getX());
//            snakeGridPane.setColumnIndex(snake.getSnakePieces().get(i), snake.getSnakePieces().get(i).getPosition().getY());
            snakeGridPane.setGridLinesVisible(true);
        }
    }
    private Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
//            System.out.println(gridPane.getRowIndex(node)+" " + gridPane.getColumnIndex(node));

            // to check.
            if(gridPane.getColumnIndex(node)!=null && gridPane.getRowIndex(node)!=null && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    private StackPane getSnackFieldByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        SnackField result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
//                result = node;
                if(!(node instanceof SnackField)){
                    System.err.println("Node is not instance of SnackField");
                }
                result = (SnackField)node;
                break;
            }
        }
        return result;
    }



    @FXML
    public void keyPressListener(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case W:
                snake.setHeadDirection(Direction.NORTH);
                break;
            case S:
                snake.setHeadDirection(Direction.SOUTH);
                break;
            case A:
                snake.setHeadDirection(Direction.WEST);
                break;
            case D:
                snake.setHeadDirection(Direction.EAST);
                break;
        }
    }
}

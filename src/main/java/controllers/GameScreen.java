package controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import models.Position;
import models.Snake;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class GameScreen{
    private int heigh, width;   //size of grid board that snake move on

    @FXML
    private GridPane snakeGridPane;

    @FXML
    public void initialize(){
            initGridBoard(16,6);

//            for(Node sPane : snakeGridPane.getChildren()){
//                System.out.println(sPane.toString());
//            }
        Random random = new Random();
        Position initPosition = new Position(random.nextInt(16), random.nextInt(6));
        Snake snakee = new Snake(initPosition);
//        snakee.grow();
//        snakee.grow();
//        placeSnakeOnBoard(new Snake(initPosition));
        placeSnakeOnBoard(snakee);
        snakeMovingTask(snakee);
    }

    private void snakeMovingTask(Snake snake) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
//                    int incrementLengthBy=0;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
//                            System.out.println("Now snake should move");
                            for(int i=0; i < snake.getLength(); i++) {
//                                snakeGridPane.getChildren().remove(getNodeByRowColumnIndex(snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY(), snakeGridPane));
                                snakeGridPane.getChildren().remove(snake.getSnakePieces().get(i));
//        snakeGridPane.add(new Snake(initPosition));

                                // update snake position
                                snake.getSnakePieces().get(i).setPosition(new Position(snake.getSnakePieces().get(i).getPosition().getX()+1, snake.getSnakePieces().get(i).getPosition().getY()));

                                snakeGridPane.getChildren().remove(getNodeByRowColumnIndex(snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY(), snakeGridPane));
                                snakeGridPane.add(snake.getSnakePieces().get(i), snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY());
                            }
                        }
                    });
                    Thread.sleep(1000); // run once every 1s
//                    incrementLengthBy++;
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void initGridBoard(int width, int height){
        this.heigh = height;
        this.width = width;

        for(int i=0; i < width; i++){
            for(int j = 0 ; j < height; j++){   //basic stackPane fields
                StackPane squareOnGameBoard = new StackPane();
                squareOnGameBoard.setStyle("-fx-border-color: darkcyan");
                snakeGridPane.add(squareOnGameBoard, i, j);
            }
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
            snakeGridPane.getChildren().remove(getNodeByRowColumnIndex(snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY(), snakeGridPane));
//        snakeGridPane.add(new Snake(initPosition));
            snakeGridPane.add(snake.getSnakePieces().get(i), snake.getSnakePieces().get(i).getPosition().getX(), snake.getSnakePieces().get(i).getPosition().getY());
        }
    }
    private Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }


}

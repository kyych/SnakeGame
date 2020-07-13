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
import models.Direction;
import models.PieceOfSnakePane;
import models.Position;
import models.Snake;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen{
    private int heigh=40, width=40;   //size of grid board that snake move on

    private ArrayList<Position> snakeFracturePositions = new ArrayList<>();

    private Snake snake;

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
        snakeMovingTask(snakee);
        this.snake= snakee;
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

                            snake.getSnakePieces().get(0).setPosition(new Position(snake.getSnakePieces().get(0).getPosition().getX()+moveX,snake.getSnakePieces().get(0).getPosition().getY()+moveY)); //update HEADSnakePiece Object position...

                            for(PieceOfSnakePane snakePiece : snake.getSnakePieces()){
                                snakeGridPane.getChildren().remove(snakePiece);
                                try {
                                    snakeGridPane.add(snakePiece, snakePiece.getPosition().getX(), snakePiece.getPosition().getY());
                                } catch (IllegalArgumentException ex){
//                                    System.out.println("Wyszedles poza granice mordo");
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

//                                    cancelled();
                                    alert.setTitle("U have reach the border");
                                    alert.setContentText("Final score: " + snake.getLength());
                                    if (cancel()){
                                        System.out.println("CANCELUDALSIE");
                                        break;
                                    }
                                    alert.show();
                                    break;


                                }
                            }
                            if(snake.getSnakePieces().get(0).getPosition().getX()<0 || snake.getSnakePieces().get(0).getPosition().getY()>width){
                                cancel();

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

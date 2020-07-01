package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import models.Snake;


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
//        System.out.println(getNodeByRowColumnIndex(5,15,snakeGridPane));
    }

    private void initGridBoard(int width, int height){
        this.heigh = height;
        this.width = width;

        for(int i=0; i < width; i++){
            for(int j = 0 ; j < height; j++){
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

    }
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
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

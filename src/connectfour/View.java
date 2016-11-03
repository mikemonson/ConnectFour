/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 *
 * @author Mike
 */
public class View extends Application {

    View view;
    BorderPane root = new BorderPane();
    GridPane checkersPane = new GridPane();
    GridPane dropCheckersPane = new GridPane();
    Presenter presenter;
    Circle dropButtonTemplate = new Circle(50);
    VBox gameStatus = new VBox();
    TextField gameStatusText = new TextField("Lets Play Connect Four!");
    Button resetButton = new Button("Reset");
    

    public View() {
    }

    public void setPresenter(Presenter pres) {
        presenter = pres;
    }

    @Override
    public void start(Stage primaryStage) {
        //generate game board
        for (int i = 0; i < presenter.getBoardSize(); i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            checkersPane.getColumnConstraints().add(column);

        }

        for (int i = 0; i < presenter.getBoardSize(); i++) {
            RowConstraints row = new RowConstraints(100);
            checkersPane.getRowConstraints().add(row);
        }

        //generate grid for buttons to drop pieces;
        dropCheckersPane.getRowConstraints().add(new RowConstraints(100));

        for (int i = 0; i < presenter.getBoardSize(); i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            dropCheckersPane.getColumnConstraints().add(column);

        }

        for (int i = 0; i < presenter.getBoardSize(); i++) {
            Button dropButton = new Button();
            dropButton.setShape(dropButtonTemplate);
            dropButton.setMinSize(100, 100);
            dropButton.setMaxSize(100, 100);
            dropButton.setId(String.valueOf(i));
            dropCheckersPane.add(dropButton, i, 0);
            dropButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    presenter.dropGamePiece(Integer.parseInt(dropButton.getId()));
                    System.out.println("Dropping in column " + dropButton.getId());
                }
            });

        }
        gameStatus.setAlignment(Pos.CENTER);
        gameStatus.setPadding(new Insets(40));
        gameStatus.getChildren().add(gameStatusText);
        gameStatus.getChildren().add(resetButton);
        root.setRight(gameStatus);
        checkersPane.setGridLinesVisible(true);
        root.setTop(dropCheckersPane);
        root.setCenter(checkersPane);
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void drawPlayerToken(Integer player, Integer[] location) {
        if (player == 1) {
            //drawBlue
            checkersPane.add(new Circle(50, 50, 50, Color.YELLOW), location[1], location[0]);
          
        } else {
            //drawYellow
            checkersPane.add(new Circle(50, 50, 50, Color.BLUE), location[1], location[0]);

        }
    }
    
    public void updateGameStatusText(Integer player) {
        gameStatusText.setText("Player " + player+"'s Turn");
    }
    
    public void displayWinnerText(Integer player) {
        gameStatusText.setText("Winner! Player "+ player);
    }

    void updateGameStatusInvalidMove() {
        gameStatusText.setText("Sorry, Invalid Move");
    }

}

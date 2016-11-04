/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Mike
 */
public class ConnectFour extends Application {

    @Override
    public void start(Stage primaryStage) {
        Integer x, y;
        final Parameters params = getParameters();
        final List<String> parameters = params.getRaw();
        final String boardSize = !parameters.isEmpty() ? parameters.get(0) : "";
        final String winningCount = !parameters.isEmpty() ? parameters.get(1) : "";
        if (!parameters.isEmpty()) {
             x = Integer.parseInt(boardSize);
             y = Integer.parseInt(winningCount);
            System.out.println(boardSize);
            System.out.println(winningCount);
        }
        else {
            //if no arguments are passed into the jar, game model defaults to 7x7 board and 4 to win
            x = 6;
            y=2;
        }
        
        

        Model gameModel = new Model(x, y);
        View view = new View();
        view.setPresenter(new Presenter(view, gameModel));

        view.start(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}

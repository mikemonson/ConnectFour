/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

/**
 *
 * @author Mike
 */
public class Presenter {

    private Model connectModel;
    private View connectView;

    public Presenter(View connectView, Model connectModel) {
        this.connectModel = connectModel;
        this.connectView = connectView;
    }

    void attachView(View connectView) {
        this.connectView = connectView;

    }

    public Integer getBoardSize() {
        return connectModel.getBoardSize();
    }

    public void dropGamePiece(Integer column) {

        Boolean validLastMove = connectModel.dropGamePiece(column);

        if (validLastMove) {
            //update view
            Integer winCheck = connectModel.checkForWin();

            connectView.drawPlayerToken(connectModel.getPlayerTurn(), connectModel.getLastMoveIndex());

            if (winCheck == 1 || winCheck == 2) {
                //display winner banner in bottom pane
                connectView.displayWinnerText(winCheck);

            } else {
                connectView.updateGameStatusText(connectModel.getPlayerTurn());

            }

        } else {
            connectView.updateGameStatusInvalidMove();
        }

    }

    //TODO IMPLEMNT MODEL/VIEW METHODS
}

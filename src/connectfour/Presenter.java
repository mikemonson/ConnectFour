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
public class Presenter
{

    private Model connectModel;
    private View connectView;

    public Presenter(View connectView, Model connectModel)
    {
        this.connectModel = connectModel;
        this.connectView = connectView;
    }

    void attachView(View connectView)
    {
        this.connectView = connectView;

    }

    public Integer getBoardSize()
    {
        return connectModel.getBoardSize();
    }

    public void dropGamePiece(Integer column)
    {
        if (connectModel.dropGamePiece(column)) {
            //update view

            connectView.drawPlayerToken(connectModel.getPlayerTurn(), connectModel.getLastMoveIndex());
            
            if(connectModel.checkForWin() == 1 || connectModel.checkForWin() == 2 ) {
                //display winner banner in bottom pane
            }
        }

    }

    //TODO IMPLEMNT MODEL/VIEW METHODS
}

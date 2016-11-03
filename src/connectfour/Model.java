/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 *
 * @author Mike
 */
class Model {

    private Integer numToWin ;
    private Integer turnCount = 1;
    public Integer[][] gameBoard;
    private Integer boardSize ;
    private int lastMoveRowIndex;
    private int lastMoveColumnIndex;
    private int playersTurn = 1;

    public Model(Integer theBoardSize, Integer numToWin) {
        this.boardSize = theBoardSize;
        this.numToWin = numToWin;
        turnCount = 1;
        gameBoard = new Integer[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = 0;
            }
        }

    }

    public Integer getTurnCount() {
        return turnCount;
    }

    public Integer getPlayerTurn() {

        if (turnCount % 2 == 1) {
            return 1;
        } else {
            return 2;
        }

    }
    
    public Integer getBoardSize() {
        return boardSize;
    }

    public Boolean dropGamePiece(Integer column) {

        //iterate backwards through chosen column
        for (int i = boardSize - 1; i >= 0; i--) {
            int indexValue = gameBoard[i][column];
            if (indexValue == 0) {
                gameBoard[i][column] = getPlayerTurn();
                turnCount++;
                lastMoveColumnIndex = column;
                lastMoveRowIndex = i;
                printGameArray();
                return true;

            }

        }

        System.out.println("Invalid Move");
        printGameArray();
        return false;

    }

    public int getValueAtBoardIndex(int i, int j) {

        return gameBoard[i][j];
    }

    public void printGameArray() {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Integer checkForWin() {

        if (checkColumnsForWin(1) || checkRowsForWin(1) || checkDiagForWin(1) ) {
            System.out.println("Winner: Player 1");
            return 1;
        }

        if (checkColumnsForWin(2) || checkRowsForWin(2) || checkDiagForWin(2)) {
            System.out.println("Winner: Player 2");

            return 5;
        } 
        else {
            return 0;
        }

    }
    
    public Integer getLastMoveRowIndex() {
        return lastMoveRowIndex;
    }
    
    public Integer[] getLastMoveIndex() {
        Integer[] lastMove = new Integer[2];
        lastMove[0] = lastMoveRowIndex;
        lastMove[1] = lastMoveColumnIndex;
        return lastMove;
    }

    //private recursive helper methods to check for a win.
    private Boolean checkRowsForWin(int checkPlayer) {
        int count = 0;
        //check rows for win
        for (int j = 0; j < boardSize; j++) {

            for (int i = 0; i < boardSize; i++) {
                int indexValue = gameBoard[j][i];
                if (indexValue == checkPlayer) {
                    count++;
                    if (count == numToWin) {
                       // System.out.println("Winner, Player " + checkPlayer);
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private Boolean checkColumnsForWin(int checkPlayer) {
        int count = 0;
        //check columns for win
        for (int j = 0; j < boardSize; j++) {

            for (int i = 0; i < boardSize; i++) {
                int indexValue = gameBoard[i][j];
                if (indexValue == checkPlayer) {
                    count++;
                    if (count == numToWin) {
                        //System.out.println("Winner, Player " + checkPlayer);
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private Boolean checkDiagForWin(int checkPlayer) {

        //check NW to SE diag for consecutive game pieces.
        int nWCount = recursiveCheckNW(lastMoveRowIndex, lastMoveColumnIndex, checkPlayer, gameBoard[lastMoveRowIndex][lastMoveColumnIndex]);
        int totalCount1 = recursiveCheckSE(lastMoveRowIndex, lastMoveColumnIndex, nWCount, gameBoard[lastMoveRowIndex][lastMoveColumnIndex]);
        if (totalCount1 >= numToWin) {
            return true;
        }
        //check NE to SW diag for consseutive game pieces.
        int nECount = recursiveCheckNE(lastMoveRowIndex, lastMoveColumnIndex, checkPlayer, gameBoard[lastMoveRowIndex][lastMoveColumnIndex]);
        int totalCount2 = recursiveCheckSW(lastMoveRowIndex, lastMoveColumnIndex, nECount, gameBoard[lastMoveRowIndex][lastMoveColumnIndex]);
        if (totalCount2 >= numToWin) {
            return true;
        } else {
            return false;
        }

    }

    private int recursiveCheckNW(int rowIndex, int colIndex, int count, int checkPlayer) {
        //base case
        //System.out.println("value at index " + rowIndex + " " + colIndex + " is " + gameBoard[rowIndex][colIndex]);
        if (count == numToWin || colIndex - 1 <= -1 || rowIndex - 1 <= -1 || gameBoard[rowIndex - 1][colIndex - 1] != checkPlayer) {
            return count;
        } else {
            count++;
            return recursiveCheckNW(rowIndex - 1, colIndex - 1, count, checkPlayer);

        }
    }

    private int recursiveCheckSE(int rowIndex, int colIndex, int count, int checkPlayer) {
        //base case
        //System.out.println("value at index " + rowIndex + " " + colIndex + " is " + gameBoard[rowIndex][colIndex]);
        if (count == numToWin || colIndex + 1 >= boardSize || rowIndex + 1 >= boardSize || gameBoard[rowIndex + 1][colIndex + 1] != checkPlayer) {
            return count;
        } else {
            count++;
            return recursiveCheckSE(rowIndex + 1, colIndex + 1, count, checkPlayer);

        }
    }

    private int recursiveCheckNE(int rowIndex, int colIndex, int count, int checkPlayer) {
        //base case
        //System.out.println("value at index " + rowIndex + " " + colIndex + " is " + gameBoard[rowIndex][colIndex]);
        if (count == numToWin || colIndex + 1 >= boardSize || rowIndex - 1 < -1 || gameBoard[rowIndex - 1][colIndex + 1] != checkPlayer) {
            return count;
        } else {
            count++;
            return recursiveCheckNE(rowIndex - 1, colIndex + 1, count, checkPlayer);

        }
    }

    private int recursiveCheckSW(int rowIndex, int colIndex, int count, int checkPlayer) {
        //base case
        //System.out.println("value at index " + rowIndex + " " + colIndex + " is " + gameBoard[rowIndex][colIndex]);
        if (count == numToWin || colIndex - 1 < 0 || rowIndex + 1 >= boardSize || gameBoard[rowIndex + 1][colIndex - 1] != checkPlayer) {
            return count;
        } else {
            count++;
            return recursiveCheckSW(rowIndex + 1, colIndex - 1, count, checkPlayer);

        }
    }

}

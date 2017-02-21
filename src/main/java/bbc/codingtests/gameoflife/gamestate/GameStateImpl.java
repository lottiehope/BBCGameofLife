package bbc.codingtests.gameoflife.gamestate;

import java.util.Arrays;

public class GameStateImpl implements GameState {

    private int noRows = 3;
    private int noCols = 3;
    private String[] board = new String[noRows];

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < noRows; i++){
            if(i != noRows-1) {
                result = result + board[i] + "\n";
            }
            else{
                result = result + board[i];
            }
        }
        return result;

    }

    public GameStateImpl(String input) {
        board = input.split("\\n");
    }

    public boolean isCellAliveAt(int row, int col) {
        if(board[row].charAt(col) == '*'){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRows() {
        return noRows;
    }

    public int getCols() {
        return noCols;
    }
}

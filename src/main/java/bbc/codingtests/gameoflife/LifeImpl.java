package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

public class LifeImpl implements Life
{

	private String[] board;
	private int x_val = 3;
	private int y_val = 3;
	private int squares = x_val*y_val;
	String init_vals;

	/**
	 * Operation to take the grid in the game and evolve all cells depending on how many cells are alive around each.
	 * */
	public GameState evolve(GameState currentState) {
		init_vals = currentState.toString();
		init_vals = init_vals.replace("\n", "");
		int[] neighbours = new int[squares];
		board = currentState.board;
		String[] new_board = new String[squares];
		int counter = 0;
		for(int i = 0; i < x_val; i++){
			for(int j = 0; j < y_val; j++){
				neighbours[counter] = checkNeighbours(i,j, currentState);
				counter++;
			}
		}

		for(int i = 0; i < squares; i++){
			if(neighbours[i] == 0 || neighbours[i] < 2 || neighbours[i] > 3){
				new_board[i] = ".";
			}
			else{
				if(init_vals.charAt(i) == '*') {
					new_board[i] = "*";
				}
				else if(neighbours[i] == 3){
					new_board[i] = "*";
				}
				else{
					new_board[i] = ".";
				}
			}
		}
		String str_board = "";
		for(int i = 0; i < squares; i++){
			str_board = str_board + new_board[i];
			if((i+1)%3==0 && i != (squares-1)){
				str_board = str_board + "\n";
			}
		}
		GameState new_state = new GameStateImpl(str_board);
		return new_state;


	}


	/**
	 * Operation to count the number of cells that surround a given cell that are alive.
	 * */
	public int checkNeighbours(int x, int y, GameState state){

		int counter = 0;

		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++) {
				try {
					if((i == 0) && (j == 0)) {
					}
					else{
						boolean result = state.isCellAliveAt((x + i), (y + j));
						if (result) {
							counter++;
						}
					}
				}
				catch (IndexOutOfBoundsException e) {
				}
			}
		}


		return counter;
	}

}

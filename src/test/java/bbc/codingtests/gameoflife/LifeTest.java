package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {

	//TODO make this test pass
	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		System.out.println(testLife.evolve(emptyState).toString());
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
		System.out.println("empty: " + testLife.evolve(emptyState).toString());
	}

	//TODO implement further tests for the other cases in the instructions

	/**
	 * Operation to perform a generic test on a given input
	 * */
	public String test(String input){


		Life testLife = new LifeImpl();
		GameState testState = new GameStateImpl(input);

		return testLife.evolve(testState).toString();

	}


}

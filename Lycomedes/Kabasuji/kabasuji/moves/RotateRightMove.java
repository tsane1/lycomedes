package kabasuji.moves;

import kabasuji.supers.Level;
import kabasuji.supers.Move;

/**
 * A Move done on a Piece that will rotate the Piece 90 degrees to the right.
 * @author Ian Jacoway
 *
 */

public class RotateRightMove extends Move {
	/** Level being updated. */
	Level currLevel;
	
	/**
	 * Constructor for rotate right move.
	 * @param Level l
	 */
	public RotateRightMove(Level l) {
		this.currLevel = l;
	}

	/**
	 * Handles the execution of the move.
	 */
	@Override
	public boolean execute() {
		if(!valid()) { return false; }
		currLevel.getSelected().rotateRight();
		return true;
	}

	/**
	 * Handles undoing the move if requested.
	 */
	@Override
	public boolean undo() {
		currLevel.getSelected().rotateLeft();
		return true;
	}

	/**
	 * Determines whether or not the move is valid.
	 */
	@Override
	public boolean valid() {
		if(currLevel.getSelected() == null)
			return false;
		return true;
	}
}

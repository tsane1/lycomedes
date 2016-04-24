package kabasuji.moves;

import kabasuji.supers.Level;
import kabasuji.supers.Move;

public class FlipYMove extends Move {
	Level currLevel;
	
	public FlipYMove(Level l) {
		this.currLevel = l;
	}

	@Override
	public boolean execute() {
		if(!valid()) { return false; }
		currLevel.getSelected().flipY();
		return true;
	}

	@Override
	public boolean undo() {
		currLevel.getSelected().flipY();
		return true;
	}

	@Override
	public boolean valid() {
		if(currLevel.getSelected() == null)
			return false;
		return true;
	}
}
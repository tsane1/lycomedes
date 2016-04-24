package kabasuji.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import org.w3c.dom.events.MouseEvent;

import kabasuji.entities.Board;
import kabasuji.moves.SelectTileMove;
import kabasuji.supers.Application;
import kabasuji.supers.Level;
import kabasuji.supers.Move;
import kabasuji.supers.Screen;
import kabasuji.supers.SuperModel;

/**
 * A controller to select tiles as active on a Board with one click, 
 * up to the following 6 clicks it then increment a number to appear on the tile (for release).
 * @author Ian Jacoway
 */

public class TileSelectController implements MouseListener{
	Application app;
	Level level;
	
	public TileSelectController(Application a, SuperModel model){
		this.app = a;
		this.level = model.getActiveLevel();
	}
	
	public boolean selectTile(Point p){
		Move m = new SelectTileMove(level);
		
		if(m.execute()){ // WAITING ON =========REFRESH()======== THEN FUNCTIONAL
			// If appropriate screen then update view
			switch (app.getCurrScreen().getName()){
				case "LevelPlay": case "PuzzleLevelEditView": case "LightningLevelEditView": case "ReleaseLevelEditView":
					app.getCurrScreen().getBoardView();//.refresh();
				default:
					level.trackMove(m);
					;//do nothing/push to undo stack?
			}
		}
//		if(level.getSelected() == null){
//			return false;
//		}
//		if (leftClick.getX() == board.getX()) && ()
//			//turn tile on & if release 
//			if(level.getLevelType() == "Release"){
//				//do increment number on tile
//			}
//		
//		level.get();
//		if (rightClick.getX() == board.getX()){
//			//increment color of number 
//		}
//		
//		app.getCurrScreen().getBoardView().refresh();
		
		return true;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		try{
			// increment a counter in selectTile for every tile being selected that is already selected
			if (e.getSource() == app.getCurrScreen().getBoardView()){
				// get object clicked on, in this case the board
				e.getPoint(); // returns the X and Y with respect to the source object (board) yeah!!
				selectTile(e.getPoint()); //
			}
			//e.getClickCount(); // will be helpful for incrementing release
		}
		catch(Exception ex){
			System.err.println("EXCEPTION CAUGHT : TileSelectController");
			ex.printStackTrace();
		}
		
	}
// What's the difference between a click and a press??
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Figure out how this is different..
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// Do nothing
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// Do nothing
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// Do nothing
		
	}
}



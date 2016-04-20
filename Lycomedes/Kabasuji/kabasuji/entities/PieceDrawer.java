package kabasuji.entities;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Helper Class that knows how to translate pieces into graphics.
 * 
 * Includes some code adapted from Professor Heineman.
 * 
 * @author Derek McMaster
 */

public class PieceDrawer {
	
	/** Placeholder default color for a piece. */
	private Color defaultColor = Color.BLUE;

	/**
	 * Default constructor for a piece drawer object to get access to the draw method.
	 */
	public PieceDrawer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method that knows how to draw pieces.
	 * @param Graphics g
	 * @param Piece p
	 */
	public void drawPiece(Graphics g, Piece p, int x, int y) {
		for(PieceTile pt : p.getTileLocations()){
			g.fillRect(x+(Tile.width*pt.getColumn()), y+(Tile.height*pt.getRow()), Tile.width, Tile.height);
			g.setColor(defaultColor);
		}
	}



}
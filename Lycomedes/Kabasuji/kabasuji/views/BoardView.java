package kabasuji.views;

import javax.swing.JPanel;

import kabasuji.entities.*;
import kabasuji.supers.Level;
import kabasuji.supers.SuperModel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;

/**
 * 
 * @author Michael
 * @author Heineman
 *
 */
public class BoardView extends JPanel {


	/**
	 *  serial id for BoardView
	 */
	private static final long serialVersionUID = -8800914211105754331L;

	private Level currLevel;
	//not sure if needed since we are using a square
	int offset = 8;
	//size of puzzle if 12x12 with squares being 32 pixels long
	private final int size = 384;

	private int setActiveX,setActiveY;
	private Color setActiveColor;

	boolean showHint = false;
	PieceDrawer drawer = new PieceDrawer();
	Image offScreenImage = null;
	Graphics offScreenGraphics = null;

	//in order open in window builder apparently
	BoardView(){

	}

	public BoardView(SuperModel model) {
		super();
		this.currLevel = model.getActiveLevel();
		setLayout(null);		
		setBounds(273, 100, 384, 384);
	}


	@Override
	public Dimension getMinimumSize() {
		int width = 2*size + 2*offset;
		int height = 2*size + 2*offset;

		return new Dimension (width, height);
	}
	@Override
	public Dimension getPreferredSize() {
		int width = 2*size + 2*offset;
		int height = 2*size + 2*offset;

		return new Dimension (width, height);
	}
	/**
	 * Draws the background puzzle image and the unplayable tiles
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if (offScreenImage == null) {
			// create on demand
			Dimension s = getPreferredSize();
			offScreenImage = this.createImage(s.width, s.height);
			offScreenGraphics = offScreenImage.getGraphics();

			redraw();
		}
		// if no offscreenImage, then Swing hasn't fully initialized; leave now
		if (offScreenImage == null) {
			System.err.println("Swing not ready for drawing.");
			return;
		}

		g.drawImage(offScreenImage, 0, 0, this);
		//in case no model like for window builder
		if(currLevel == null){return;}

		//draw active piece
		if(currLevel.getActivePiece() != null){
			//finds needed adjust for piece drawer
			//probably could just make piece drawer to adjust but for time this works
			Piece active = currLevel.getActivePiece();
			int rowAdjust = active.getTileLocations()[0].getRow();
			int colAdjust = active.getTileLocations()[0].getColumn();

//			setActiveY -= (rowAdjust*32);
//			setActiveX -= (colAdjust*32);

			drawer.drawPiece(g, currLevel.getActivePiece(), setActiveX, setActiveY, setActiveColor);

		}

	}
	public void redraw(){
		//set buffer
		int x = offset;
		int y = offset;

		Dimension dim = getPreferredSize();
		offScreenGraphics.clearRect(0, 0, dim.width, dim.height);

		Board board = currLevel.getBoard();
		Tile[][] boardArray = board.getBoardArray();
		UnplayableTile tile = new UnplayableTile(0, 0);
		//Just incase separate drawing color needed for them
		PuzzleBoardTile pTile = new PuzzleBoardTile(0, 0);
		LightningBoardTile lTile = new LightningBoardTile(0, 0);
		ReleaseBoardTile rTile = new ReleaseBoardTile(0, 0);
		//draws out the board
		//MIGHT NEED TO CHANGE IF WE PLACE TRANSPARENT ON TOP?? or not
		int i,j = 0;
		for(i = 0;i<12;i++){
			for(j = 0;j<12;j++){
				if(isHintLocation(i, j) && showHint && (!boardArray[i][j].isCovered())){
					
					System.out.println("in here");
					
					offScreenGraphics.setColor(Color.orange.brighter());
					offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					offScreenGraphics.setColor(Color.BLACK);
					offScreenGraphics.drawRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					
					
				}
				else if(boardArray[i][j].isCovered()){
					//if its covered dont do anything leave it alone
					HashMap<Point,Piece> map = board.getPlacedPieces();
					Point pt = new Point(i,j);
					Piece played = map.get(pt);
					if(played != null){
//						int rowAdjust = played.getTileLocations()[0].getRow();
//						int colAdjust = played.getTileLocations()[0].getColumn();
//						
//						int rowCord = pt.y + (pt.y - rowAdjust);
//						int colCord = pt.x + (pt.x - colAdjust);
						drawer.drawPiece(offScreenGraphics, played, (pt.x*32), (pt.y*32), currLevel.getPieceColor(played.getPieceID()));
//						System.out.println(currLevel.getPieceColor(played.getPieceID()).toString());
					}
				}
				//unplayable tile then make a black tile there
				else if(boardArray[i][j].getClass() == tile.getClass()){
					offScreenGraphics.setColor(Color.WHITE);
					//the tile start is i*width and goes to that plus width
					offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
				}
				else if((boardArray[i][j].getClass() == lTile.getClass())){
					if(((LightningBoardTile) boardArray[i][j]).isMarked()){
						offScreenGraphics.setColor(Color.GREEN);
						offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					}else{
						offScreenGraphics.setColor(Color.LIGHT_GRAY); //regular board tiles are just light gray still need to figure out the release tile number stuff
						offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
						offScreenGraphics.setColor(Color.BLACK); //regular board tiles are just light gray still need to figure out the release tile number stuff
						offScreenGraphics.drawRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					}
				}
				else if((boardArray[i][j].getClass() == rTile.getClass())){
					if(((ReleaseBoardTile) boardArray[i][j]).getValue() > 0){
						offScreenGraphics.setColor(Color.LIGHT_GRAY);
						offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
						offScreenGraphics.setColor(Color.BLACK); //regular board tiles are just light gray still need to figure out the release tile number stuff
						offScreenGraphics.drawRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));

						offScreenGraphics.setFont(new Font("Kristen ITC", Font.BOLD, 16));
						if(((ReleaseBoardTile) boardArray[i][j]).getValue() > 6 && ((ReleaseBoardTile) boardArray[i][j]).getValue() <= 12){
							offScreenGraphics.setColor(Color.GREEN.darker());
							offScreenGraphics.drawString(Integer.toString(((ReleaseBoardTile) boardArray[i][j]).getValue()-6), (i*Tile.width + 13), (j*Tile.height + 20));

						}else if(((ReleaseBoardTile) boardArray[i][j]).getValue() > 12){
							offScreenGraphics.setColor(Color.RED.darker());
							offScreenGraphics.drawString(Integer.toString(((ReleaseBoardTile) boardArray[i][j]).getValue()-12), (i*Tile.width + 13), (j*Tile.height + 20));

						}else{

							offScreenGraphics.setColor(Color.YELLOW);
							offScreenGraphics.drawString(Integer.toString(((ReleaseBoardTile) boardArray[i][j]).getValue()), (i*Tile.width + 13), (j*Tile.height + 20));

						}

						
//						offScreenGraphics.drawString(Integer.toString(((ReleaseBoardTile) boardArray[i][j]).getValue()), (i*Tile.width + 13), (j*Tile.height + 20));

					}else{
						offScreenGraphics.setColor(Color.LIGHT_GRAY); //regular board tiles are just light gray still need to figure out the release tile number stuff
						offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
						offScreenGraphics.setColor(Color.BLACK); //regular board tiles are just light gray still need to figure out the release tile number stuff
						offScreenGraphics.drawRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					}
				}
				else {
					offScreenGraphics.setColor(Color.LIGHT_GRAY); //regular board tiles are just light gray still need to figure out the release tile number stuff
					offScreenGraphics.fillRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
					offScreenGraphics.setColor(Color.BLACK); //regular board tiles are just light gray still need to figure out the release tile number stuff
					offScreenGraphics.drawRect(i*Tile.width, j*Tile.height,(Tile.width), (Tile.height));
				}
				
				
				//System.out.println(board.getPlacedPieces().size());

			}
		}
	}
	public void drawActivePiece(int x, int y, Color c){
		setActiveColor = c;
		setActiveX = x;
		setActiveY = y;
	}
	public void refresh(){
		redraw();
		repaint();
	}
	

	public void showHint() {
		//drawer.drawHintPiece(offScreenGraphics, currLevel.getBoard().getHintLocations());
		
		showHint = true;
	}
	public boolean isHintLocation(int col, int row){
		for(Tile pt: currLevel.getBoard().getHintLocations()){
			if(pt.getColumn()==col && pt.getRow()==row){
				return true;
			}
		}
		return false;
	}

}
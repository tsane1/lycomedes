package tests.entities;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import kabasuji.entities.Palette;
import kabasuji.entities.Piece;
import kabasuji.entities.PieceTile;

public class TestPalette extends TestCase {

	Piece testPiece;
	PieceTile[] arr;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		/** set up a test piece */
		arr = new PieceTile[6];
		PieceTile pt1 = new PieceTile(0,0);
		arr[0] = pt1;
		PieceTile pt2 = new PieceTile(1,0);
		arr[1] = pt2;
		PieceTile pt3 = new PieceTile(2,0);
		arr[2] = pt3;
		PieceTile pt4 = new PieceTile(3,0);
		arr[3] = pt4;
		PieceTile pt5 = new PieceTile(4,0);
		arr[4] = pt5;
		PieceTile pt6 = new PieceTile(5,0);
		arr[5] = pt6;
		
		testPiece = new Piece(1, arr);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPal(){
		Palette p = new Palette();
		
		List<Piece> temp = new ArrayList<Piece>();
		temp.add(testPiece);
		
		assertTrue(p.addPieces(temp));
		
		assertFalse(p.getPieces().isEmpty());
		
		assertTrue(p.removePiece(testPiece));
	}

}

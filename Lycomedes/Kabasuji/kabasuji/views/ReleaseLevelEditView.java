package kabasuji.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import kabasuji.controllers.DeleteLevelController;
import kabasuji.controllers.RedoController;
import kabasuji.controllers.SaveLevelController;
import kabasuji.controllers.SelectPieceController;
import kabasuji.controllers.UndoController;
import kabasuji.entities.PuzzleLevel;
import kabasuji.entities.ReleaseLevel;
import kabasuji.supers.Application;
import kabasuji.supers.Level;
import kabasuji.supers.SuperModel;
import kabasuji.supers.Screen;

/**
 * 
 * @author Tanuj Sane
 * @since 4/23/16
 *
 */

public class ReleaseLevelEditView extends Screen {
	private ReleaseLevel level;
	private BoardView boardView;
	private BullpenView bullpenView;
	
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");
	private JButton btnSave = new JButton("Save");
	private JButton btnDelete = new JButton("Delete");
	
	JScrollPane pieceScroll = new JScrollPane();
	
	public ReleaseLevelEditView(String levelName, SuperModel m) {
		super(levelName, m);
		this.level = (ReleaseLevel)this.model.loadLevel(m.getUserLevelDir(), levelName+".lev");
		
		if(this.level == null) { // create new level
			this.level = new ReleaseLevel("Level " + (this.model.getTotalNumLevels()+1));
			this.setTitle("New Release Level: " + "Level " + (this.model.getTotalNumLevels() + 1));
		}
		else if(!this.level.getLevelType().equals("Release")) {
			this.level = new ReleaseLevel(levelName);
			this.setTitle("New Release Level: " + levelName);
		}
		else this.setTitle(level.getLevelName() + ": " + level.getLevelType() + " [EDIT]");
		
		model.setActiveLevel(level);
		this.boardView = new BoardView(this.model);
		this.bullpenView = new BullpenView(this.model);
	}
	
	@Override
	public void populate() {
		btnSave.setActionCommand(level.getLevelName());
		btnSave.setBackground(SystemColor.text);
		btnSave.setForeground(SystemColor.textHighlight);
		btnSave.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnSave.setBounds(726, 370, 155, 57);
		this.add(btnSave);
		
		btnDelete.setActionCommand(level.getLevelName());
		btnDelete.setBackground(SystemColor.text);
		btnDelete.setForeground(SystemColor.textHighlight);
		btnDelete.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnDelete.setBounds(726, 437, 155, 57);
		this.add(btnDelete);
		
		btnUndo.setBackground(SystemColor.text);
		btnUndo.setForeground(SystemColor.textHighlight);
		btnUndo.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnUndo.setBounds(726, 110, 155, 57);
		this.add(btnUndo);
		
		btnRedo.setBackground(SystemColor.text);
		btnRedo.setForeground(SystemColor.textHighlight);
		btnRedo.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnRedo.setBounds(726, 177, 155, 57);
		this.add(btnRedo);
		
		this.add(boardView);

		pieceScroll = new JScrollPane();
		pieceScroll.setBounds(13, 512, 904, 228);
		pieceScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pieceScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pieceScroll.setViewportView(bullpenView);
		this.add(pieceScroll);
	}
	
	@Override
	public void installControllers() {
		btnUndo.addActionListener(new UndoController(this.app, this.model));
		btnRedo.addActionListener(new RedoController(this.app, this.model));
		btnSave.addActionListener(new SaveLevelController(this.app, this.model));
		btnDelete.addActionListener(new DeleteLevelController(this.app, this.model));
		SelectPieceController psc = new SelectPieceController(level, bullpenView);
		bullpenView.addMouseListener(psc);
	}

	@Override
	public void refresh() {

	}

	@Override
	public String getName() {
		return this.level.getLevelType() + "LevelEditView";
	}

	@Override
	public BullpenView getBullpenView(){
		return this.bullpenView;
	}
	
	@Override
	public BoardView getBoardView(){
		return this.boardView;
	}
	
	@Override
	public Level getLevel() {
		return this.level;
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application(new ReleaseLevelEditView("Test Level", new SuperModel()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

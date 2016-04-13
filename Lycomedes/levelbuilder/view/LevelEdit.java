package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.BackController;

import controller.RedoController;
import controller.UndoController;
import model.Board;
import model.Bullpen;
import model.Level;
import model.LevelBuilderModel;
import supers.Application;
import supers.Model;
import supers.Screen;

public class LevelEdit extends Screen {
	/**
	 * Eclipse said so
	 */
	private static final long serialVersionUID = 1L;
	
	private Screen prevScreen;
	private Level level;
	private BoardView board;
	private BullpenView bullpen;
	
	public LevelEdit(Level l) {
		super(l.getLevelName(), l);
		this.level = l;
	}
	
	@Override
	public void populate() {
		board = new BoardView(level.getBoard());
		bullpen = new BullpenView(level.getBullpen());
		this.add(board);
		this.add(bullpen);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Kristen ITC", Font.PLAIN, 12));
		tabbedPane.setBounds(673, 38, 250, 484);
		this.add(tabbedPane);
		
		JPanel puzzle = new JPanel();
		puzzle.setBackground(Color.WHITE);
		tabbedPane.addTab("Puzzle", null, puzzle, null);
		tabbedPane.setForegroundAt(0, new Color(255, 0, 0));
		tabbedPane.setBackgroundAt(0, SystemColor.textHighlight);
		
		JLabel label = new JLabel("Puzzle Level Builder");
		label.setBounds(20, 20, 210, 33);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(31, 103, 188, 41);
		
		JLabel label_1 = new JLabel("Total Number of Moves:");
		label_1.setBounds(31, 57, 188, 28);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		puzzle.setLayout(null);
		puzzle.add(label);
		puzzle.add(formattedTextField);
		puzzle.add(label_1);
		
		JPanel lightning = new JPanel();
		lightning.setBackground(Color.WHITE);
		tabbedPane.addTab("Lightning", null, lightning, null);
		tabbedPane.setForegroundAt(1, new Color(255, 215, 0));
		
		JLabel label_2 = new JLabel("Lightning Level Builder");
		label_2.setBounds(10, 20, 230, 25);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(255, 215, 0));
		label_2.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		
		JLabel label_3 = new JLabel("Time Allowed (in seconds):");
		label_3.setBounds(8, 57, 234, 20);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		lightning.setLayout(null);
		lightning.add(label_2);
		lightning.add(label_3);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(31, 103, 188, 41);
		lightning.add(formattedTextField_1);
		
		JPanel release = new JPanel();
		release.setBackground(Color.WHITE);
		tabbedPane.addTab("Release", null, release, null);
		tabbedPane.setForegroundAt(2, new Color(50, 205, 50));
		
		JLabel label_4 = new JLabel("4");
		label_4.setBounds(130, 256, 27, 25);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_5 = new JLabel("5");
		label_5.setBounds(167, 256, 27, 25);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_6 = new JLabel("6");
		label_6.setBounds(204, 256, 27, 25);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_7 = new JLabel("4");
		label_7.setBounds(130, 288, 27, 25);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(new Color(0, 128, 0));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_8 = new JLabel("5");
		label_8.setBounds(167, 288, 27, 25);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(0, 128, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_9 = new JLabel("3");
		label_9.setBounds(93, 320, 27, 25);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(new Color(255, 215, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_10 = new JLabel("4");
		label_10.setBounds(130, 320, 27, 25);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(new Color(255, 215, 0));
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_11 = new JLabel("5");
		label_11.setBounds(167, 320, 27, 25);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(new Color(255, 215, 0));
		label_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_12 = new JLabel("6");
		label_12.setBounds(204, 320, 27, 25);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(new Color(255, 215, 0));
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_13 = new JLabel("6");
		label_13.setBounds(204, 288, 27, 25);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(new Color(0, 128, 0));
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_14 = new JLabel("1");
		label_14.setBounds(19, 256, 27, 25);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_15 = new JLabel("2");
		label_15.setBounds(56, 256, 27, 25);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setForeground(Color.RED);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_16 = new JLabel("3");
		label_16.setBounds(93, 256, 27, 25);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setForeground(Color.RED);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_17 = new JLabel("1");
		label_17.setBounds(19, 288, 27, 25);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setForeground(new Color(0, 128, 0));
		label_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_18 = new JLabel("2");
		label_18.setBounds(56, 288, 27, 25);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setForeground(new Color(0, 128, 0));
		label_18.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_19 = new JLabel("3");
		label_19.setBounds(93, 288, 27, 25);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setForeground(new Color(0, 128, 0));
		label_19.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_20 = new JLabel("1");
		label_20.setBounds(19, 320, 27, 25);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setForeground(new Color(255, 215, 0));
		label_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_21 = new JLabel("2");
		label_21.setBounds(56, 320, 27, 25);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setForeground(new Color(255, 215, 0));
		label_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_22 = new JLabel("Pieces To Place");
		label_22.setBounds(57, 223, 136, 20);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		
		JLabel label_23 = new JLabel("Release Level Builder");
		label_23.setBounds(20, 20, 210, 25);
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setForeground(new Color(50, 205, 50));
		label_23.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		
		JLabel label_24 = new JLabel("Total Allowed Moves:");
		label_24.setBounds(46, 57, 158, 20);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		release.setLayout(null);
		release.add(label_4);
		release.add(label_5);
		release.add(label_6);
		release.add(label_7);
		release.add(label_8);
		release.add(label_9);
		release.add(label_10);
		release.add(label_11);
		release.add(label_12);
		release.add(label_13);
		release.add(label_14);
		release.add(label_15);
		release.add(label_16);
		release.add(label_17);
		release.add(label_18);
		release.add(label_19);
		release.add(label_20);
		release.add(label_21);
		release.add(label_22);
		release.add(label_23);
		release.add(label_24);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(31, 103, 188, 41);
		release.add(formattedTextField_2);
		
		JButton saveBtn = new JButton("");
		saveBtn.setIcon(new ImageIcon(LevelEdit.class.getResource("/imgs/Save.png")));
		saveBtn.setBounds(102, 26, 50, 54);
		this.add(saveBtn);
		saveBtn.setFont(new Font("Segoe UI Semilight", Font.BOLD, 13));
		
		JButton backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(LevelEdit.class.getResource("/imgs/back arrow.JPG")));
		backBtn.setBounds(22, 38, 80, 37);
		this.add(backBtn);
		
		JButton undoBtn = new JButton("");
		undoBtn.setIcon(new ImageIcon(LevelEdit.class.getResource("/imgs/Undo.png")));
		undoBtn.setBounds(102, 92, 53, 37);
		this.add(undoBtn);
		
		JButton redoBtn = new JButton("");
		redoBtn.setIcon(new ImageIcon(LevelEdit.class.getResource("/imgs/Redo.png")));
		redoBtn.setBounds(32, 87, 53, 37);
		this.add(redoBtn);
	}
	
	
	@Override
	public void installControllers() {
		super.installControllers();
	}
	

	@Override
	public void initModel() {
		// Method intentionally unimplemented, must be overridden to use
	}

	@Override
	public void refresh() {
		// Method intentionally unimplemented, must be overridden to use
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public void setPreviousFrame(Screen screen) {
		prevScreen = screen;
	}

	public LevelEditSelectView getPreviousFrame() {
		return (LevelEditSelectView)prevScreen;
	}
	
	@Override
	public String getName() {
		return "LevelEdit";
	}
}

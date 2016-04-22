package kabasuji.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import kabasuji.controllers.DeleteLevelController;
import kabasuji.controllers.LevelEditController;
import kabasuji.controllers.LevelPlayController;
import kabasuji.controllers.NavigateLevelSelectController;
import kabasuji.controllers.NewLevelController;
import kabasuji.supers.SuperModel;
import kabasuji.supers.Application;
import kabasuji.supers.Screen;

public class LevelPlaySelectView extends Screen {
	private ArrayList<JButton> levelButtons = new ArrayList<JButton>();
	private ArrayList<JLabel> levelNames = new ArrayList<JLabel>();
	private JButton btnNext = new JButton("Next");
	private JButton btnPrev = new JButton("Previous");
	
	public LevelPlaySelectView(SuperModel m) {
		super("Play a Level", m);
	}
	
	@Override
	public void populate() {
		btnNext.setActionCommand("Next");
		btnNext.setBackground(SystemColor.text);
		btnNext.setForeground(SystemColor.textHighlight);
		btnNext.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnNext.setBounds(765, 683, 155, 57);
		this.add(btnNext);
		
		btnPrev.setActionCommand("Previous");
		btnPrev.setBackground(SystemColor.text);
		btnPrev.setForeground(SystemColor.textHighlight);
		btnPrev.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnPrev.setBounds(13, 683, 155, 57);
		this.add(btnPrev);
		
		for(int idx = 0; idx < 15; idx++) {
			levelNames.add(new JLabel());
			levelNames.get(idx).setText(model.getDefaultLevelByIndex(idx).getLevelName());
			levelNames.get(idx).setHorizontalAlignment(SwingConstants.CENTER);
			levelNames.get(idx).setForeground(SystemColor.textHighlight);
			levelNames.get(idx).setFont(new Font("Kristen ITC", Font.BOLD, 18));
			levelNames.get(idx).setSize(128,50);
			
			levelButtons.add(new JButton());
			levelButtons.get(idx).setActionCommand(model.getDefaultLevelByIndex(idx).getLevelName());
			switch(model.getDefaultLevelByIndex(idx).getLevelType()) {
			case "Puzzle":
				levelButtons.get(idx).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/puzzle_icon_smol.png")));
				break;
			case "Lightning":
				levelButtons.get(idx).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/lightning_icon_smol.png")));
				break;
			case "Release":
				levelButtons.get(idx).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/release_icon_smol.png")));
				break;
			}
			levelButtons.get(idx).setBackground(SystemColor.text);
			levelButtons.get(idx).setSize(128, 128);
		}	
		
		for(int idx = 0; idx < this.model.numUserLevels(); idx++) {
			levelNames.add(new JLabel());
			levelNames.get(idx + 15).setText(model.getUserLevelByIndex(idx).getLevelName());
			levelNames.get(idx + 15).setHorizontalAlignment(SwingConstants.CENTER);
			levelNames.get(idx + 15).setForeground(SystemColor.textHighlight);
			levelNames.get(idx + 15).setFont(new Font("Kristen ITC", Font.BOLD, 18));
			levelNames.get(idx + 15).setSize(128,50);
			
			levelButtons.add(new JButton());
			levelButtons.get(idx + 15).setActionCommand(model.getUserLevelByIndex(idx).getLevelName());
			switch(model.getUserLevelByIndex(idx).getLevelType()) {
			case "Puzzle":
				levelButtons.get(idx + 15).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/puzzle_icon_smol.png")));
				break;
			case "Lightning":
				levelButtons.get(idx + 15).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/lightning_icon_smol.png")));
				break;
			case "Release":
				levelButtons.get(idx + 15).setIcon(new ImageIcon(PuzzleLevelEditView.class.getResource("/imgs/release_icon_smol.png")));
				break;
			}
			levelButtons.get(idx + 15).setBackground(SystemColor.text);
			levelButtons.get(idx + 15).setSize(128, 128);
		}		
			
		refresh();
	}

	@Override
	public void installControllers() {
		for(int idx = 0; idx < 15 + this.model.numUserLevels(); idx++) {
			levelButtons.get(idx).addActionListener(new LevelPlayController(this.app, this.model));
		}
		btnNext.addActionListener(new NavigateLevelSelectController(this.app, this.model));
		btnPrev.addActionListener(new NavigateLevelSelectController(this.app, this.model));
	}
	
	@Override
	public String getName() {
		return "LevelEditSelect";
	}

	@Override
	public void refresh() {
		for(int idx = 0; idx < 15 + this.model.numUserLevels(); idx++) {
			this.remove(levelButtons.get(idx));
			this.remove(levelNames.get(idx));
		}		
		this.validate();
		this.repaint();
		
		btnNext.setEnabled(model.getPage() < (model.numUserLevels() + 14) / 10);
		btnPrev.setEnabled(model.getPage() > 0);
		btnNext.validate();
		btnNext.repaint();
		btnPrev.validate();
		btnPrev.repaint();		
		
		for(int idx = 0; idx < 10; idx++) {
			int btnIndex = (10*model.getPage()) + idx;
			if(btnIndex > 15 + model.numUserLevels());
			else {
				System.out.println(btnIndex);
				levelNames.get(btnIndex).setLocation(125+(138*(idx%5)), 150+(188*(idx/5)));
				this.add(levelNames.get(btnIndex));
				levelNames.get(btnIndex).validate();
				levelNames.get(btnIndex).repaint();
				
				levelButtons.get(btnIndex).setLocation(125+(138*(idx%5)), 200+(188*(idx/5)));
				this.add(levelButtons.get(btnIndex));
				levelButtons.get(btnIndex).validate();
				levelButtons.get(btnIndex).repaint();
			}
		}
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application(new LevelPlaySelectView(new SuperModel()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

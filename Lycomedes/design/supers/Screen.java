package supers;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import controller.BackController;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;

public class Screen extends JPanel implements IScreen {
	private String title;
	private JButton btnBack;
	private Model model;
	private Application app;
	
	public Screen(String title, Model m) {
		this.title = title;
		this.model = m;
		initScreen(this.title);
	}
	
	private void initScreen(String title) {
		this.setBackground(Color.WHITE);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.setLayout(null);
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Kristen ITC", Font.BOLD, 32));
		lblTitle.setBounds(0,13,930,75);
		this.add(lblTitle);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.text);
		btnBack.setFont(new Font("Kristen ITC", Font.BOLD, 12));
		btnBack.setForeground(SystemColor.textHighlight);
		btnBack.setBounds(12, 13, 97, 25);
		this.add(btnBack);
	}
	
	public void finish(Application a) {
		this.app = a;
		btnBack.addActionListener(new BackController(this.app, this, model));
	}
	
	@Override
	public void populate() {
		// Method intentionally unimplemented, must be overridden to use
	}

	@Override
	public void installControllers() {
	// Method intentionally unimplemented, must be overridden to use
	}

	@Override
	public void initModel() {
		// Method intentionally unimplemented, must be overridden to use
	}

	@Override
	public void refresh() {
		// Method intentionally unimplemented, must be overridden to use
	}
	
	@Override
	public String getName() {
		return this.title;
	}
}

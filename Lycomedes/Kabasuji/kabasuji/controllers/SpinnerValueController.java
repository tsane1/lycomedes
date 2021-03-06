package kabasuji.controllers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

import kabasuji.supers.Application;
import kabasuji.supers.SuperModel;
import kabasuji.entities.LightningLevel;
import kabasuji.entities.PuzzleLevel;

/**
 * Controller for the spinner values in the builder.
 */
public class SpinnerValueController implements FocusListener {
	/** Model instance being updated. */
	private SuperModel model;
	/** Application being run */
	private Application app;

	/**
	 * Constructor for the spinner controller.
	 * @param app
	 * @param model
	 */
	public SpinnerValueController(Application app, SuperModel model) {
		this.app = app;
		this.model = model;
	}

	/**
	 * Method for interpreting from the focus listener.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		System.out.println("focus on " + e.getComponent().toString());		
	}

	/**
	 * Method for updating the focus listener.
	 */
	@Override
	public void focusLost(FocusEvent e) {
		switch(app.getCurrScreen().getName()) {
		case "LightningLevelEditView":
			if(((JFormattedTextField)e.getSource()).getName().equals("mins")) ((LightningLevel)model.getActiveLevel()).setMinsLeft((Integer)((JFormattedTextField)e.getComponent()).getValue());
			else if(((JFormattedTextField)e.getSource()).getName().equals("secs")) ((LightningLevel)model.getActiveLevel()).setSecsLeft((Integer)((JFormattedTextField)e.getComponent()).getValue());
			else System.err.println("Something went wrong yo.");
			break;
		case "PuzzleLevelEditView":
			((PuzzleLevel)model.getActiveLevel()).setMovesLeft((Integer)((JFormattedTextField)e.getComponent()).getValue());
		}
	}
}

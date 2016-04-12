package levelbuilder;

import java.awt.EventQueue;

import model.LevelBuilderModel;
import view.BuilderSplashScreen;
import view.MainBuilderScreen;

public class LevelBuilder {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		LevelBuilderModel m = new LevelBuilderModel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Load the Splash Screen
					BuilderSplashScreen sc = new BuilderSplashScreen();
					//Sleep
					Thread.sleep(sc.splashScreenDelay());
					//Launch into the main builder
					MainBuilderScreen frame = new MainBuilderScreen(m);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
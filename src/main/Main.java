package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminates on close marker
		w.setResizable(false);
		w.setTitle("Game");
		
		GamePanel gp = new GamePanel();
		w.add(gp);
		
		w.pack(); // causes this window to be sized to fit the preferred size and layouts of its subcomponents
		
		w.setLocationRelativeTo(null); // set location to center
		w.setVisible(true); // make window visible
		
	}

}

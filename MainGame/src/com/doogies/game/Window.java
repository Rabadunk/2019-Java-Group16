package com.doogies.game;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window() {
		setTitle("Save Puppies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(1280, 720));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
}

package com.jon.GUI;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class OptionsPanel extends JPanel{
	
	/**
	 OptionsPanel to display the options 
	 */
	private static final long serialVersionUID = 1L;

	public OptionsPanel(){
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("I am in options menu");
	}
}

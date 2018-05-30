package com.jon.GUI;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{

	/**
	 MainMenuPanel for displaying our Main Menu 
	 */
	private static final long serialVersionUID = 1L;
	GUIManager guiManager;
	
	public MainMenuPanel(GUIManager guiManager){
		
		initComponents();
		this.guiManager = guiManager;
	}
	
	public void initComponents(){
		
		setBackground(Color.CYAN);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Button buttonNewGame = new Button("NEW GAME");
		buttonNewGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonNewGamePressed();
				
			}
		});
		
		Button buttonOptions = new Button("OPTIONS");
		buttonOptions.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonOptionsPressed();
				
			}
		});
		
		Button buttonHelp = new Button("HELP");
		buttonHelp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonHelpPressed();
				
			}
		});
		
		Button buttonCredits = new Button("CREDITS");
		buttonCredits.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonCreditsPressed();
				
			}
		});
		
		Button buttonExit = new Button("EXIT");
		buttonExit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		
		add(buttonNewGame);
		add(buttonOptions);
		add(buttonHelp);
		add(buttonCredits);
		add(buttonExit);	
	}
	
	public void buttonNewGamePressed(){
		guiManager.startGame();
	}

	public void buttonOptionsPressed(){
		guiManager.showOptions();
	}
	
	public void buttonHelpPressed(){
		guiManager.showHelp();
	}
	
	public void buttonCreditsPressed(){
		guiManager.showCredits();
	}
}

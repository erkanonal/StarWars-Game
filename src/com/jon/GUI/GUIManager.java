package com.jon.GUI;
import javax.swing.JFrame;
import GameLogic.GameEngine;

public class GUIManager extends JFrame {

	/**
	 GUIManager Class to handle all Gui operations. It is set as JFrame a it is also contains the panels.
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 1000;
	private int HEIGHT = 700;
	
	private MainMenuPanel mainMenuPanel;
	private GamePanel gamePanel;
	private OptionsPanel optionsPanel;
	private HelpPanel helpPanel;
	private CreditsPanel creditsPanel;
	
	
	public GUIManager() {

		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GameEngine.getInstance().setFrame(this);
		
		gamePanel = null;	
		
		//gamePanel = new GamePanel();
		//getContentPane().add(gamePanel);
		
		//gamePanel.setVisible(false);
				
		optionsPanel = new OptionsPanel();
		//add(optionsPanel);
		optionsPanel.setVisible(false);
		
		helpPanel = new HelpPanel();
		//add(helpPanel);
		helpPanel.setVisible(false);
		
		creditsPanel = new CreditsPanel();
		//add(creditsPanel);
		creditsPanel.setVisible(false);
		
		mainMenuPanel = new MainMenuPanel(this);
		//getContentPane().add(mainMenuPanel);	
		//setContentPane(mainMenuPanel);
		mainMenuPanel.setVisible(true);
		
		
		addKeyListener(GameEngine.getInstance());
		GameEngine.getInstance().start();
	
	}
	
	public void startGame(){
		
		//setContentPane(gamePanel);
		//gamePanel.setVisible(true);

		mainMenuPanel.setVisible(false);
		
		GameEngine.getInstance().start();
	}
	
	public void showOptions(){
		
		//setContentPane(optionsPanel);
		//optionsPanel.setVisible(true);

		GameEngine.getInstance().start();
	}
	
	public void showHelp(){
		
		setContentPane(helpPanel);
		helpPanel.setVisible(true);
		
	}
	
	public void showCredits(){
		
		setContentPane(creditsPanel);
		creditsPanel.setVisible(true);
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
	
	public void setGamePanel(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}
}

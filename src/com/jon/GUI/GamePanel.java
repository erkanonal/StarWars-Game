package com.jon.GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


import GameLogic.GameEngine;


public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	GameEngine gameEngine = GameEngine.getInstance();
	
	public Rectangle playButton = new Rectangle(gameEngine.getFrame().getWidth()/2 + 100, 200, 150, 120);
	public Rectangle optionsButton = new Rectangle(gameEngine.getFrame().getWidth()/2 + 100, 350, 150, 120);
	public Rectangle quitButton = new Rectangle(gameEngine.getFrame().getWidth()/2 + 100, 500, 150, 120);
			
	public  GamePanel() {
		
		setFocusable(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(x >= (gameEngine.getFrame().getWidth()/2 + 100) && x <= (gameEngine.getFrame().getWidth()/2 + 250)){
					if(y >= 200 && y <= 320){
						gameEngine.setState(1);
					}
				}	
				
				if(x >= (gameEngine.getFrame().getWidth()/2 + 100) && x <= (gameEngine.getFrame().getWidth()/2 + 250)){
					if(y >= 500 && y <= 620){
						System.exit(0);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//SettingBackground  
		if(gameEngine.getState() == 1 ){
			ImageIcon imageIcon1 = new ImageIcon("res\\images\\space.png");
			Image i1 = imageIcon1.getImage();
			g.drawImage(i1, 0, 0, this.getSize().width, this.getSize().height, this);
		}
		
		if(gameEngine.getState() == 2 ){
			ImageIcon imageIcon2 = new ImageIcon("res\\images\\menu_background.jpg");
			Image i2 = imageIcon2.getImage();
			g.drawImage(i2, 0, 0, this.getSize().width, this.getSize().height, this);
		}
		

		
		if(gameEngine.getState() == 2){
			Font fnt0 = new Font("arial", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("Star Wars", 550, 100);
			
			
			Font fnt1 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt1);
			
			g.drawString("Play", playButton.x + 30, playButton.y + 70);
			g2d.draw(playButton);
			
			g.drawString("Options", optionsButton.x + 30, optionsButton.y + 70);
			g2d.draw(optionsButton);
			
			g.drawString("Quit", quitButton.x + 30, quitButton.y + 70);
			g2d.draw(quitButton);
		}
				
		if(gameEngine.getState() == 1){
			
			if(gameEngine.getPlayer() != null ){
				gameEngine.getPlayer().render(g);
			}
			if(gameEngine.getLevelManager() != null){
				gameEngine.getLevelManager().getCurrentLevel().render(g);
				
				
			}
		}
	}

}

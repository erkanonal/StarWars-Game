package GameLogic;

import java.awt.Graphics;

import java.io.*;
import javazoom.*;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SortingFocusTraversalPolicy;

import com.jon.Entities.EntityA;
import com.jon.Entities.EntityB;
import com.jon.Entities.FireOne;
import com.jon.Entities.LevelManager;
import com.jon.Entities.LevelOne;
import com.jon.Entities.Player;
import com.jon.Entities.Sword;
import com.jon.GUI.GUIManager;
import com.jon.GUI.GamePanel;
import com.jon.GUI.Main;
import com.sun.javafx.geom.AreaOp.AddOp;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import Audio.Sound;


public class GameEngine implements Runnable, KeyListener{

	private  static GameEngine gameEngine;
	private LevelManager levelManager;
	private Player player;

	private GUIManager guiManager;
	private GamePanel gamePanel;

	public LinkedList<EntityA> swords;
	public LinkedList<EntityB> enemies;

	private Thread thread;
	private MyTimer myTimer;
	private boolean isRunning;

	private boolean left;
	private boolean right; 

	private boolean flag1= true;
	private boolean flag2= true;
	private boolean flag3= true;

	public static enum STATE{
		MENU,
		GAME
	};


	public static STATE State = STATE.MENU;
	private Sound testMusic;
	private Sound backgroundSound;
	private Sound vaderSound;
	private Sound palpatineSound;

	public GameEngine(){

		gameEngine = null;
		levelManager = null;
		player =  null;

		guiManager = null;
		gamePanel = null;

		thread = null;
		isRunning = false;

		swords = null;
		enemies = null;

		right = false;
		left = false;
		
		myTimer = new MyTimer();
	}

	public void init() {

		gamePanel = new GamePanel();
		gamePanel.setVisible(true);

		gameEngine.getFrame().getContentPane().removeAll(); 

		gameEngine.getFrame().getContentPane().add(gamePanel);
		gameEngine.getFrame().setGamePanel(gamePanel);
		gameEngine.getFrame().setContentPane(gamePanel);

		//gamePanel.requestFocus();	
		//gamePanel = gameEngine.getFrame().getGamePanel();
		//gamePanel.setVisible(true);
		//gameEngine.getFrame().getContentPane().add(gamePanel );	
		//gameEngine.getFrame().setContentPane(gamePanel);		
		//gamePanel.requestFocus();	

		player = new Player(420,610);
		player.setGameOver(false);

		levelManager = new LevelManager();	

		testMusic = new Sound("/music/laser_blast.wav");
		backgroundSound = new Sound("/music/imperial_march.wav");
		vaderSound = new Sound("/music/vader_sound.wav");
		//palpatineSound = new Sound("/music/palpatine_sound.wav");
		
		backgroundSound.loop();	
			

		/*try{
			File file = new File("Laser.wav");
			FileInputStream fis = new FileInputStream("C:\\Users\\erkan\\Desktop\\Original Project\\song.wav");
			FileInputStream fis = new FileInputStream("C:\\Users\\erkan\\Desktop\\Original Project\\res\\music\\Laser.wav");
			
			BufferedInputStream bis = new BufferedInputStream(fis);

			try{
				javazoom.jl.player.Player player = new javazoom.jl.player.Player(bis);
				player.play();
			}catch (JavaLayerException e) {}
		}catch (IOException e) {}
		 */
		
		/*File Clap = new File("laser_blast.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Clap));
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/


	}


	public synchronized void start(){

		if(isRunning){
			return;
		}
		else{
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop(){
		if(!isRunning){
			return;
		}
		else{
			isRunning = false;
			try {
				thread.join();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.exit(1);
		}
	}



	public void run(){

		//FPS variables
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		//FPS variables

		init();

		while(isRunning){
			//Game Loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;

				gameEngine.update();
			}
			gameEngine.render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);

				updates = 0;
				frames = 0;
			}
		}

		cleanUp();
		stop();
	}

	public void cleanUp(){

	}

	public void tick(){

	}

	public void restart(){


		gameEngine.getFrame().dispose();


		State = STATE.GAME.MENU;
		GameEngine.getInstance().setNull();
		BonusHandler.getInstance().setNull();
		BossBulletHandler.getInstance().setNull();

		GUIManager gui = new GUIManager();
		gui.requestFocus();

		gui.setVisible(true);
		stop();
	}

	public void update(){
		if(State == STATE.GAME){

			if(levelManager.getLevel(1).isBossDead() && flag1){
				JOptionPane.showMessageDialog(null, "Next Level", "Level 1 Completed!", JOptionPane.INFORMATION_MESSAGE);
				flag1 = false;
			}
			if(levelManager.getLevel(2).isBossDead() && flag2){
				JOptionPane.showMessageDialog(null, "Next Level", "Level 2 Completed!", JOptionPane.INFORMATION_MESSAGE);
				flag2 = false;
			}
			if(levelManager.getLevel(3).isBossDead() && flag3){
				JOptionPane.showMessageDialog(null, "Next Level", "Level 3 Completed!", JOptionPane.INFORMATION_MESSAGE);
				flag3 = false;
				restart();
			}
			levelManager.getCurrentLevel().move();
			if(player.getNumOfLive() <= 0){
				JOptionPane.showMessageDialog(null, "Game Over", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Before Restart");
				restart();
				System.out.println("After Restart");
			}

			if(right){
				player.move();
				player.setDirection(true);
			}
			if(left){
				player.move();
				player.setDirection(false);
			}
		}
	}

	public void render(){
		gamePanel.repaint();
	}

	public static GameEngine getInstance(){
		if(gameEngine == null){
			gameEngine = new GameEngine();
			return gameEngine;
		}
		else{
			return gameEngine;
		}	
	}

	public void setFrame(GUIManager guiManager){
		this.guiManager = guiManager;
	}

	public GUIManager getFrame(){
		return guiManager;
	}

	public boolean getRight(){
		return right;
	}

	public boolean getLeft(){
		return left;
	}

	public Player getPlayer(){
		return player;
	}

	public LevelManager getLevelManager(){
		return levelManager;
	}

	//1 for game, 2 for menu
	public int getState() {
		if(State == STATE.GAME){
			return 1;
		}
		if(State == STATE.MENU){
			return 2;
		}
		return 0;
	}

	public void setState(int x) {
		if(x == 1){
			State = STATE.GAME;
		}
		if(x == 2){
			State = STATE.MENU;
		}
	}

	public void setNull() {
		gameEngine = null;
	}
	
	public  Sound getVaderSound() {
		return vaderSound;
	}
	
	public  Sound getPalpatineSound() {
		return palpatineSound;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		if(State == STATE.GAME){
			if(id == KeyEvent.VK_RIGHT)
			{
				right = true;
			}
			else if(id == KeyEvent.VK_LEFT)
			{
				left = true;
			}
			else if(id == KeyEvent.VK_SPACE)
			{
				if(myTimer.timerEvent(500)){
					testMusic.play();
					
					if(player.getWeaponNum() == 1){
						Sword sword2 = new Sword(player.getX() + player.getWidth()/2, player.getY());
						sword2.setSwordNum(2);
						levelManager.getCurrentLevel().addSwordEntity(sword2);
					}
					if(player.getWeaponNum() == 2){
						Sword sword1 = new Sword(player.getX() - 5 + player.getWidth()/2, player.getY());
						sword1.setSwordNum(1);
						Sword sword3 = new Sword(player.getX() + 5, player.getY());
						sword3.setSwordNum(3);

						levelManager.getCurrentLevel().addSwordEntity(sword1);
						levelManager.getCurrentLevel().addSwordEntity(sword3);
					}
					if(player.getWeaponNum() == 3){
						Sword sword1 = new Sword(player.getX() - 5 + player.getWidth()/2, player.getY());
						sword1.setSwordNum(1);
						Sword sword2 = new Sword(player.getX() + player.getWidth()/2, player.getY());
						sword2.setSwordNum(2);
						Sword sword3 = new Sword(player.getX() + 5 + player.getWidth()/2, player.getY());
						sword3.setSwordNum(3);

						levelManager.getCurrentLevel().addSwordEntity(sword1);
						levelManager.getCurrentLevel().addSwordEntity(sword2);
						levelManager.getCurrentLevel().addSwordEntity(sword3);
					}
				}
				
			}
			else if(id == KeyEvent.VK_ESCAPE)
			{

			}
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();

		if(State == STATE.GAME){
			if(id == KeyEvent.VK_RIGHT)
			{
				right = false;
			}
			else if(id == KeyEvent.VK_LEFT)
			{
				left = false;
			}
			else if(id == KeyEvent.VK_SPACE)
			{
				
			}
		}

	}
	public void keyTyped(KeyEvent e) {

	}
}

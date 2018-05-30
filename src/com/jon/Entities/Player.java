package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import GameLogic.GameEngine;


public class Player extends GameObject implements EntityA{

	GameEngine gameEngine;
	
	private BufferedImage imageFront;
	
	private BufferedImage imageLeft1, imageLeft2, imageLeft3;	
	private BufferedImage imageRight1, imageRight2, imageRight3;
	
	private BufferedImage imageLife;
	
	private int width, height;
	private int numOfLives;
	
	private boolean direction;
	private boolean isGameOver;
	
	private int weaponNum;
	
	public Player(int x, int y) 
	{	
		super(x, y);
			
		gameEngine = GameEngine.getInstance();
		numOfLives = 3;
		weaponNum = 1;
		direction = false;
		isGameOver = false;
				
		try 
		{
			imageFront = ImageIO.read(new File("res\\images\\JediFront.png"));
			imageLeft1 = ImageIO.read(new File("res\\images\\JediLeft.png"));
			imageRight1 = ImageIO.read(new File("res\\images\\JediRight.png"));
			
			imageLife = ImageIO.read(new File("res\\\\images\\heart.png"));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		width = imageFront.getWidth();
		height = imageFront.getHeight();
		
	}

	public void move()
	{
		int speed = 3;
		
		// direction == true for right, false for left
		if(direction)
		{
			if(x + width + speed < GameEngine.getInstance().getFrame().getWidth())
			{
				x = x + speed;
			}
			else 
			{
				x = gameEngine.getFrame().getWidth() - width;
				
			}
		}
		else
		{
			if(x - speed > 0)
			{
				x = x - speed;
			}
			else
			{
				x = 0;
				
			}
		}
	}
		
	public void render(Graphics g)
	{
		if(gameEngine.getRight() == true && gameEngine.getLeft() == false )
		{
			g.drawImage(imageRight1, x, y, imageRight1.getWidth(null), imageRight1.getHeight(null), null);		
		}
		else if(gameEngine.getRight() == false && gameEngine.getLeft() == true )
		{
			g.drawImage(imageLeft1, x, y, imageLeft1.getWidth(null), imageLeft1.getHeight(null), null);				
		}
		else if(gameEngine.getRight() == false && gameEngine.getLeft() == false)
		{
			g.drawImage(imageFront, x, y, imageFront.getWidth(null), imageFront.getHeight(null), null);			
		}
		
		int temp1 = 0;
		int temp2 = 0;
		for(int i = 0; i < numOfLives; i++){
			g.drawImage(imageLife, temp1, temp2, imageLife.getWidth(null), imageLife.getHeight(null), null);	
			temp1 = temp1 + imageLife.getWidth();
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public void incrementLife(){
		if(this.numOfLives < 5){
			this.numOfLives++;
		}
	}
	
	public void decrementLife(int hit){
		this.numOfLives--;
		if(this.numOfLives == 0){
			isGameOver = true;
			//JOptionPane.showMessageDialog(null, null, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public int getNumOfLive(){
		return numOfLives;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public boolean getDirection(){
		return direction;
	}
	
	public boolean isGameOver(){
		return isGameOver;
	}
	public void setGameOver(boolean g){
		isGameOver = g;
	}
	
	//true for right, false for left
	public void setDirection(boolean direction){
		this.direction = direction;
	}

	public int getWeaponNum() {
		return weaponNum;
	}

	public void setWeaponNum(int weaponNum) {
		this.weaponNum = weaponNum;
	}

}

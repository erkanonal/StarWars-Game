package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameLogic.GameEngine;
import GameLogic.Physics;

public class Sword extends GameObject implements EntityA {

	BufferedImage imageSword;
	GameEngine gameEngine = GameEngine.getInstance();
	
	private int width, height;
	private int swordNum;
	
	public Sword(int x, int y)
	{
		super(x, y);
		
		try 
		{		
			imageSword = ImageIO.read(new File("res\\images\\lightsaber.png"));
		} 
		catch (IOException e) 
		{		
			e.printStackTrace();
		}
		
		width = imageSword.getWidth();
		height = imageSword.getHeight();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(imageSword, x, y, imageSword.getWidth(null), imageSword.getHeight(null), null);	
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public void move()
	{
		if (swordNum == 1) {
			y = y - 5;
			x = x - 5;
		}
		if (swordNum == 2) {
			y = y - 5;	
		}
		if (swordNum == 3) {
			y = y - 5;	
			x = x + 5;
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSwordNum() {
		return swordNum;
	}

	public void setSwordNum(int swordNum) {
		this.swordNum = swordNum;
	}
}

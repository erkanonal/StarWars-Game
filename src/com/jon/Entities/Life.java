package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameLogic.GameEngine;

public class Life extends GameObject implements EntityB, Bonus{

GameEngine gameEngine;
	
	BufferedImage heart;
	
	private int speed;
	
	private int width, height;
	
	public Life(int x, int y, int speed) {
		super(x, y);
		gameEngine = GameEngine.getInstance();
		this.speed = speed;
		
		try {
			heart = ImageIO.read(new File("res\\images\\heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = heart.getWidth();
		height = heart.getHeight();

	}

	public void move() {
		y = y + speed;	
	}

	public void render(Graphics g) {
		g.drawImage(heart, x, y, heart.getWidth(null), heart.getHeight(null), null);		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

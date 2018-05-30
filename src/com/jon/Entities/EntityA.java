package com.jon.Entities;

import java.awt.Rectangle;

public interface EntityA {

	public void move();
	public void render(java.awt.Graphics g);
	public Rectangle getBounds();
	
	public int getX();
	public int getY();
}

package com.jon.Entities;

import java.awt.Rectangle;

public interface Bonus {

	public void move();
	public void render(java.awt.Graphics g);
	public Rectangle getBounds();
	
	public int getX();
	public int getY();
}

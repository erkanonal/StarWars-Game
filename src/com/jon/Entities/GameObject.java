package com.jon.Entities;

import java.awt.Rectangle;

public class GameObject {

	protected int x;
	protected int y;
	
	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle(x, y, width, height);
	}
}

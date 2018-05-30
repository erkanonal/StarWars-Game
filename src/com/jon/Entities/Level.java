package com.jon.Entities;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sun.xml.internal.stream.Entity;

public interface Level {

	public void move();
	public void render(Graphics g);
	public void releaseTheBoss();
	public boolean isBossDead();
	
	public void addSwordEntity(Sword sword);
	public void removeSwordEntity(Sword sword);
	
	public int getNumEnemyKilled();
	public void setNumEnemyKilled(int killed);

}

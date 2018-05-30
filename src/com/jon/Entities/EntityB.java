package com.jon.Entities;

import java.awt.Rectangle;

import javax.sound.midi.VoiceStatus;

public interface EntityB {

	public void move();
	public void render(java.awt.Graphics g);
	public Rectangle getBounds();
	
	public int getX();
	public int getY();
}

package com.jon.Entities;

import javax.swing.JOptionPane;

import Audio.Sound;
import GameLogic.GameEngine;
import sun.net.www.content.audio.x_aiff;

public class LevelManager {

	LevelOne levelOne;
	LevelTwo levelTwo;
	LevelThree levelThree;
	
	boolean flag1;
	boolean flag2;
	boolean flag3;
	
	boolean flag4;
	boolean flag5;
	boolean flag6;
	
	public LevelManager(){
		levelOne = null;
		levelTwo = null;
		levelThree = null;
		
		flag1 = true;
		flag2 = true;
		flag3 = true;
		
		flag4 = true;
		flag5 = true;
		flag6 = true;
		
	}
	
	public Level getLevel(int levelNumber){
		if(levelNumber == 1){
			if(levelOne == null){
				levelOne = new LevelOne();
				return levelOne;
			}
			else{
				return levelOne;
			}
		}
		if(levelNumber == 2){
			if(levelTwo == null){
				levelTwo = new LevelTwo();
				return levelTwo;
			}
			else{
				return levelTwo;
			}
		}
		if(levelNumber == 3){
			if(levelThree == null){
				levelThree = new LevelThree();
				return levelThree;
			}
			else{
				return levelThree;
			}
		}
		
		return null;
	}
	
	public boolean isLevelCompleted(int levelNumber){
		if(levelNumber == 1){
			if(levelOne.isBossDead())
				return true;
		}
		if(levelNumber == 2){
			if(levelTwo.isBossDead())
				return true;
		}
		if(levelNumber == 3){
			if(levelThree.isBossDead())
				return true;
		}
		return false;
	}
	
	public Level getCurrentLevel(){

		if(getLevel(1).getNumEnemyKilled() != 30 || !(getLevel(1).isBossDead()) || flag4){
			if((getLevel(1).getNumEnemyKilled() == 30) && flag1){
				 getLevel(1).releaseTheBoss();

				 flag1 = false;
			}
			if(getLevel(1).isBossDead()){
				 //JOptionPane.showMessageDialog(null, "Next Level", "Level 1 Completed!", JOptionPane.INFORMATION_MESSAGE);
				 flag4 = false;
				 return getLevel(2);
			}
			return getLevel(1);		
		}

		if(getLevel(2).getNumEnemyKilled() != 40 || !(getLevel(2).isBossDead()) || flag5){
		
				if(getLevel(2).getNumEnemyKilled() == 40 && flag2){
					 getLevel(2).releaseTheBoss();
					 //GameEngine.getInstance().getPalpatineSound().play();
					 flag2 = false;
				}
				if(getLevel(2).isBossDead()){
					 //JOptionPane.showMessageDialog(null, "Next Level", "Level 2 Completed!", JOptionPane.INFORMATION_MESSAGE);
					 flag5 = false;
					 return getLevel(3);
				}
				return getLevel(2);
			}
		if(getLevel(3).getNumEnemyKilled() != 50 || !(getLevel(3).isBossDead()) || flag6){
			
					if(getLevel(3).getNumEnemyKilled() == 50 && flag3){
						 getLevel(3).releaseTheBoss();
						 GameEngine.getInstance().getVaderSound().play();
						 flag3 = false;
					}
					if(getLevel(3).isBossDead()){
						// JOptionPane.showMessageDialog(null, "Next Level", "Level 3 Completed!", JOptionPane.INFORMATION_MESSAGE);
						 flag6 = false;
					}
					return getLevel(3);
				}
		return null;

		}

}

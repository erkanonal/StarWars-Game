package com.jon.Entities;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import GameLogic.BonusHandler;
import GameLogic.BossBulletHandler;
import GameLogic.GameEngine;
import GameLogic.Physics;

public class LevelTwo implements Level {

	private GameEngine gameEngine;
	private BossBulletHandler bossBulletHandler;
	private BonusHandler bonusHandler;
	private Player player;
	
	private LinkedList<Sword> swords;
	private LinkedList<Soldier> enemies;
	
	private LinkedList<X2UP> x2up;
	private LinkedList<X3UP> x3up;
	
	private BossPalpatine cercei;
	private boolean bossFireStarted;
	
	private Sword tempSword;
	private Soldier tempEnemy;
	private FireOne tempBullet;
	
	private boolean isBossReleased = false;
	private boolean isBossDead = false;
	private boolean oneIsDead = true;
	private boolean justOneBonus = true;
	
	private Random random;
	
	private int numEnemyKilled;
	
	int temp1;
	
	public LevelTwo()
	{
		gameEngine = GameEngine.getInstance();
		bossBulletHandler = BossBulletHandler.getInstance();
		bonusHandler = BonusHandler.getInstance();
		
		player = gameEngine.getPlayer();
		enemies = new LinkedList<Soldier>();
		cercei = new BossPalpatine(420,50);
		swords = new LinkedList<Sword>();
		
		x2up = new LinkedList<X2UP>();
		x3up = new LinkedList<X3UP>();
		
		random= new Random();
		bossFireStarted = true;
		
		numEnemyKilled = 0;
		temp1 = 240;
		
			for(int y = 0; y < 40; y++)
			{
				addEnemyEntity(new Soldier(random.nextInt(gameEngine.getFrame().getWidth() - 20), 0));
				temp1 = temp1 + 50;
			}
	}
	
	public void move()
	{		
		for(int i = 0; i < enemies.size(); i++)
		{		
			enemies.get(i).move();
			
			if(Physics.Collision(gameEngine.getPlayer(), enemies.get(i))){
				justOneBonus = true;
				if(gameEngine.getPlayer().getWeaponNum() == 2){
					gameEngine.getPlayer().setWeaponNum(1);
				}
				if(gameEngine.getPlayer().getWeaponNum() == 3){
					gameEngine.getPlayer().setWeaponNum(2);
				}
				if(enemies.get(i).getChanceOfX2Up() && justOneBonus){
					bonusHandler.addBonus(enemies.get(i).dropX2());
					justOneBonus = false;
				}
				if(enemies.get(i).getChanceOfX3Up() && justOneBonus){
					bonusHandler.addBonus(enemies.get(i).dropX3());
					justOneBonus = false;
				}
				if(enemies.get(i).getChanceOfLife() && justOneBonus){
					bonusHandler.addBonus(enemies.get(i).dropLife());
					justOneBonus = false;
				}
				removeEnemyEntity(enemies.get(i));
				numEnemyKilled++;
				gameEngine.getPlayer().decrementLife(1); 
			}
		}
		
		bonusHandler.update();
		
		for(int i = 0; i < swords.size(); i++)
		{
			tempSword = swords.get(i);
			tempSword.move();
			oneIsDead = true;
			
			for(int j = 0; (j < enemies.size()) && oneIsDead; j++){
				if(Physics.Collision(tempSword, enemies.get(j))){
					
					removeSwordEntity(swords.get(i));
					oneIsDead = false;
					justOneBonus = true;
					if(enemies.get(j).getChanceOfX2Up() && justOneBonus){
						bonusHandler.addBonus(enemies.get(j).dropX2());
						justOneBonus = false;
					}
					if(enemies.get(j).getChanceOfX3Up() && justOneBonus){
						bonusHandler.addBonus(enemies.get(j).dropX3());
						justOneBonus = false;
					}
					if(enemies.get(j).getChanceOfLife() && justOneBonus){
						bonusHandler.addBonus(enemies.get(j).dropLife());
						justOneBonus = false;
					}
					removeEnemyEntity(enemies.get(j));
					numEnemyKilled++;
				}
			}
			
			//if(swords.get(i).getY() < 0){
			//	removeSwordEntity(swords.get(i));
			//}
		}
		
		//enemyBulletHandler.update();
		
		if(isBossReleased && !(isBossDead)){
			cercei.move();
			bossBulletHandler.update();
			
			if(bossFireStarted){
				cercei.fire();
				bossFireStarted = false;
			}
					
			for(int i = 0; i < (swords.size()) && !(isBossDead); i++)
			{
				tempSword = swords.get(i);
				tempSword.move();
				
				if(Physics.Collision(tempSword, cercei)){
					removeSwordEntity(swords.get(i));
					cercei.setHealth(cercei.getHealth() - 1);
					
					if(cercei.getHealth() == 0){
						isBossDead = true;
						cercei.getTimer().cancel();
						cercei.getTimer().purge();
						bossBulletHandler.removeAll();
						cercei = null;
					}
				}
			}
			
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			tempEnemy = enemies.get(i);
			tempEnemy.render(g);
		}
		
		for(int i = 0; i < swords.size(); i++)
		{
			tempSword = swords.get(i);
			tempSword.render(g);
		}
		
		if(isBossReleased && !(isBossDead)){
			bossBulletHandler.render(g);
		}

		bonusHandler.render(g);
		/*for(int i = 0; i < enemyBulletHandler.getFires().size(); i++)
		{
			tempBullet = (enemyBulletHandler.getFires().get(i));
			tempBullet.render(g);
		}
		*/
		
		if(isBossReleased && !(isBossDead)){
			cercei.render(g);
		}
	}
	
	public void releaseTheBoss(){
		isBossReleased = true;
	}
	
	public void addEnemyEntity(Soldier soldier)
	{
		enemies.add(soldier);
	}
	
	public void removeEnemyEntity(Soldier soldier)
	{
		enemies.remove(soldier);
	}
	
	public void addSwordEntity(Sword sword)
	{
		swords.add(sword);
	}
	
	public void removeSwordEntity(Sword sword)
	{
		swords.remove(sword);
	}
	
	public LinkedList<Sword> getSwords(){
		return swords;
	}
	
	public LinkedList<Soldier> getEnemies(){
		return enemies;
	}
	
	public BossPalpatine getBoss(){
		return cercei;
	}
	
	public boolean isBossDead(){
		return isBossDead;
	}
	
	public int getNumEnemyKilled(){
		return numEnemyKilled;
	}
	
	public void setNumEnemyKilled(int killed){
		numEnemyKilled = killed;
	}
}

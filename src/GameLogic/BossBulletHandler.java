package GameLogic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jon.Entities.EntityB;
import com.jon.Entities.FireOne;

public class BossBulletHandler {

	private GameEngine gameEngine;
	private LinkedList<FireOne> fires;
	public static BossBulletHandler bossBulletHandler;
	
	public BossBulletHandler() {
		gameEngine = GameEngine.getInstance();
		bossBulletHandler = null;
		fires = new LinkedList<FireOne>();
	}

	public static BossBulletHandler getInstance(){
		if(bossBulletHandler == null){
			bossBulletHandler = new BossBulletHandler();
			return bossBulletHandler;
		}
		else{
			return bossBulletHandler;
		}	
	}
	
	public void addBullet(FireOne fireOne){
		fires.add(fireOne);
	}
	
	public void render(Graphics g){
			for(int i = 0; i < fires.size(); i++){
				fires.get(i).render(g);
		}
	}
	
	public void update(){
			for(int i = 0; i < fires.size(); i++){
				fires.get(i).move();
				
				if(fires.get(i).getY() > gameEngine.getFrame().getHeight()){
					fires.remove(fires.get(i));
				}
				if(fires.size() != 0){
					if(Physics.Collision(gameEngine.getPlayer(),fires.get(i))){
						fires.remove(fires.get(i));
						gameEngine.getPlayer().decrementLife(1);
					}
				}
			}
	}
	
	public void removeAll(){
		for(int i = 0; i < fires.size(); i++){
			fires.remove(i);
		}
	}
	
	public List<FireOne> getFires() {
		return fires;
	}
	
	public void setNull() {
		bossBulletHandler = null;
	}
}

package GameLogic;

import java.awt.Graphics;
import java.util.LinkedList;
import com.jon.Entities.Bonus;
import com.jon.Entities.Life;
import com.jon.Entities.X2UP;
import com.jon.Entities.X3UP;

public class BonusHandler {

	private GameEngine gameEngine;
	private LinkedList<Bonus> bonuses;
	public static BonusHandler bonusHandler;
	
	public BonusHandler() {
		gameEngine = GameEngine.getInstance();
		bonusHandler = null;
		bonuses = new LinkedList<Bonus>();
	}

	public static BonusHandler getInstance(){
		if(bonusHandler == null){
			bonusHandler = new BonusHandler();
			return bonusHandler;
		}
		else{
			return bonusHandler;
		}	
	}
	
	public void addBonus(Bonus bonus){
		bonuses.add(bonus);
	}
	
	public void render(Graphics g){
			for(int i = 0; i < bonuses.size(); i++){
				bonuses.get(i).render(g);
		}
	}
	
	public void update(){
			for(int i = 0; i < bonuses.size(); i++){
				bonuses.get(i).move();
				if(Physics.Collision(gameEngine.getPlayer(),bonuses.get(i))){
					if(bonuses.get(i) instanceof Life){
						gameEngine.getPlayer().incrementLife();
					}
					if(bonuses.get(i) instanceof X2UP){
						gameEngine.getPlayer().setWeaponNum(2);
					}
					if(bonuses.get(i) instanceof X3UP){
						gameEngine.getPlayer().setWeaponNum(3);
					}
					bonuses.remove(bonuses.get(i));
				}
				else if(bonuses.get(i).getY() > gameEngine.getFrame().getHeight()){
					bonuses.remove(bonuses.get(i));
				}
			}
	}
	
	public void removeAll(){
		for(int i = 0; i < bonuses.size(); i++){
			bonuses.remove(i);
		}
	}
	
	public LinkedList<Bonus> getBonuses() {
		return bonuses;
	}
	
	public void setNull() {
		bonusHandler = null;
	}
}

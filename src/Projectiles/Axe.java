package Projectiles;

import Batman.TheBatman;
import Game.GamePanel;

public class Axe extends Projectile{

	public boolean hit;


	public Axe(boolean right, int x, int y, GamePanel gp) {
		super(right, x, y, gp);
		// TODO Auto-generated constructor stub
	}
	
	
	public void damage(TheBatman batman) {
		if (x >= batman.x+10 && x <= batman.x +70 && batman.y > 300) {
				batman.health -= 20;
				hit = true;
		}
	}
}

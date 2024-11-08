package Projectiles;

import java.awt.Color;
import java.awt.Graphics2D;//38-41 walk, 42-47 backflip, 48-51 kick, 52-55 dying

import Batman.TheBatman;
import Game.GamePanel;

public class Bullet extends Projectile{

	public boolean hit;

	public Bullet(boolean right, int x, int y, GamePanel gp) {
		super(right, x, y, gp);
		if (right) {
			Direction = true;
			this.x = x+100;				
		}
		else {
			this.x = x;
		}
		// TODO Auto-generated constructor stub
	}
	
	public void damage(TheBatman batman) {
		if (x >= batman.x+10 && x <= batman.x +70 && batman.y > 300) {
				batman.health -= 20;
				hit = true;
						
					
				
			
		}
	}


}
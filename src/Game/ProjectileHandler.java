package Game;

import java.util.ArrayList;
import java.util.Iterator;

import Batman.TheBatman;
import Projectiles.Axe;
import Projectiles.Bullet;

public class ProjectileHandler {
	ArrayList<Bullet> bullets;
	ArrayList<Axe> axes;
	GamePanel gp;
	TheBatman batman;
	
	public ProjectileHandler(GamePanel gp, TheBatman batman) {
		this.gp = gp;
		this.batman = batman;
	}
	
	public void handleBullets() {
		Iterator<Bullet> iterator = bullets.iterator();
	    while (iterator.hasNext()) {
	    	Bullet bullet = iterator.next();
		    bullet.damage(batman);
		    bullet.move();
		    if (bullet.hit) {
		    	iterator.remove();		    	
		    }
		    if(bullet.x > gp.WIDTH || bullet.x < 0) {
		    	iterator.remove();
		    }
		    //if (bullet.x)
	    }
	}
	
	
	public void handleAxes() {
		Iterator<Axe> iterator = axes.iterator();
	    while (iterator.hasNext()) {
	    	Axe axe = iterator.next();
		    axe.damage(batman);
		    axe.move();
		    if (axe.hit) {
		    	iterator.remove();		    	
		    }
		    if(axe.x > gp.WIDTH || axe.x < 0) {
		    	iterator.remove();
		    }
		    //if (bullet.x)
	    }
	}


}

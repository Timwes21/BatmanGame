package Enemy;

import java.util.ArrayList;

import Game.GamePanel;
import Game.TheBatman;
import Projectiles.Axe;
import Projectiles.Bullet;
import Projectiles.Projectile;

public class AxeGuy extends Enemy{
	Sprites sprites;
	String[] throwAxeSprites = {};
	boolean throwAxe;
	
	public AxeGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		sprites = new Sprites();
		axes = new ArrayList<>();
		attackFrequency = 40;
		bullets = new ArrayList<>();
		standingSprite= sprites.axeGuyStanding;
		attackSprites = sprites.axeGuySwingAxe;
		walkingSprites = sprites.axeGuyWalking;
		dyingSprites = sprites.axeGuyDying;
		throwAxeSprites = sprites.axeGuyAxeThrow;
		knockedOutSprites = sprites.axeGuyKnockedOut;
		currentSpritePath = standingSprite;
		this.y = y -6;
		
	}

	
	public void attack2() {
		System.out.println(inRange);
		if (inRange == false) {
			if (throwAxe) {
				throwAxe = animate(throwAxeSprites, throwAxe);
			}
			else {//if the spriteTracker is divisible by 20, enemy will stab
				strikeTracker += .5;
				if (strikeTracker % attackFrequency == 0) {
					throwAxe = true;
				}
			}	
		}
	}
	
	public void damage2() {
		if (axes.size() > 0) {
			for (int i=0;i<axes.size();i++) {
				if (axes.get(i).x >= batman.x+10 && axes.get(i).x <= batman.x +70 && batman.y > 300) {
					if (batman.block == false) {
						batman.health -= 20;
						axes.remove(i);
						
					}
				}
			}
		}
	}
	
	public void throwAxe() {
		System.out.println(axes.size());
		if (currentSpritePath == throwAxeSprites[1]) {
			if (axes.size() < 1) {
				axes.add(new Axe(right, x, y, gp));
		}
	}
	}
	
	public void removeAxe() {
		if (axes.size() > 0) {
			for(int i=0;i<axes.size();i++) {
				axes.get(i).move();
				if (axes.get(i).x < 0 || axes.get(i).x > gp.WIDTH) {
					axes.remove(axes.get(i));
				
				}
			}
		}
	}
	
	@Override
	public void addedMethods() {
		attack2();
		removeAxe();
		throwAxe();
		damage2();
		
		
	}


}

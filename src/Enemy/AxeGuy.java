package Enemy;

import java.util.ArrayList;

import Batman.TheBatman;
import Game.GamePanel;
import Projectiles.Axe;
import Projectiles.Bullet;
import Projectiles.Projectile;

public class AxeGuy extends Enemy{
	Sprites sprites;
	String[] throwAxeSprites = {};
	boolean strike2;
	double strike2Tracker;
	private double ammoCooldown;
	
	public AxeGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		sprites = new Sprites();
		attackFrequency = 40;
		standingSprite= sprites.axeGuyStanding;
		attackSprites = sprites.axeGuySwingAxe;
		walkingSprites = sprites.axeGuyWalking;
		dyingSprites = sprites.axeGuyDying;
		throwAxeSprites = sprites.axeGuyAxeThrow;
		knockedOutSprites = sprites.axeGuyKnockedOut;
		currentSpritePath = standingSprite;
		this.y = y -6;
		layingSprite = 2;
		ammo = 1;
		
	}

	
	public void attack2() {
		if (inRange == false) {
			if (strike2) {
				strike2 = animate(throwAxeSprites, strike2);
			}
			else {//if the spriteTracker is divisible by 20, enemy will stab
				strike2Tracker += .5;
				if (strike2Tracker % 80 == 0) {
					strike2 = true;
				}
			}	
		}
	}
	
	
	public void throwAxe() {
		if (currentSpritePath == throwAxeSprites[1]) {
			throwAxe = true;
	}
		else {
			throwAxe = false;
		}
	}
	
	
	@Override
	public void addedMethods() {
		attack2();
		throwAxe();
		ammoCooldown();
		
		
	}
	
	public void ammoCooldown() {
		ammoCooldown += .5;
		if (ammoCooldown  == 80) {
			ammo = 1;
			ammoCooldown = 0;
		}
	}


}

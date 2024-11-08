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
		
	}

	
	public void attack2() {
		System.out.println(strikeTracker);
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
		
		
	}


}

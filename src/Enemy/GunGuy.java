package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Batman.TheBatman;
import Game.GamePanel;
import Projectiles.Bullet;

public class GunGuy extends Enemy {
	Sprites sprites;

	public GunGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		sprites = new Sprites();
		attackFrequency = 40;
		standingSprite= sprites.gunGuyStanding;
		attackSprites = sprites.gunGuyShooting;
		walkingSprites = sprites.gunGuyWalking;
		dyingSprites = sprites.gunGuyDying;
		knockedOutSprites = sprites.gunGuyKnockedOut;
		currentSpritePath = standingSprite;
		layingSprite = 3; 
	}
	
	@Override
	public void attack() {
		if (x <= awarenessRight || x >= awarenessLeft) {
			if (strike) {
				strike = animate(attackSprites, strike);
			}
			else {//if the spriteTracker is divisible by 20, enemy will stab
				strikeTracker += .5;
				if (strikeTracker % attackFrequency == 0) {
					strike = true;
				}
			}	
		}
	}
	
	
	
	@Override
	public void damage() {
		
	}
	@Override
	public void move() {
		if (x > awarenessRight || x < awarenessLeft) {
			if (right) {
				x += moveSpeed;
			}
			else {
				x -= moveSpeed;
			}
			animate(walkingSprites, false);
		}
		else {
			currentSpritePath = standingSprite;
		}
	}
	
	public void shoot() {
		if (currentSpritePath == attackSprites[1]) {
				shootBullet = true;
		}
		else {
			shootBullet = false;
		}
	}
	


	

	@Override
	public void addedMethods() {
		shoot();

		
	}
	
	
	

	
}

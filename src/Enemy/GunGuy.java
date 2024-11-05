package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.GamePanel;
import Game.TheBatman;
import Projectiles.Bullet;

public class GunGuy extends Enemy {
	Sprites sprites;

	public GunGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		sprites = new Sprites();
		attackFrequency = 40;
		bullets = new ArrayList<>();
		standingSprite= sprites.gunGuyStanding;
		attackSprites = sprites.gunGuyShooting;
		walkingSprites = sprites.gunGuyWalking;
		dyingSprites = sprites.gunGuyDying;
		currentSpritePath = standingSprite;
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
		if (bullets.size() > 0) {
			for (int i=0;i<bullets.size();i++) {
				if (bullets.get(i).x >= batman.x+10 && bullets.get(i).x <= batman.x +70 && batman.y > 300) {
					if (batman.block == false) {
						batman.health -= 20;
						bullets.remove(i);
						
					}
				}
			}
		}
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
			if (bullets.size() < 1) {
				bullets.add(new Bullet(right, x, y, gp));
		}
	}
	}
	
	public void removeBullet() {
		if (bullets.size() > 0) {
			for(int i=0;i<bullets.size();i++) {
				bullets.get(i).move();
				if (bullets.get(i).x < 0 || bullets.get(i).x > gp.WIDTH) {
					bullets.remove(bullets.get(i));
				
				}
			}
		}
	}



	@Override
	public void addedMethods() {
		shoot();
		removeBullet();
		
	}
	
	
	

	
}

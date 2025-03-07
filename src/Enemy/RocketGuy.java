package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Batman.TheBatman;
import Game.GamePanel;
import Projectiles.Batarang;
import Projectiles.Bullet;

public class RocketGuy extends Enemy{
	private int bulletX;
	double ammoCooldown;


	public RocketGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		// TODO Auto-generated constructor stub
		this.WIDTH = WIDTH + 55;
		this.y = y-5;
		sprites = new Sprites();
		standingSprite= sprites.RocketGuyIdle;
		attackSprites = sprites.RocketGuyShoot;
		dyingSprites = sprites.RocketGuyDying;
		knockedOutSprites = sprites.RocketGuyKnockedOut;
		currentSpritePath = standingSprite;
		attackFrequency = 70;
		layingSprite = 3;
		ammo =1;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		Image CurrentSprite = new ImageIcon(currentSpritePath).getImage();
		if(flipImage) {
			g2.drawImage(CurrentSprite, x+WIDTH+20, y, -WIDTH, HEIGHT, gp);
		}
		else{
			g2.drawImage(CurrentSprite, x, y, WIDTH, HEIGHT, gp);
			
		}
		g2.setColor(Color.red);
		g2.fillRect(x+40, y-20, displayLackOfHealth, 8);
		g2.setColor(Color.green);
		g2.fillRect(x+40, y-20, (int)(health *.80), 8);
	
		
	}
	
	@Override
	public void move() {
		
	}
	
	
	@Override
	public void damage() {
	}
	
	public void shoot() {
		if (currentSpritePath == attackSprites[4]) {
			shootRocket = true;
		}
		else {
			shootRocket = false;
		}
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
	public void findRange() {
		if (x > awarenessLeft && x < awarenessRight) {
			inRange = true;
		}
		else {
			inRange = false;
		}
		
		if (left) {
			
		
			if (batman.x > x -50 && batman.x < x+50) {
				damageRange = true;
			}
			else {
				damageRange = false;
			}
		}
		else {
			if (batman.x > x && batman.x < x+100) {
				damageRange = true;
			}
			else {
				damageRange = false;
			}
			
		}
		
		
		if (batman.getBatarangs() != null) {
			for (Batarang batarang: batman.batarangs) {
				if (batarang.x >= x+20 && batarang.x <= x+80) {
					rangDamageRange = true;
				}
				else {
					rangDamageRange = false;
				}
			}
		}
	}

	public void ammoCooldown() {
		ammoCooldown += .5;
		if (ammoCooldown % 80 == 0) {
			ammo = 1;
			//ammoCooldown = 0;
		}
	}

	

	@Override
	public void addedMethods() {
		// TODO Auto-generated method stub
		shoot();
		ammoCooldown();
	}

}

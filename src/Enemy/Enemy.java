package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Batman.TheBatman;
import Projectiles.Axe;
import Projectiles.Batarang;
import Projectiles.Bullet;
import Game.GamePanel;


public abstract class Enemy{
	Random rand = new Random();
	int moveSpeed = rand.nextInt(1, 4);
	public int x;
	public int y;
	public int health;
	int healthBarX = x;
	int healthBarY = y-20;
	int awarenessLeft;
	int awarenessRight;
	int attackFrequency = 20;
	public int displayLackOfHealth;
	public int WIDTH;
	public int HEIGHT;
	int layingSprite;

	
	float movementSpeed = .1f;
	float strikeTracker = 1;
	float index;
	protected float indexx;
	float sleepTracker;
	
	
	boolean flipImage;
	boolean standing;
	boolean left;
	public boolean right;
	public boolean alive = true;
	boolean strike;
	public boolean knockedOut;
	public boolean drawBullet;
	public boolean damageRange;
	public boolean rangDamageRange;
	public boolean shootBullet;
	public boolean shootRocket;
	public boolean throwAxe;
	boolean inRange;

	
	String standingSprite;
	String[] attackSprites = {};
	String[] walkingSprites = {};
	String[] dyingSprites = {};
	String[] knockedOutSprites = {};
	String currentSpritePath;
	
	
	ArrayList<Axe> axes;
	GamePanel gp;
	TheBatman batman;
	Sprites sprites;
	
	public Enemy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health){
		this.x = x;
		this.y = y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.gp = gp;
		this.batman = batman;
		this.health = health;
		this.displayLackOfHealth = (int)(health *.80);
	}
	
	
	public void draw(Graphics2D g2) {
		Image CurrentSprite = new ImageIcon(currentSpritePath).getImage();
		if(flipImage) {
			g2.drawImage(CurrentSprite, x+WIDTH, y, -WIDTH, HEIGHT, gp);
		}
		else{
			g2.drawImage(CurrentSprite, x, y, WIDTH, HEIGHT, gp);
			
		}
		g2.setColor(Color.red);
		g2.fillRect(x, y-20, displayLackOfHealth, 8);
		g2.setColor(Color.green);
		g2.fillRect(x, y-20, (int)(health *.80), 8);
		
		
		if (axes != null) {
			if (axes.size() > 0) {
				Iterator<Axe> iterator = axes.iterator();
			    while (iterator.hasNext()) {
			    	Axe axe = iterator.next();
				    axe.draw(g2);
			    }
			}
		}
		
		
		/*if (axes.size() > 0) {	
		    Iterator<Axe> iterator = axes.iterator();
		    while (iterator.hasNext()) {
		        Axe axe = iterator.next();
		        axe.draw(g2);
		    }
		}*/
		
		
	}

	public void movement() {
		setEnemyAwareness();
		
		if(health > 0) {
			if (knockedOut) {
				knockout();
			}
			else {
				move();	
				attack();
				setDirectionOfEnemy();
				damage();
				backInScreen();
				findRange();
				addedMethods();
			}
		}
		else {
			alive();
		}
		
		
	}
	
	public void move() {
		if (x >= awarenessLeft && x <= batman.x-40 || x <= awarenessRight && x >= batman.x+50) {
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
		if (x > gp.WIDTH) {
			x -= moveSpeed;
		}
		else if(x+WIDTH < 0) {
			x += moveSpeed;
		}
	}

	
	public void attack() {
		if (x >= batman.x-40 && x <= batman.x+50 && batman.y > 250) {
			if (strike) {
				strike = animate(attackSprites, strike);
			}
			else {
				strikeTracker += .5;
				if (strikeTracker % attackFrequency == 0) {
					strike = true;
					
				}
			}	
		}
	}
	
	public void alive() {
		alive = animate(dyingSprites, alive);
	}
	
	public void knockout() {
		sleepTracker += .5;
		System.out.println(sleepTracker);	
		if (currentSpritePath == knockedOutSprites[layingSprite]) {
			currentSpritePath = knockedOutSprites[layingSprite];
			if (sleepTracker > 200) {
				knockedOut = false;
				sleepTracker = 0;
				}
			}
		else if (knockedOut) {
			animate(knockedOutSprites);	
		}
	}
	
	public void damage() {
		if (currentSpritePath == attackSprites[layingSprite]) {//Batman takes damage if enemy is stabbing
			if (batman.block == false)
				batman.health -= 5;
		}
	}
	
	public void setDirectionOfEnemy() {
		if (batman.x < x) {
			left = true;
			right = false;
			flipImage = false;
		}
		else {
			right = true;
			left = false;
			flipImage = true;
		}
	}
	
	public boolean animate(String[] sprites, boolean automateSwitch) {
		if (indexx >= sprites.length-1) {
			indexx = 0;
			automateSwitch = false;
		}
		indexx += movementSpeed;
		currentSpritePath = sprites[(int)indexx];
		return automateSwitch;
	}
	
	public void animate(String[] sprites) {
		index += movementSpeed;
		currentSpritePath = sprites[(int)index];
		if (index >= sprites.length-1) {
			index = 0;
		}
	}
	
	
	public void setEnemyAwareness() {
		awarenessLeft = batman.x-300;
		awarenessRight = batman.x+300;
	}
	
	public void backInScreen() {
		if (x > gp.WIDTH) {
			x -= moveSpeed;
		}
		else if(x+WIDTH < 0) {
			x += moveSpeed;
		}
		
	}
	
	public void findRange() {
		if (x > awarenessLeft && x < awarenessRight) {
			inRange = true;
		}
		else {
			inRange = false;
		}
		
		if (batman.x > x -50 && batman.x < x+50) {
			damageRange = true;
		}
		else {
			damageRange = false;
		}
		
		if (batman.getBatarangs() != null) {
			for (Batarang batarang: batman.batarangs) {
				if (batarang.x >= x+10 && batarang.x <= x +35) {
					rangDamageRange = true;
				}
				else {
					rangDamageRange = false;
				}
			}
		}
	}
	
	public abstract void addedMethods();
	
	
	
	
	
	
	
	
	
	
	
}
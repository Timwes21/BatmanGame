package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;

import Projectiles.Axe;
import Projectiles.Batarang;
import Projectiles.Bullet;
import Game.GamePanel;
import Game.TheBatman;


public abstract class Enemy{
	Random rand = new Random();
	int moveSpeed = rand.nextInt(1, 4);
	float movementSpeed = .1f;
	int awarenessLeft;
	int awarenessRight;
	float strikeTracker = 1;
	float index;
	int attackFrequency = 20;
	boolean flipImage;
	boolean standing;
	boolean left;
	boolean right;
	public boolean alive = true;
	boolean strike;
	public boolean knockedOut;
	public boolean drawBullet;
	TheBatman batman;
	public int health;
	public int x;
	public int y;
	int healthBarX = x;
	int healthBarY = y-20;
	public int WIDTH;
	public int HEIGHT;
	public int displayLackOfHealth;
	String standingSprite;
	String[] attackSprites = {};
	String[] walkingSprites = {};
	String[] dyingSprites = {};
	String[] knockedOutSprites = {};
	String currentSpritePath;
	ArrayList<Bullet> bullets;
	ArrayList<Axe> axes;
	Sprites sprites;
	
	GamePanel gp;
	private int knockedOutIndex;
	protected float indexx;
	boolean inRange;
	public boolean damageRange;
	public boolean rangDamageRange;
	
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
		
		if(bullets != null) {
			if (bullets.size() > 0) {	
			    Iterator<Bullet> iterator = bullets.iterator();
			    while (iterator.hasNext()) {
			    	Bullet bullet = iterator.next();
				    bullet.draw(g2);
			    }
			}
		}
		
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
			else {//if the spriteTracker is divisible by 20, enemy will stab
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
		if (knockedOut) {
			knockedOut = animate(knockedOutSprites, knockedOut);		
		}
	}
	
	public void damage() {
		if (currentSpritePath == attackSprites[3]) {//Batman takes damage if enemy is stabbing
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
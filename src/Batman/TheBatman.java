package Batman;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Enemy.Enemy;
import Game.GamePanel;
import Game.KeyHandler;
import Projectiles.Batarang;

public class TheBatman extends Rectangle{
	int runSpeed = 2;
	int WIDTH = 100;
	int HEIGHT = 150;
	int startingX = 100/2;
	int startingY = 500 - 200;
	float leftIndex;
	public int x;
	public int y;
	public int batarangX;
	int batarangY;
	int healthBarX = 0;
	int healthBarY = 450;
	int punchChoice = 1;
	int kickChoice = 1;
	public int health;
	int batarnagAmount;
	int counter;
	public int healthCopy;
	float index = 0;
	float jumpSpeed = 7.5f;
	float jumpIndex = 0;
	float fallIndex = 0;
	float standingIndex = 0;
	float punchSpeed;
	float kickIndex;
	float throwingIndex;
	float superPunchIndex;
	float smokeIndex;
	GamePanel gp;
	KeyHandler keys;
	String[] standingSprites = {"Resources\\Batman standing\\standing1.png", "Resources\\Batman standing\\standing2.png", "Resources\\Batman standing\\standing3.png", "Resources\\Batman standing\\standing4.png", "Resources\\Batman standing\\standing5.png", "Resources\\Batman standing\\standing6.png", "Resources\\Batman standing\\standing7.png", "Resources\\Batman standing\\standing8.png", "Resources\\Batman standing\\standing9.png", "Resources\\Batman standing\\standing10.png", "Resources\\Batman standing\\standing11.png", "Resources\\Batman standing\\standing12.png", "Resources\\Batman standing\\standing13.png", "Resources\\Batman standing\\standing14.png", "Resources\\Batman standing\\standing15.png", "Resources\\Batman standing\\standing16.png"};
	String[] runningSprites = {"Resources\\Batman running\\running1.png", "Resources\\Batman running\\running2.png", "Resources\\Batman running\\running3.png", "Resources\\Batman running\\running4.png", "Resources\\Batman running\\running5.png", "Resources\\Batman running\\running6.png", "Resources\\Batman running\\running7.png", "Resources\\Batman running\\running8.png", "Resources\\Batman running\\running9.png", "Resources\\Batman running\\running10.png", "Resources\\Batman running\\running11.png", "Resources\\Batman running\\running12.png", "Resources\\Batman running\\running13.png", "Resources\\Batman running\\running14.png", "Resources\\Batman running\\running15.png", "Resources\\Batman running\\running16.png", "Resources\\Batman running\\running17.png", "Resources\\Batman running\\running18.png", "Resources\\Batman running\\running19.png", "Resources\\Batman running\\running20.png", "Resources\\Batman running\\running21.png", "Resources\\Batman running\\running22.png"};
	String[] punching1Sprites = {"Resources\\Batman punching\\punch1-1.png", "Resources\\Batman punching\\punch1-2.png", "Resources\\Batman punching\\punch1-3.png", "Resources\\Batman punching\\punch1-4.png", "Resources\\Batman punching\\punch1-5.png", "Resources\\Batman punching\\punch1-6.png", "Resources\\Batman punching\\punch1-7.png", "Resources\\Batman punching\\punch1-8.png", "Resources\\Batman punching\\punch1-9.png"};
	String[] punching2Sprites = {"Resources\\Batman punching\\punch2-1.png", "Resources\\Batman punching\\punch2-2.png", "Resources\\Batman punching\\punch2-3.png", "Resources\\Batman punching\\punch2-4.png", "Resources\\Batman punching\\punch2-5.png", "Resources\\Batman punching\\punch2-6.png", "Resources\\Batman punching\\punch2-7.png", "Resources\\Batman punching\\punch2-8.png", "Resources\\Batman punching\\punch2-9.png"};
	String[] punching3Sprites = {"Resources\\Batman punching\\punch3-1.png", "Resources\\Batman punching\\punch3-2.png", "Resources\\Batman punching\\punch3-3.png", "Resources\\Batman punching\\punch3-4.png", "Resources\\Batman punching\\punch3-5.png", "Resources\\Batman punching\\punch3-6.png", "Resources\\Batman punching\\punch3-7.png", "Resources\\Batman punching\\punch3-8.png", "Resources\\Batman punching\\punch3-9.png", "Resources\\Batman punching\\punch3-10.png"};
	String[] jumpingSprites = {"Resources\\Batman jumping\\jump1.png", "Resources\\Batman jumping\\jump2.png", "Resources\\Batman jumping\\jump3.png" };
	String[] fallingSprites = {"Resources\\Batman jumping\\jump4.png", "Resources\\Batman jumping\\jump5.png", "Resources\\Batman jumping\\jump6.png", "Resources\\Batman jumping\\jump7.png"};
	String[] kicking1Sprites = {"Resources\\Batman kicking\\kick1-1.png", "Resources\\Batman kicking\\kick1-2.png", "Resources\\Batman kicking\\kick1-3.png", "Resources\\Batman kicking\\kick1-4.png", "Resources\\Batman kicking\\kick1-5.png", "Resources\\Batman kicking\\kick1-6.png", "Resources\\Batman kicking\\kick1-7.png", "Resources\\Batman kicking\\kick1-8.png", "Resources\\Batman kicking\\kick1-9.png"};
	String[] kicking2Sprites = {"Resources\\Batman kicking\\kick2-1.png", "Resources\\Batman kicking\\kick2-2.png", "Resources\\Batman kicking\\kick2-3.png", "Resources\\Batman kicking\\kick2-4.png", "Resources\\Batman kicking\\kick2-5.png", "Resources\\Batman kicking\\kick2-6.png", "Resources\\Batman kicking\\kick2-7.png", "Resources\\Batman kicking\\kick2-8.png", "Resources\\Batman kicking\\kick2-9.png", "Resources\\Batman kicking\\kick2-10.png"};
	String[] throwingSprites = {"Resources\\Batman Throwing\\throw1.png", "Resources\\Batman Throwing\\throw2.png", "Resources\\Batman Throwing\\throw3.png", "Resources\\Batman Throwing\\throw4.png", "Resources\\Batman Throwing\\throw5.png", "Resources\\Batman Throwing\\throw6.png", "Resources\\Batman Throwing\\throw7.png", "Resources\\Batman Throwing\\throw8.png", "Resources\\Batman Throwing\\throw9.png", "Resources\\Batman Throwing\\throw10.png", "Resources\\Batman Throwing\\throw11.png", "Resources\\Batman Throwing\\throw12.png", "Resources\\Batman Throwing\\throw13.png", "Resources\\Batman Throwing\\throw14.png", "Resources\\Batman Throwing\\throw15.png", "Resources\\Batman Throwing\\throw16.png", "Resources\\Batman Throwing\\throw17.png", "Resources\\Batman Throwing\\throw18.png", "Resources\\Batman Throwing\\throw19.png"};
	String[] superPunchSprites = {"Resources\\Batman super punch\\super_punch1.png", "Resources\\Batman super punch\\super_punch2.png", "Resources\\Batman super punch\\super_punch3.png", "Resources\\Batman super punch\\super_punch4.png", "Resources\\Batman super punch\\super_punch5.png", "Resources\\Batman super punch\\super_punch6.png", "Resources\\Batman super punch\\super_punch7.png", "Resources\\Batman super punch\\super_punch8.png", "Resources\\Batman super punch\\super_punch10.png", "Resources\\Batman super punch\\super_punch11.png", "Resources\\Batman super punch\\super_punch12.png", "Resources\\Batman super punch\\super_punch13.png", "Resources\\Batman super punch\\super_punch14.png", "Resources\\Batman super punch\\super_punch15.png", "Resources\\Batman super punch\\super_punch16.png", "Resources\\Batman super punch\\super_punch17.png"};
	String[] smokeSprites = {"Resources\\Batman smoke\\smoke1.png", "Resources\\Batman smoke\\smoke2.png", "Resources\\Batman smoke\\smoke3.png", "Resources\\Batman smoke\\smoke4.png", "Resources\\Batman smoke\\smoke5.png", "Resources\\Batman smoke\\smoke6.png", "Resources\\Batman smoke\\smoke7.png", "Resources\\Batman smoke\\smoke8.png", "Resources\\Batman smoke\\smoke9.png", "Resources\\Batman smoke\\smoke10.png", "Resources\\Batman smoke\\smoke11.png", "Resources\\Batman smoke\\smoke12.png", "Resources\\Batman smoke\\smoke13.png"};
	String blockSprite = "Resources\\Batman block\\block.png";
	public String[] punch;
	public String[] kick;
	public List<Batarang> batarangs = new ArrayList<>();
	public String currentSpritePath;
	boolean flipImage;
	boolean standing;
	boolean punchingAnimation;
	boolean punching;
	boolean jumping;
	boolean falling;
	boolean left;
	public boolean right;
	boolean kicking;
	boolean throwing;
	boolean superPunching;
	boolean rangIsMovin;
	public boolean block;
	boolean smoke;
	//141-144
	
	
	public TheBatman(int x, int y, GamePanel gp, KeyHandler keys, int health, int healthCopy){
		this.x = x;
		this.y = y;
		this.gp = gp;
		this.keys = keys;
		this.health = health;
		this.healthCopy = healthCopy;
		
		
		
		
	}
	
	
	
	
	public void moveLeft() {
		//Handles Movement
		if (x > 0)
			x -= 5;
		//Handles animation
		if (index > runningSprites.length - 1) {
			index = 4;			
		}
        index += .4;
        currentSpritePath = runningSprites[(int)index];
        if (index == 22) {
            index = 4;  
        }
	}
	
	public void moveRight() {
		//Handles Movement
		if (x < 1000 - WIDTH)
			x += 5;
		//Handles animation
		if (index > runningSprites.length - 1) {
			index = 4;			
		}
		index += .4;
        currentSpritePath = runningSprites[(int)index];
		if (index == 22) {
            index = 4;  
		}
	}
	
	public void jump(){
		//TODO jumping logic
		if (jumping) {
			flipImage = false;
			if (left) {
				flipImage = true;
			}
            if (y > 160) {
                y -= jumpSpeed;
                jumpIndex +=.2;
                currentSpritePath = jumpingSprites[(int)jumpIndex];
                if (jumpIndex >= jumpingSprites.length-1) {
                	jumpIndex = jumpingSprites.length -1;
                }
		}
            else  {
            	jumping = false;
            	falling = true;
            }
		}
		if (falling) {
			flipImage = false;
			if (left) {
				flipImage = true;
			}
			if (y < 302) {
				y+=7;
				fallIndex +=.2;
                currentSpritePath = fallingSprites[(int)fallIndex];
                if (fallIndex >= fallingSprites.length-1) {
                	fallIndex = fallingSprites.length -1;
                }
			}
			else {
				falling = false;
				jumpIndex =0;
				fallIndex = 0;
			}	
		}
	}
	
	public void punch() {
		//TODO handle damage

		//Handles animation
		if (punchChoice == 1) {
			punch = punching1Sprites;
		}
		if (punchChoice == 2) {
			punch = punching2Sprites;
		}
		if (punchChoice == 3) {
			punch = punching3Sprites;       
		}
        if(punching) {
			punchSpeed += .3;
	       	currentSpritePath = punch[(int)punchSpeed];
	       	if ((int)punchSpeed >= punch.length-1) {
	       		punching = false;
	       		punchSpeed = 0;
	       		punchChoice++;
	       		if (punchChoice == 4){
	       			punchChoice = 1;
	       		}
	       	}
	       	
	    }
        if (currentSpritePath == standingSprites[2]) {
        	punchChoice =1 ;
        }
        
         
    }
	
	public void standing() {
		if (standing) {
			standingIndex += .2;
	        currentSpritePath = standingSprites[(int)standingIndex];
			if (standingIndex >= standingSprites.length-1) {
				standingIndex = 0;  
			}
		}
	}
	
	public void kicking() {
		if (kickChoice == 1) {
			kick = kicking1Sprites;
		}
		if (kickChoice == 2) {
			kick = kicking2Sprites;
		}
		if(kicking) {
			kickIndex += .3;
	       	currentSpritePath = kick[(int)kickIndex];
	       	if ((int)kickIndex >= kick.length-1) {
	       		kicking = false;
	       		kickIndex = 0;
	       		kickChoice++;
	       		if (kickChoice == 3){
	       			kickChoice = 1;
	       		}
	       	}
	    }
        if (currentSpritePath == standingSprites[3]) {
        	kickChoice =1 ;
     }
	}
	
	public void throwingBatarang() {
		if (throwing) {
			if (throwingIndex > 3) {
				if (left)
					x -= 1;
				else
					x += 1;
				
			}
			if (left) {
				flipImage = false;
			}
			if (right) {
				flipImage = true;
			}
			throwingIndex += .3;
			currentSpritePath = throwingSprites[(int)throwingIndex]; 
			if ((int)throwingIndex >= throwingSprites.length-1) {
	       		throwing = false;
		       	throwingIndex = 0;
		   		
			}
			
			if (currentSpritePath == throwingSprites[5]) {
				if (batarangs.size() < 1) 
					batarangs.add(new Batarang(right, x, y, gp, 20));					
				
			}
		}
		
	}
	
	public void superPunch() {
		if (superPunching) {
			if (left) {
				flipImage = false;
			}
			if (right) {
				flipImage = true;
			}
			superPunchIndex += .3;
			currentSpritePath = superPunchSprites[(int)superPunchIndex]; 
			if ((int)superPunchIndex >= superPunchSprites.length-1) {
				superPunching = false;
				superPunchIndex = 0;
			}
		}
	}
	
	public void block() {
		currentSpritePath = blockSprite;
	}
	
	public void smoke() {
		if (smoke) {
			if (left) {
				flipImage = false;
			}
			if (right) {
				flipImage = true;
			}
			smokeIndex += .3;
			currentSpritePath = smokeSprites[(int)smokeIndex]; 
			if ((int)smokeIndex >= smokeSprites.length-1) {
	       		smoke = false;
		       	smokeIndex = 0;
		   		
			}
			
			
		}
	}
	
	public void movement(List<Enemy> enemies) {
			
		if (keys.left&& keys.A != true&& keys.S != true) {
			moveLeft();
			left = true;
			right = false;
			standing = false;
			flipImage = false;
		}
		else if (keys.right && keys.A != true && keys.S != true) {
			moveRight();
			right = true;
			left = false;
			standing = false;
			flipImage = true;
		}
		else if (keys.A) {
			punching = true;
			
		}
		else if (keys.S) {
			kicking = true;
		}
		else if(keys.Q && batarangs.size() < 1) {
			throwing = true;
		}
		else if(keys.D) {
			superPunching = true;
		}
		else if (keys.W) {
			smoke = true;
		}
		else if(keys.space) {
			block();
		}
		else {
			if (!jumping && !falling) {
				standing = true;		
			}
			flipImage = false;
			if (left) {
				flipImage = true;
			}
			standing();
		}
		if(keys.up) {		
			if (!falling) {
				jumping = true;
				standing = false;
			}
		}
		
		if(keys.space) {
			block();
			block = true;
		}
		else {
			block = false;
		}
		if (keys.A && keys.right) {
			flipImage = false;
		}
		else if(keys.A && keys.left) {
			flipImage = true;
		}
		
		punch();
		jump();
		kicking();
		superPunch();
		throwingBatarang();
		smoke();
		damage(enemies);	
		
		
	}
	
	public void damage(List<Enemy> enemies) {
		int enemiesSize = enemies.size();
		if (enemies != null) {
			for (Enemy enemy: enemies) {
				checkBatarangs(enemy, enemiesSize);
				if (enemy.damageRange) {
					if (currentSpritePath == punch[4]) {
						enemy.health -= 5;
					}
					if (currentSpritePath == kick[4]) {
						enemy.health -= 9;
					}
					if (currentSpritePath == superPunchSprites[4]) {
						enemy.health -= 10;
					}
			
				}
				
				if (enemy.x >= x-300 && enemy.x <= x+300) {
					if (currentSpritePath == smokeSprites[5]) {
						enemy.knockedOut = true;
					}
				}
        		
        		
        	}
        	
        }
		
		if (currentSpritePath == blockSprite) {
			block = true;
		}
		else {
			block = false;
		}
	}
	
	
	
		
		

	
	public void draw(Graphics2D g2) {
		Image CurrentSprite = new ImageIcon(currentSpritePath).getImage();
		if(flipImage) {
			g2.drawImage(CurrentSprite, x+WIDTH, y, -WIDTH, HEIGHT, gp);
		}
		else{
			g2.drawImage(CurrentSprite, x, y, WIDTH, HEIGHT, gp);
			
		}
		
		if (batarangs.size() > 0) {	
		    Iterator<Batarang> iterator = batarangs.iterator();
		    while (iterator.hasNext()) {
		        Batarang batarang = iterator.next();
		        batarang.draw(g2);
		    }
		}
		
		
		
		g2.setColor(Color.red);
		g2.fillRect(healthBarX, healthBarY, healthCopy, 20);
		g2.setColor(Color.green);
		g2.fillRect(healthBarX, healthBarY, health, 20);
		g2.setColor(Color.white);
		g2.setFont(new Font("MS Gothic", Font.BOLD, 23));
		g2.drawString("Health", healthBarX, healthBarY+20);
		
		
		
		
	}
	
	public void checkBatarangs(Enemy enemy, int enemiesSize) {
		System.out.println(batarangs.size());
		if (batarangs.size() > 0) {	
		    for (int i=0;i<batarangs.size();i++) {
		    	batarangs.get(i).move(enemiesSize);
				if (enemy.rangDamageRange) {
					enemy.health -=5;	
					enemy.knockedOut = true;
					batarangs.remove(i);
					enemy.rangDamageRange = false;
				}
				else if(batarangs.get(i).x > gp.WIDTH || batarangs.get(i).x < 0) {
					batarangs.remove(i);
				}
		    }
		}
	}
	
	public List<Batarang> getBatarangs() {
		return batarangs;
	}
	

	//helper functions
	public void animate(float index, String[] sprites, boolean action) {
		if (action) {
			index += .3;
	       	currentSpritePath = sprites[(int)index];
	       	if ((int)index >= sprites.length-1) {
	       		action = false;
	       		index = 0;
	       	}
		}
	}
	
	
	
	
	
		
	
	

	
	
	
	
	
	

}

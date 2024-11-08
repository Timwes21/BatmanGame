package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Batman.TheBatman;
import Game.GamePanel;

public class FlameMime extends Enemy{
	
	private int damageIndex;


	public FlameMime(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		// TODO Auto-generated constructor stub
		sprites = new Sprites();
		standingSprite= sprites.flameMimeStanding;
		attackSprites = sprites.flameMimeFlame;
		dyingSprites = sprites.flameMimeDying;
		knockedOutSprites = sprites.flameMimeKnockedOut;
		currentSpritePath = standingSprite;
		
	}
	
	public void draw(Graphics2D g2) {
		Image CurrentSprite = new ImageIcon(currentSpritePath).getImage();
		if(flipImage) {
			g2.drawImage(CurrentSprite, x+WIDTH+55, y, -WIDTH, HEIGHT, gp);
		}
		else{
			g2.drawImage(CurrentSprite, x, y, WIDTH, HEIGHT, gp);
			
		}
		g2.setColor(Color.red);
		g2.fillRect(x+30, y-20, displayLackOfHealth, 8);
		g2.setColor(Color.green);
		g2.fillRect(x+30, y-20, (int)(health *.80), 8);
		
	}
	
	@Override
	public void attack() {
		if (x >= batman.x-40 && x <= batman.x+50 && batman.y > 250 && left) {
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
		else if (right && x+20 <= batman.x && x+100 > batman.x ) {
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
		else {
			indexx = 0;
		}
	}
	
	@Override
	public void damage() {
		if (currentSpritePath == attackSprites[5] || currentSpritePath == attackSprites[6] || currentSpritePath == attackSprites[7] || currentSpritePath == attackSprites[8]) {//Batman takes damage if enemy is stabbing
			if (batman.block == false)
				batman.health -= 1;
		}
		
		
	}
	
	@Override
	public void move() {
		currentSpritePath = standingSprite;
		if (!strike) {
		}
	}


	@Override
	public void addedMethods() {
		// TODO Auto-generated method stub
		
	}

}

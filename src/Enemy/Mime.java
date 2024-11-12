package Enemy;

import java.util.ArrayList;

import Batman.TheBatman;
import Game.GamePanel;

public class Mime extends Enemy {

	private Sprites sprites;
	String[] backFlip;
	boolean backflip;




	public Mime(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health) {
		super(x, y, WIDTH, HEIGHT, gp, batman, health);
		// TODO Auto-generated constructor stub
		
		this.y = y-5;
		sprites = new Sprites();
		attackFrequency = 40;
		//bullets = new ArrayList<>();
		standingSprite= sprites.mimeStanding;
		attackSprites = sprites.mimeKicking;
		walkingSprites = sprites.mimeWalking;
		dyingSprites = sprites.mimeDying;
		backFlip = sprites.mimeBackFlip;
		knockedOutSprites = sprites.mimeKnockedOut;
		currentSpritePath = standingSprite;
		drawBullet = true;
		moveSpeed = 2;
		layingSprite = 3;
	}
	
	
	@Override
	public void move() {
		if (!backflip) {
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
		}
	}
	
	
	public void backflip() {
		if (batman.currentSpritePath == batman.punch[1] || batman.currentSpritePath == batman.kick[1]) {
			if (x > batman.x-40 && x <batman.x + 100)
				backflip = true;
				index = 0;
		}
		if (backflip) {
			movementSpeed = .2f;
			backflip = animate(backFlip, backflip);
			if (currentSpritePath != backFlip[backFlip.length-3])
				if (right) {
					x -= 3;
				}
				else {
					x+= 3;
				}
			
			//if (currentSpritePath == backFlip[backFlip.length-1]) {
				//backflip = false;
			
		}
		else {
			movementSpeed = .1f;
		}
	}

	@Override
	public void addedMethods() {
		backflip();
		
	}

}

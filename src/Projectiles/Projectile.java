package Projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Game.GamePanel;

public class Projectile{
	boolean Direction;
	public int x;
	private int y;
	int vel;
	GamePanel gp;
	String[] projectileSprites;
	private double index;
	String spritePath;
	String[] sprites = {};
	int adjustVertical;
	
	
	public Projectile(boolean right, int x, int y , GamePanel gp) {
		adjustVertical = 0;
		if (right) {
			Direction = true;
			this.x = x+40;				
		}
		else {
			this.x = x+20;
		}
		this.y = y + 12;
		vel = 4;
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		
		if (sprites.length > 0) {
			Image CurrentBatarangSprite = new ImageIcon(spritePath).getImage();
			g2.drawImage(CurrentBatarangSprite, x, y+adjustVertical, 20, 20, gp);
		}
		else {
			g2.setColor(Color.black);
			g2.fillOval(x, y, 10, 10);
			
		}
	}
	
	public void move() {
		if (Direction) {
			x += vel;
		}
		else {
			x -= vel;			
		}
	
	
		if (sprites.length > 0) {
			animate();
		}
	
	}
	
	public void animate() {
		index += .2;
		spritePath = sprites[(int)index];
		if (index >= sprites.length-1) {
			index=0;
		}
	}
	
	public void move(int enemiesSize) {
		if (Direction) {
			x += vel/enemiesSize;
		}
		else {
			x -= vel/enemiesSize;			
		}
	
	
		if (sprites.length > 0) {
			animate();
		}
	
	}

	
	
}

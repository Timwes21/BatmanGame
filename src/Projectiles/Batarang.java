package Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Game.GamePanel;

public class Batarang extends Projectile{	
	float index;
	String[] batarangSprites = {"Resources\\batarang\\batarang1.png", "Resources\\batarang\\batarang2.png", "Resources\\batarang\\batarang3.png", "Resources\\batarang\\batarang4.png"};
	GamePanel gp;
	
	public Batarang(boolean right, int x, int y, GamePanel gp, int size) {
		super(right, x, y, gp, size);
		vel = 11;
		sprites = batarangSprites;
		adjustVertical = 40;
		this.size = 20;
		// TODO Auto-generated constructor stub
	}


	
	
	
	

	
	
}


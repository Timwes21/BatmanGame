package Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Game.GamePanel;

public class Batarang extends Projectile{	
	float index;
	String[] batarangSprites = {"batarang\\batarang1.png", "batarang\\batarang2.png", "batarang\\batarang3.png", "batarang\\batarang4.png"};
	GamePanel gp;
	
	public Batarang(boolean right, int x, int y, GamePanel gp) {
		super(right, x, y, gp);
		vel = 11;
		sprites = batarangSprites;
		adjustVertical = 40;
		// TODO Auto-generated constructor stub
	}


	
	
	
	

	
	
}


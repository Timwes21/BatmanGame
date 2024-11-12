package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import Batman.TheBatman;
import Enemy.Enemy;
import Enemy.Sprites;
import Projectiles.Axe;
import Projectiles.Bullet;

public class Display {
	int WIDTH;
	int HEIGHT;
	Image BG = new ImageIcon("bg-game.png").getImage();
	Image BG_mainMenu = new ImageIcon("altpic.png").getImage();
	Image BUILDINGS = new ImageIcon("buildings.png").getImage();
	Image GROUND = new ImageIcon("ground.png").getImage();
	Image SIGNAL = new ImageIcon("Bat-Signal-PNG-Picture.png").getImage();
	GamePanel gp;
	Color transparent = new Color(0,0,0,200);
	String currentemblem;
	Sprites sprites;
	Font batFont = loadFont("GothamNightsBold-Wrp4.ttf", 24);
	Font batFont2 = loadFont("Andes.ttf", 60);

	
	
	Image enemy1Emblem;
	Image gunGuyEmblem;
	Image mimeEmblem;
	Image axeGuyEmblem;
	Image fireMimeEmblem;
	Image rocketGuyEmblem;
	
	String[] knifeGuyAnimation = {};
	String[] gunGuyAnimation = {};
	String[] mimeAnimation = {};
	String[] axeGuyAnimation = {};
	String[] fireMimeAnimation = {};
	String[] rocketGuyAnimation = {};
	
	String currentknifeGuySprite;
	String currentgunGuySprite;
	String currentMimeSprite;
	String currentAxeGuySprite;
	String currentFireMimeSprite;
	String currentRocketGuySprite;
	
	float knifeGuyIndex;
	float gunGuyIndex;
	float mimeIndex;
	float axeGuyIndex;
	float fireMimeIndex;
	float rocketGuyIndex;

	int n;
	//float index;
	int newIndex;

	
	Display(int WIDTH, int HEIGHT, GamePanel gp){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.gp = gp;
		sprites = new Sprites();
		knifeGuyAnimation = sprites.knifeGuyStabbingSprites;
		gunGuyAnimation = sprites.gunGuyShooting;
		mimeAnimation = sprites.mimeKicking;
		axeGuyAnimation = sprites.axeGuySwingAxe;
		fireMimeAnimation = sprites.flameMimeFlame;
		rocketGuyAnimation = sprites.RocketGuyShoot;
		
		n=1;
		
	}
	
	
	public void mainMenu(Graphics2D g2) {
		g2.drawImage(BG_mainMenu, 0, 0, WIDTH+40, HEIGHT, gp);
    	
    	g2.setFont(batFont2);
    	g2.setColor(Color.white);
    	g2.drawString("The Batman", (WIDTH/2)-120, HEIGHT/2);
    	
    	g2.setFont(batFont);
    	g2.drawString("Press Enter to Continue", WIDTH/2-100, HEIGHT/2+50);
	}
	
	public void game(boolean pause, boolean dead, TheBatman batman, List<Enemy> enemies, int wave, int enemyDefeats, List<Bullet> bullets, List<Axe> axes, Graphics2D g2) {
		g2.drawImage(BG, 0, 0, WIDTH+50, HEIGHT+25, gp);
		g2.drawImage(SIGNAL, 0, 0, 200, 400, gp);
		g2.drawImage(BUILDINGS, 0, 102, WIDTH+2, 350, gp);
		g2.drawImage(GROUND, 0-1, HEIGHT-70, WIDTH, 100, gp);
		
		//draws player
		batman.draw(g2);
		
		//draws all the enemies		
		List<Enemy> enemiesCopy = new ArrayList<>(enemies);
		for (Enemy enemy : enemiesCopy) {
		    enemy.draw(g2);
		}
		
		List<Bullet> bulletsCopy = new ArrayList<>(bullets);
		if(bulletsCopy != null) {
			if (bulletsCopy.size() > 0) {	
			    Iterator<Bullet> iterator = bulletsCopy.iterator();
			    while (iterator.hasNext()) {
			    	Bullet bullet = iterator.next();
				    bullet.draw(g2);
			    }
			}
		}
		
		
		List<Axe> axesCopy = new ArrayList<>(axes);
			if (axesCopy.size() > 0) {
				Iterator<Axe> iterator = axesCopy.iterator();
			    while (iterator.hasNext()) {
			    	Axe axe = iterator.next();
				    axe.draw(g2);
			    }
			}
		
			
		
		g2.setColor(Color.white);
		g2.setFont(batFont2);
		g2.drawString("Wave: " + wave, WIDTH/2-70,60);
		
		
		g2.setFont(new Font("Roboto Black", Font.BOLD, 30));
		if (pause) {
			pauseMenu(g2);
		}
		else {
			g2.drawString("press p to pause", WIDTH-240, 20);
		}
		
	
	
		if (dead) {
			g2.drawString("You Have Died", WIDTH/2-100, (HEIGHT/2));
			g2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));
			g2.drawString("Press Enter to Continue", WIDTH/2-80, (HEIGHT/2) +20);
	}
		g2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 10));
		g2.drawString("Enemies Defeated: " + enemyDefeats, 200, 20);
	}
	
	
	public void pauseMenu(Graphics2D g2) {
		int controlsX = WIDTH/2-240;
		int controlsY = HEIGHT/2-40;
		int enemiesListX = controlsX+250;
		//System.out.println(n);
		enemy1Emblem = new ImageIcon(currentknifeGuySprite).getImage();
		gunGuyEmblem = new ImageIcon(currentgunGuySprite).getImage();
		mimeEmblem = new ImageIcon(currentMimeSprite).getImage();
		axeGuyEmblem = new ImageIcon(currentAxeGuySprite).getImage();
		fireMimeEmblem = new ImageIcon(currentFireMimeSprite).getImage();
		rocketGuyEmblem = new ImageIcon(currentRocketGuySprite).getImage();
		g2.drawString("press r to resume", WIDTH-255, 20);
		g2.setColor(transparent);
		g2.fillRoundRect(WIDTH/2-250, 100, 500, 300, 25, 25);
		g2.setColor(Color.white);
		g2.drawString("PAUSED", WIDTH/2-51, (HEIGHT/2)-100);
		g2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 15));
		g2.drawString("CONTROLS:", controlsX, controlsY -30);
		g2.drawString("Run............LEFT RIGHT", controlsX, controlsY);
		g2.drawString("Jump...................UP", controlsX, controlsY + 20);
		g2.drawString("Punch...................A", controlsX, controlsY + 40);
		g2.drawString("Kick....................S", controlsX, controlsY + 60);
		g2.drawString("Super Punch.............D", controlsX, controlsY + 80);
		g2.drawString("Super Kick...LEFT/RIGHT+S", controlsX, controlsY + 100);
		g2.drawString("Batarang................Q", controlsX, controlsY + 120);
		g2.drawString("Smoke Grenade...........W", controlsX, controlsY + 140);
		g2.drawString("Block...............SPACE", controlsX, controlsY + 160);
		g2.drawLine(WIDTH/2, HEIGHT/2-80, WIDTH/2, 390);
		g2.drawString("ENEMIES:", enemiesListX, controlsY -30);
		g2.drawImage(enemy1Emblem, enemiesListX, controlsY-20, 50, 50, gp);
		g2.drawImage(gunGuyEmblem, enemiesListX + 90, controlsY-20, 50, 50, gp);
		g2.drawImage(mimeEmblem, enemiesListX +170, controlsY-20, 50, 50, gp);
		g2.drawImage(axeGuyEmblem, enemiesListX, controlsY+70, 50, 50, gp);
		g2.drawImage(fireMimeEmblem, enemiesListX + 90, controlsY+70, 50, 50, gp);
		g2.drawImage(rocketGuyEmblem, enemiesListX +160, controlsY+70, 70, 50, gp);
		g2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 10));
		g2.drawString("Knife guy", enemiesListX, controlsY+40);
		g2.drawString("Gun guy", enemiesListX+90, controlsY+40);
		g2.drawString("Mime", enemiesListX+90*2, controlsY+40);
		g2.drawString("Axe guy", enemiesListX+10, controlsY+130);
		g2.drawString("Fire Mime", enemiesListX+90, controlsY+130);
		g2.drawString("Rocket guy", enemiesListX+170, controlsY+130);
		animateEmblems();
		
	}
	
	public Font loadFont(String fontName, float fontSize) {
	    try (InputStream is = getClass().getResourceAsStream(fontName)) {
	        return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(fontSize);
	    } catch (FontFormatException | IOException e) {
	        e.printStackTrace();
	        return new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
	    }
	}
	
	
	public void animateEmblems() {
		knifeGuyIndex = animate(knifeGuyAnimation, knifeGuyIndex);
		currentknifeGuySprite = knifeGuyAnimation[(int)knifeGuyIndex];
		
		gunGuyIndex = animate(gunGuyAnimation,gunGuyIndex);
		currentgunGuySprite = gunGuyAnimation[(int)gunGuyIndex];
		
		mimeIndex = animate(mimeAnimation,mimeIndex);
		currentMimeSprite = mimeAnimation[(int)mimeIndex];
		
		axeGuyIndex = animate(axeGuyAnimation,axeGuyIndex);
		currentAxeGuySprite = axeGuyAnimation[(int)axeGuyIndex];
		
		fireMimeIndex = animate(fireMimeAnimation,fireMimeIndex);
		currentFireMimeSprite = fireMimeAnimation[(int)fireMimeIndex];
		
		rocketGuyIndex = animate(rocketGuyAnimation,rocketGuyIndex);
		currentRocketGuySprite = rocketGuyAnimation[(int)rocketGuyIndex];
	}
	
	
	public float animate(String[] sprites, float index) {
		index += .15;
		System.out.println(index);
		if (index >= sprites.length-1) {
			index = 0;
		}
		return index;
	}
}

package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import Enemy.AxeGuy;
import Enemy.Enemy;
import Enemy.FlameMime;
import Enemy.GunGuy;
import Enemy.KnifeGuy;
import Enemy.Mime;
import Enemy.RocketGuy;
import Enemy.Runner;
import Projectiles.Bullet;

public class GamePanel extends JPanel implements Runnable{
	public int WIDTH = 1000;
	int HEIGHT = 500;
	Random rand = new Random();
	Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
	
	int EnemySpawnX = rand.nextInt(900);
	int EnemySpawnY = 350;
	int wave;
	int takeAway;
	int healthBonus;
	int enemyDefeats;
	List<Enemy> enemies = new ArrayList<>();
	boolean pause;
	boolean mainMenu;
	int playerStartingX = 100/2;
	int playerStartingY = 500 - 195;
	KeyHandler keys = new KeyHandler();
	TheBatman batman = new TheBatman(playerStartingX, playerStartingY, this, keys, 100, 100);
	int waveNumber = 1;
	boolean spawn;
	boolean dead;
	Display display;
	ArrayList<Bullet> bullets;
	//JLabel emblem;

	
	
	
	
	
	GamePanel(){
		//emblem = new JLabel("emblempic.png");
		//emblem.setBackground(Color.red);
		setFocusable(true);
		setLayout(null);
		setPreferredSize(SCREEN_SIZE);
		//this.add(emblem);
		this.setDoubleBuffered(false);
		this.addKeyListener(keys); 
		Thread gameThread = new Thread(this);
		gameThread.start();
		mainMenu = true;
		dead = false;
		display = new Display(WIDTH, HEIGHT, this);
		bullets = new ArrayList<>();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
    	if (mainMenu) {
	    	display.mainMenu(g2);
    	}
    	else{
    		//draws background and scenery
			display.game(pause, dead, batman, enemies, wave, enemyDefeats, bullets, g2);
    	}
    	

		g2.dispose();
	}

	
	


	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000_000_000/amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				if (mainMenu) {
					if (keys.enter)
						mainMenu = false;
				}
				else if(batman.health < 1) {
					dead = true;
					reset();
				}
				else {
					if (pause == false) {
						//System.out.println(batman.batarangs.size());
						dead = false;
						batman.movement(enemies);
						enemyMovement();
						spawnEnemy();
						progressWave();
						handleBullets();
						//System.out.println(enemies.size());
					}
					else {
						
					}
					
				
					pauseTracker();
					
				}
				
				//checkCollision();
				repaint();
				delta--;
				//System.out.println("test");
			}
		}
		
		
	}
	
	public void spawnEnemy() {
		int EnemySpawnX = rand.nextInt(200, 950);
		//KnifeGuys being added
		if (enemies.size() < (wave * 2) - takeAway && wave < 6 && wave < 20) {
			GunGuy enemy2 = new GunGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy2);
			takeAway++;
		}
		
		/*if (enemies.size() <  (wave * 2) - takeAway && wave < 6) {
			KnifeGuy enemy1 = new KnifeGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy1);
			takeAway++;
		}
		if (enemies.size() < (wave * 2) - takeAway && wave < 6 && wave < 20) {
			GunGuy enemy2 = new GunGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy2);
			takeAway++;
		}
		if (enemies.size() < (wave/2) - takeAway && wave > 9) {
			Mime enemy3 = new Mime(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy3);
			takeAway++;
		}
		if (enemies.size() < (wave/2) - takeAway && wave > 19) {
			AxeGuy enemy4 = new AxeGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy4);
			takeAway++;
		}
		if (enemies.size() < (wave/2) - takeAway && wave > 24) {
			FlameMime enemy5 = new FlameMime(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy5);
			takeAway++;
		}
		if (enemies.size() < (wave/2) - takeAway && wave > 30) {
			RocketGuy enemy6 = new RocketGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy6);
			takeAway++;
		}*/
		
	}
	
	public void progressWave() {
		if (enemies.size() == 0 && dead == false) {
			wave++;
			healthBonus += 10;
			batman.health = 90 + healthBonus;
			batman.healthCopy = 90 + healthBonus;
			takeAway=0;
		}
	}
	

	
	public void enemyMovement() {
		System.out.println(bullets.size());
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy enemy = iterator.next();
			enemy.movement();
			if (enemy.alive == false ) {
				iterator.remove();
				enemyDefeats +=1;
				
			}
			if (enemy.shoot) {
				bullets.add(new Bullet(enemy.right, enemy.x, enemy.y, this));
				enemy.shoot = false;
				
			}
		}
	}
	
	public void pauseTracker() {
		if (keys.P == true) {
			pause = true;
		}
		if (keys.R == true) {
			pause = false;
		}
	}
	
	public void reset() {
		if (keys.enter) {
			healthBonus = 0;
			batman.healthCopy = 100;
			batman.x = playerStartingX;
			batman.y = playerStartingY;
			enemies.clear();
			
			wave = 0;
			batman.health = 100;
			
		}
	}
	
	
	public void handleBullets() {
		Iterator<Bullet> iterator = bullets.iterator();
	    while (iterator.hasNext()) {
	    	Bullet bullet = iterator.next();
		    bullet.damage(batman);
		    bullet.move();
		    if (bullet.hit) {
		    	iterator.remove();		    	
		    }
		    if(bullet.x > WIDTH || bullet.x < 0) {
		    	iterator.remove();
		    }
		    //if (bullet.x)
	    }
	}

}

	
		
	
	
	
	
	



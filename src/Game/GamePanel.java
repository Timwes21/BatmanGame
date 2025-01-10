package Game;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import Batman.TheBatman;
import Enemy.AxeGuy;
import Enemy.Enemy;
import Enemy.FlameMime;
import Enemy.GunGuy;
import Enemy.KnifeGuy;
import Enemy.Mime;
import Enemy.RocketGuy;
import Projectiles.Axe;
import Projectiles.Bullet;

public class GamePanel extends JPanel implements Runnable{
	/**
	 *
	 */
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
	public List<Enemy> enemies = new ArrayList<>();
	List<GunGuy> gunGuys = new ArrayList<>();
	List<AxeGuy> axeGuys = new ArrayList<>();
	List<RocketGuy> rocketGuys = new ArrayList<>();
	boolean pause;
	boolean mainMenu;
	int playerStartingX = 100/2;
	int playerStartingY = 500 - 195;
	KeyHandler keys = new KeyHandler();
	TheBatman batman;
	int waveNumber = 1;
	boolean spawn;
	boolean dead;
	Display display;
	ArrayList<Bullet> bullets;
	public ArrayList<Axe> axes;
	ArrayList<Bullet> rockets;
	HashMap<List<Enemy>, List<Axe>> AxeGuys;

	
	
	
	
	
	GamePanel(){
		
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
		axes = new ArrayList<>();
		rockets = new ArrayList<>();
		batman = new TheBatman(playerStartingX, playerStartingY, this, keys, 100, 100);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
    	if (mainMenu) {
	    	display.mainMenu(g2);
    	}
    	else{
    		//draws background and scenery
			display.game(pause, dead, batman, enemies, wave, enemyDefeats, bullets, axes, rockets, g2);
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
						
						dead = false;
						batman.movement(enemies);
						enemyMovement();
						spawnEnemy();
						progressWave();
						handleBullets();
						handleAxes();
						handleRockets();

					}
					else {
						
					}
					
				
					pauseTracker();
					
				}
				
				
				repaint();
				delta--;
				
			}
		}
		
		
	}
	
	public void spawnEnemy() {
		int EnemySpawnX = rand.nextInt(200, 950);
		//testing block
		/*if (enemies.size() < (wave * 2) - takeAway && wave < 6 && wave < 20) {
			
			RocketGuy enemy3 = new RocketGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy3);
			takeAway++;
		}*/
		
		if (enemies.size() <  (wave * 2) - takeAway && wave < 6) {
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
		}
		
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
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy enemy = iterator.next();
			enemy.movement();
			if (enemy.alive == false ) {
				iterator.remove();
				enemyDefeats +=1;
				
			}
			if (enemy.shootBullet) {
				bullets.add(new Bullet(enemy.right, enemy.x, enemy.y, this, 5));
				enemy.shootBullet = false;
				
			}
			
			if (enemy.throwAxe && enemy.ammo == 1) {
				axes.add(new Axe(enemy.right, enemy.x, enemy.y, this, 20));
				enemy.throwAxe = false;
				enemy.ammo = 0;
				
			}
			
			if (enemy.shootRocket && enemy.ammo == 1) {
				rockets.add(new Bullet(enemy.right, enemy.x, enemy.y+16, this, 10));
				enemy.shootRocket = false;
				enemy.ammo = 0;
				
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
			bullets.clear();
			axes.clear();
			rockets.clear();
			
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
	
	
	public void handleAxes() {
		
		Iterator<Axe> iterator = axes.iterator();
	    while (iterator.hasNext()) {
	    	Axe axe = iterator.next();
		    axe.damage(batman);
		    axe.move();
		    if (axe.hit) {
		    	iterator.remove();		    	
		    }
		    if(axe.x > WIDTH || axe.x < 0) {
		    	iterator.remove();
		    }
		    
	    }
	}
	
	public void handleRockets() {
		Iterator<Bullet> iterator = rockets.iterator();
	    while (iterator.hasNext()) {
	    	Bullet rocket = iterator.next();
		    rocket.damage(batman);
		    rocket.move();
		    if (rocket.hit) {
		    	iterator.remove();		    	
		    }
		    if(rocket.x > WIDTH || rocket.x < 0) {
		    	iterator.remove();
		    }
		    //if (bullet.x)
	    }
	}

}

	
		
	
	
	
	
	



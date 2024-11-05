package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
    	if (mainMenu) {
	    	display.mainMenu(g2);
    	}
    	else{
    		//draws background and scenery
			display.game(pause, dead, batman, enemies, wave, enemyDefeats, g2);
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
						checkEnemyDeath();
						spawnEnemy();
						progressWave();
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
		if (enemies.size() <  (wave * 2) - takeAway) {
			RocketGuy enemy = new RocketGuy(EnemySpawnX, EnemySpawnY, 90, 100, this, batman, 100);
			enemies.add(enemy);
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
		for (Enemy enemy:enemies) {
			enemy.movement();
			}
		}

	
	public void checkEnemyDeath() {
		for (int i=0;i<enemies.size();i++) {
			if (enemies.get(i).alive == false ) {
				enemies.remove(i);
				enemyDefeats +=1;
				
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

}

	
		
	
	
	
	
	



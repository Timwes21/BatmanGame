package Enemy;





import Game.GamePanel;
import Game.TheBatman;
	
	
	
public class KnifeGuy extends Enemy{
		
	Sprites sprites;
	
	
	public KnifeGuy(int x, int y, int WIDTH, int HEIGHT, GamePanel gp, TheBatman batman, int health){
		super(x,y,WIDTH,HEIGHT,gp,batman,health);
		sprites = new Sprites();
		standingSprite= sprites.knifeGuyStandingSprite;
		attackSprites = sprites.knifeGuyStabbingSprites;
		walkingSprites = sprites.knifeGuyWalkingSprites;
		dyingSprites = sprites.knifeGuyDyingSprites;
		currentSpritePath = standingSprite;
	}


	@Override
	public void addedMethods() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
}


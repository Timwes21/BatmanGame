package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean space;
	public boolean A;
	public boolean S;
	public boolean D;
	public boolean P;
	public boolean R;
	public boolean enter;
	public boolean Q;
	public boolean W;


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT -> right = true;
		case KeyEvent.VK_LEFT -> left = true;
		case KeyEvent.VK_UP -> up = true;
		case KeyEvent.VK_SPACE -> space = true;
		case KeyEvent.VK_A -> A = true;
		case KeyEvent.VK_S -> S = true;
		case KeyEvent.VK_D -> D = true;
		case KeyEvent.VK_P -> P = true;
		case KeyEvent.VK_R -> R = true;
		case KeyEvent.VK_ENTER -> enter = true;
		case KeyEvent.VK_Q -> Q = true;
		case KeyEvent.VK_W -> W = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT -> right = false;
		case KeyEvent.VK_LEFT -> left = false;
		case KeyEvent.VK_UP -> up = false;
		case KeyEvent.VK_SPACE -> space = false;
		case KeyEvent.VK_A -> A = false;
		case KeyEvent.VK_S -> S = false;
		case KeyEvent.VK_D -> D = false;
		case KeyEvent.VK_P -> P = false;
		case KeyEvent.VK_R -> R = false;
		case KeyEvent.VK_ENTER -> enter = false;
		case KeyEvent.VK_Q -> Q = false;
		case KeyEvent.VK_W -> W = false;
		}
		
		
		
	}

}


/*
 * Due to the MVC Pattern every logic got its own Controller
 * This is the Controller for the User which got Keys as input.
 * The User influences the position of the Player with the arrow keys
 * 
 * This Class directly interacts with Rules. It gets the users' input and sends it to
 * rules which decides whether a move is legal or illegal.
 * 
 */


package ludigame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerSpieler implements KeyListener{
	
	private Rules rules;
	public ControllerSpieler(Rules rules) {
	
		this.rules=rules;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		/* ArrowKey right (KeyCode 39) */
		if (e.getKeyCode()==39)
		{
		   rules.pMoveRE(); 
		}
		/* ArrowKey left (KeyCode 37) */
		else if (e.getKeyCode()==37)
		{
			rules.pMoveLE();   
		}
		/* ArrowKey up (KeyCode 38) */
		if (e.getKeyCode()==38)
		{  
			rules.pMoveUP(); 
		}
		/* ArrowKey down (KeyCode 40) */
		else if (e.getKeyCode()==40)
		{
			rules.pMoveDO();  
		}

	}
	
	
	/* following stuff is integrated because it is a must if you implement the KeyListener */
	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
}




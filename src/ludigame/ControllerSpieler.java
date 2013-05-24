package ludigame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerSpieler implements KeyListener{
	
	private Rules rules;
	private boolean move;
	public ControllerSpieler(Rules rules) {
	
		this.rules=rules;
		this.move=false;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==39)
		{
		   rules.pMoveRE(); 
		}
		// left arrow == 37
		else if (e.getKeyCode()==37)
		{
			rules.pMoveLE();   
		}
		
		if (e.getKeyCode()==38)
		{  
			rules.pMoveUP(); 
		}
		// left arrow == 37
		else if (e.getKeyCode()==40)
		{
			rules.pMoveDO();  
		}

	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// right arrow == 39

	
	}
}




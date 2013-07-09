/*
 * Due to the MVC Pattern every logic got its own Controller
 * This is the Controller for the User which got Keys as input.
 * The User influences the position of the Player with the arrow keys
 * 
 * This Class directly interacts with Rules. It gets the users' input and sends it to
 * rules which decides whether a move is legal or illegal.
 * 
 */

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Sounds.Sound;

import models.Enemy;
import models.Spieler;

public class ControllerSpieler2 implements  Runnable {

	private Enemy en;
	private Thread controllerThread;
	private Boolean running = false;
	private int sX = 0;
	private int sY = 0;
	private int counter = 0;

	public ControllerSpieler2(Enemy en) {
		this.en = en;
		controllerThread = new Thread(this);
		controllerThread.start();
	}

	public void start() {
		controllerThread.start();
	}

	public void movethis(int x, int y) {
		en.setSpeedX(x);
		en.setSpeedY(y);
		if (!running) {
			running = true;
		}
	}

	public void keyIsPressed(String key) {
      
		String k= key;
		if(k.equals("feuerball")==false && k.equals("eisball")==false && k.equals("laser")==false){
		String xOry;
		
		xOry=k.replaceFirst("[.][^.]+$", "");
		
		String koord= k.replace(xOry+".","");
		
		int pos= Integer.valueOf(koord);
		
		if(xOry.equals("x"))
		{
			if(pos<en.getPosX())
			{
				en.setDirection(1);
			} else if(pos>en.getPosX())
			{
				en.setDirection(3);
			}
			en.setPos(pos, en.getPosY());
		}
		if(xOry.equals("y"))
		{
			if(pos<en.getPosY())
			{
				en.setDirection(2);
			} else if(pos>en.getPosY())
			{
				en.setDirection(0);
			}
			en.setPos(en.getPosX(),pos);
		}
	} else if(k.equals("feuerball")){
		
		
			
			
			en.setTypedforSkill(1);
			en.shot();
			
			

		
		
	} else if(k.equals("eisball"))
	{
		en.setTypedforSkill(3);
		en.shot();
	} else if(k.equals("laser"))
	{
		System.out.println("lasererzeugt");
		en.setTypedforSkill(2);
		en.shot();
	}
		
		running = true;
	
	}





	@Override
	public void run() {
		while (en.getLifestatus()==true) {
			//System.out.println("thread im ControllerSpieler2");
			en.getDamage();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((en.getSpeedX() > 0) || (en.getSpeedY() > 0)
					|| (en.getSpeedX() < 0) || (en.getSpeedY() < 0)) {
				en.updateAttributesAndItems();
				en.changePos(en.getPosX() + en.getSpeedX(),
						en.getPosY() + en.getSpeedY());
			}
			counter++;
			if (counter == (1000 / en.getSpeed())) {
				if (en.getManaPoints() < 100)
					en.setManaPoints(en.getManaPoints() + 1);
				counter = 0;
			}

		}

	}

}

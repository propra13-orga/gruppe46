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

		running = true;
		if (key.equals("p39"))
			sX = 1;

		if (key.equals("p37"))
			sX = -1;
		
		if (key.equals("p38"))
			sY = -1;
		
		if (key.equals("p40"))
			sY = 1;

		if (key.equals("p32")) {
			
			if (en.getTyped() == false) {
				Sound ball=new Sound();
				ball.play("sounds/ball.wav");
				en.setTypedforSkill(1);
				en.shot();
				en.setTyped(true);

			}
		}
		
        if (key.equals("p87")) {
			
			if (en.getTyped() == false) {
				Sound ball=new Sound();
				ball.play("sounds/ball.wav");
				en.setTypedforSkill(3);
				en.shot();
				en.setTyped(true);

			}
		}
		if (key.equals("p81"))// 81=q
		{
			en.setTypedforSkill(2);
			en.shot();
		}
		movethis(sX, sY);
		
		if ((key.equals("r39")) || (key.equals("r37"))) {
			sX = 0;
			en.setSpeedX(0);
		}
		if ((key.equals("r38")) || (key.equals("r40"))) {
			sY = 0;
			en.setSpeedY(0);
		}
		if ((key.equals("r32")) || (key.equals("r87"))) {
			//e.setTyped(false);
		}
	}





	@Override
	public void run() {
		while (en.getLifestatus()==true) {
			//System.out.println("thread im ControllerSpieler2");
			en.getDamage();
			try {
				Thread.sleep(en.getSpeed());
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

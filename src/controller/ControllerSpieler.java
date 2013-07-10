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

import models.Spieler;

public class ControllerSpieler implements KeyListener, Runnable {

	private Spieler sp;
	private Thread controllerThread;
	private Boolean running = false;
	private int sX = 0;
	private int sY = 0;
	private int counter = 0;

	public ControllerSpieler(Spieler sp) {
		this.sp = sp;
		controllerThread = new Thread(this);
	}

	public void start() {
		controllerThread.start();
	}

	public void movethis(int x, int y) {
		sp.setSpeedX(x);
		sp.setSpeedY(y);
		if (!running) {
			running = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		running = true;
		if (e.getKeyCode() == 39) {
			sX = 1;
		}
		if (e.getKeyCode() == 37) {
			sX = -1;
		}
		if (e.getKeyCode() == 38) {
			sY = -1;
		}
		if (e.getKeyCode() == 40) {
			sY = 1;
		}
		if (e.getKeyCode() == 32) {
			
			if (sp.getTyped() == false && sp.getManaPoints()>=sp.getManaCost()) {
				sp.setBallerzeugt(true);
				Sound ball=new Sound();
				ball.play("sounds/ball.wav");
				sp.setTypedforSkill(1);
				sp.shot();
				sp.setTyped(true);

			}
		}
		
        if (e.getKeyCode() == 87) {
			
			if (sp.getTyped() == false && sp.getManaPoints()>=sp.getManaCost()) {
				sp.setEisballerzeugt(true);
				Sound ball=new Sound();
				ball.play("sounds/ball.wav");
				sp.setTypedforSkill(3);
				sp.shot();
				sp.setTyped(true);

			}
		}
		if (e.getKeyCode() == 81)// 81=q
		{
			
			sp.setLasererzeugt(true);
			sp.setTypedforSkill(2);
			sp.shot();
			

		}
		movethis(sX, sY);
	}

	/*
	 * following stuff is integrated because it is a must if you implement the
	 * KeyListener
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == 39) || (e.getKeyCode() == 37)) {
			sX = 0;
			sp.setSpeedX(0);
		}
		if ((e.getKeyCode() == 40) || (e.getKeyCode() == 38)) {
			sY = 0;
			sp.setSpeedY(0);
		}
		if (e.getKeyCode() == 32 || e.getKeyCode()==87) {
			sp.setTyped(false);
			sp.setBallerzeugt(false);
			sp.setEisballerzeugt(false); 
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void run() {
		while (sp.getLifestatus()==true) {
			sp.getDamage();
			try {
				Thread.sleep(sp.getSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((sp.getSpeedX() > 0) || (sp.getSpeedY() > 0)
					|| (sp.getSpeedX() < 0) || (sp.getSpeedY() < 0)) {
				sp.updateAttributesAndItems();
				sp.changePos(sp.getPosX() + sp.getSpeedX(),
						sp.getPosY() + sp.getSpeedY());
			}
			counter++;
			if (counter == (1000 / sp.getSpeed())) {
				if (sp.getManaPoints() < 100)
					sp.setManaPoints(sp.getManaPoints() + 1);
				counter = 0;
			}

		}

	}

}

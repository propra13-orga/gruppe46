package models;

import java.util.Observable;

public class RangeSkills extends Observable implements Runnable {
	private int posX;
	private int posY;
	private int StartPosX;
	private int StartPosY;
	private int dmg;
	PlayingField pf;
	private int speedThread;
	private int speedX;
	private int speedY;
	private int skillSize;
	private boolean destroyed;
	private int range;
	private String direction;
	private Thread t;
	private Player owner;
	private String skillTyp="keins";

	public RangeSkills(int x, int y, PlayingField pf, Player owner) {
		setPos(x, y);
		this.StartPosX = x;
		this.StartPosY = y;
		this.owner = owner;
		this.setDestroyed(false);
		this.pf = pf;
		t = new Thread(this);
		this.t.start();
	}

	public void setSkillSize(int s) {
		this.skillSize = s;

	}

	public Player getOwner() {
		return this.owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getSkillSize() {
		return this.skillSize;
	}

	public void setDirection(String d) {
		this.direction = d;
	}

	public String getDirection() {
		return direction;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;

	}

	public void setPos(int x, int y) {
		setChanged();
		this.posX = x;
		this.posY = y;
		notifyObservers();

	}

	public void setRange(int r) {
		this.range = r;

	}

	public int getRange() {
		return this.range;
	}

	public void run() {

		while (this.getDestroyed() == false) {

			this.kollisionsAbfrage();
			try {
				Thread.sleep(speedThread);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getDmg() {
		return this.dmg;
	}

	public int getSpeedThread() {
		return this.speedThread;

	}

	public void setSpeedThread(int s) {
		this.speedThread = s;

	}

	public int getSpeedX() {
		return this.speedX;

	}

	public int getSpeedY() {
		return this.speedY;

	}

	public void setSpeedX(int x) {
		this.speedX = this.speedX + x;
	}

	public void setSpeedY(int y) {
		this.speedY = this.speedY + y;
	}

	public boolean getDestroyed() {
		return this.destroyed;
	}

	public void setDestroyed(boolean b) {
		this.destroyed = b;
	}

	/* Kollisionsabfragen */

	public void kollisionsAbfrage() {

		int x = (this.getPosX() + this.getSpeedX()) / 60;
		int y = (this.getPosY() + this.getSpeedY()) / 60;

		if (Math.abs(this.getPosX() - this.StartPosX) > this.getRange()
				|| Math.abs(this.getPosY() - this.StartPosY) > this.getRange()) {
			this.setDestroyed(true);
		}
		if (pf.getFieldarray()[x][y].getClass().getName().equals("models.Wall")
				|| this.getPosX() < 1 || this.getPosX() > pf.getWidth() - 20
				|| this.getPosY() < 1 || this.getPosY() > pf.getHeight() - 20) {

			
			this.setDestroyed(true);

		} else {
			this.setPos(this.getPosX() + this.getSpeedX(), this.getPosY()
					+ this.getSpeedY());

		}

	}

	public String getSkillTyp() {
		return skillTyp;
	}

	public void setSkillTyp(String skillTyp) {
		this.skillTyp = skillTyp;
	}

}

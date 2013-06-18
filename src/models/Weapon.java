package models;

public class Weapon extends Item {

	private int dmg;
	private boolean destroyed;
	private int range;
	private String direction;
	private int speedX;
	private int speedY;

	public Weapon(Player owner) {
		super(owner);
		this.setPos(owner.getPosX(), owner.getPosY());
	}

	public void setDestroyed(boolean d) {
		this.destroyed = d;
	}

	public boolean getDestroyed() {
		return this.destroyed;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getRange() {
		return this.range;

	}

	public int getDmg() {
		return this.dmg;
	}

	public void setDmg(int d) {
		this.dmg = d;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String s) {
		this.direction = s;
	}

	public int getSpeedX() {
		return this.speedX;
	}

	public void setSpeedX(int x) {
		this.speedX = x;
	}

	public int getSpeedY() {
		return this.speedY;
	}

	public void setSpeedY(int y) {
		this.speedY = y;
	}

}

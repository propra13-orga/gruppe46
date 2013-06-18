package models;

public class Item {

	int buyCosts;
	int sellCosts;
	int posX;
	int posY;
	Player owner;

	public Item(Player owner) {
		this.owner = owner;
	}
	
	public void setAttributes() {

	}

	public void setOwner(Player owner) {
		this.owner = owner;
		this.setAttributes();
	}

	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY()

	{
		return posY;
	}

}

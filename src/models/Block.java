/*
 *This is the main class for all blocks (we call them items) on the map
 *Every block is an item and will be extended by this class.
 */
package models;

import java.util.Observable;

public class Block extends Observable {

	private String kind;
	private int posX;
	private int posY;

	/* Every Block is initialized with a position and a type */
	public Block(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		kind="";

	}

	/* getters */
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public String getKind()
	{
		return this.kind;
	}

	/* setters */
	public void setKind(String kind)
	{
		this.kind=kind;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}

	public void setPosY(int y) {
		this.posY = y;
	}

}

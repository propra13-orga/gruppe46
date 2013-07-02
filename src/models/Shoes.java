package models;

import Sounds.Sound;

public class Shoes extends Item{

	public Shoes(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		owner.setSpeed(-5);
		Sound shoesound=new Sound();
		shoesound.play("sounds/shoes.wav");
		
	}
}

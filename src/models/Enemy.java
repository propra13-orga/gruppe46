package models;

import Sounds.Sound;

public class Enemy extends Player {


	
	public Enemy(PlayingField pf, MoveableObjects movObj) {
		super(pf, movObj);
		this.setHealthpoints(100);
		this.setLifes(1);
		this.setManaPoints(100);
	}
	
	
	
	public void playDeadsound()
	{
		//Sound dead=new Sound();
		//dead.play("ghost.wav");
	}

}

/*
 * Spieler is just a player. For more info have a look
 * at the player class.
 */
package models;

import Sounds.Sound;

public class Spieler extends Player {
	private PlayingField pf;

	MoveableObjects movObj;
	
	public Spieler(PlayingField pf, MoveableObjects movObj) {

		super(pf, movObj);
		this.movObj=movObj;
		this.pf = pf;
		this.setPos(pf.getStartX() * 60, pf.getStartY() * 60);
		this.setLifes(1);
		this.setMoney(10);
		this.setHealthpoints(100);
		this.setSpeedX(0);
		this.setSpeedY(0);
		this.setScore(0);

	}

	public void reactOnFinish(int TargetLayer, int Tlvl) {
		for(int i=0; i<pf.getItemList().size(); i++)
			pf.removeItem(i);
		pf.LoadLayer(TargetLayer, Tlvl);
		this.setPos(pf.getStartX() * 60 , pf.getStartY() * 60 );
		
		
		movObj.getRskills().clear();

	}

	public void reactShop() {
		pf.LoadLayer(-1,1);
		this.setPos(pf.getStartX() * 60 , pf.getStartY() * 60 );
		movObj.getRskills().clear();
	}
	
	public void killSpieler()
	{
		Sound no=new Sound();
		no.play("sounds/no.wav");
		this.setHealthpoints(100);
		this.setManaPoints(100);
		pf.LoadLayer(pf.getLayer(), pf.getLevel());
		this.setPos(pf.getStartX()*60, pf.getStartY()*60);
	}
}

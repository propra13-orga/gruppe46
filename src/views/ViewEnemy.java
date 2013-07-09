/*
 * The View of the enemy is just a View of a Player
 * For more info have a look at the ViewPlayer class
 */
package views;

import java.awt.Graphics;

import models.Enemy;
import models.Player;

public class ViewEnemy extends ViewPlayer {

	public ViewEnemy(Player e, String ImagePath) {
		super(e, ImagePath);
	}


	@Override
	public void draw(Graphics g) {

		if (this.getPlayer().getLifes() < 1) {
			this.setImage("images/enemyDead.png");
		} else {
			if (this.getPlayer().getDirection()==3 && this.getSkin() == 1) {
				this.setImage("images/enemyR.png");
			} else if (this.getPlayer().getDirection()==3 && this.getSkin() == 2) {
				this.setImage("images/enemymovrechts.png");
			} else if (this.getPlayer().getDirection() ==1 && this.getSkin() == 1) {

				this.setImage("images/enemyL.png");

			} else if (this.getPlayer().getDirection()==1 && this.getSkin() == 2) {

				this.setImage("images/enemymovlinks.png");
			} else if (this.getPlayer().getDirection() ==2  && this.getSkin() == 1) {
				this.setImage("images/enemyB2.png");
			} else if (this.getPlayer().getDirection()==2 && this.getSkin() == 2) {
				this.setImage("images/enemyB3.png");
			} else if (this.getPlayer().getSpeedY() == 0
					&& (this.getPlayer().getDirection() == 2)) {
				this.setImage("images/enemyB.png");
			}

			else if (this.getPlayer().getDirection()==0 && this.getSkin() == 1) {

				this.setImage("images/enemyV2.png");

			} else if (this.getPlayer().getDirection()==0 && this.getSkin() == 2) {

				this.setImage("images/enemyV3.png");

			} else if (this.getPlayer().getSpeedY() == 0
					&& (this.getPlayer().getDirection() == 0)) {
				this.setImage("images/enemyV.png");

			}
		}
		g.drawImage(this.getImage(), this.getPlayer().getPosX(), this
				.getPlayer().getPosY(), null);
		drawstatus(g);
	}

}

/* 
 * just another ViewPlayer.  For more info have
 * a look at the ViewPlayer class.
 * 
 */

package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Player;

public class ViewSpieler extends ViewPlayer {

	public ViewSpieler(models.Spieler p, String ImagePath) {
		super(p, ImagePath);
	}

	@Override
	public void draw(Graphics g) {
		this.setSkin();
		if (this.getPlayer().getSpeedX() > 0 && this.getSkin() == 1) {
			this.setImage("images/playerR.png");
		} else if (this.getPlayer().getSpeedX() > 0 && this.getSkin() == 2) {
			this.setImage("images/playermovrechts.png");
		} else if (this.getPlayer().getSpeedX() < 0 && this.getSkin() == 1) {

			this.setImage("images/playerL.png");

		} else if (this.getPlayer().getSpeedX() < 0 && this.getSkin() == 2) {

			this.setImage("images/playermovlinks.png");
		} else if (this.getPlayer().getSpeedY() < 0 && this.getSkin() == 1) {
			this.setImage("images/playerB2.png");
		} else if (this.getPlayer().getSpeedY() < 0 && this.getSkin() == 2) {
			this.setImage("images/playerB3.png");
		} else if (this.getPlayer().getSpeedY() == 0
				&& (this.getPlayer().getDirection() == 2)) {
			this.setImage("images/playerB.png");
		}

		else if (this.getPlayer().getSpeedY() > 0 && this.getSkin() == 1) {

			this.setImage("images/playerV2.png");

		} else if (this.getPlayer().getSpeedY() > 0 && this.getSkin() == 2) {

			this.setImage("images/playerV3.png");

		} else if (this.getPlayer().getSpeedY() == 0
				&& (this.getPlayer().getDirection() == 0)) {
			this.setImage("images/playerV.png");

		}
		
		g.drawImage(this.getImage(), this.getPlayer().getPosX(), this
				.getPlayer().getPosY(), null);
		
	}
	
}

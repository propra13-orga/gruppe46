package views;

import java.awt.Graphics;

import models.Player;

public class ViewKEnemy extends ViewPlayer {

	public ViewKEnemy(Player p, String ImagePath) {
		super(p, ImagePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		this.setImage("images/Ludi2.png");

		if (this.getPlayer().getLifes() < 1) {
			this.setImage("images/Ludi2dead.png");
		}
		g.drawImage(this.getImage(), this.getPlayer().getPosX(), this
				.getPlayer().getPosY(), null);
		drawstatus(g);

	}

}

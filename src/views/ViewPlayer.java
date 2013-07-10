/* 
 * This is the main class for the the view of all players
 * it draws every single player (till now enemies and spieler)
 * it extends our abstract interface Drawable
 */
package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import models.Player;

public class ViewPlayer implements Drawable {

	private int skin = 1;
	private Player p;
	private BufferedImage PlayerImg;
	private BufferedImage LifeImg;
	private int counter = 0;

	/* initialized by a Player */
	public ViewPlayer(Player p, String ImagePath) {
		this.p = p;
		setImage(ImagePath);

	}

	public int getSkin() {
		return this.skin;

	}

	public void setSkin() {
		counter++;
		if (counter == 20)
			skin = 1;
		if (counter == 40) {
			skin = 2;
			counter = 0;
		}
	}

	public Player getPlayer() {
		return this.p;

	}
	
	public void deletePlayer()
	{
		this.p=null;
	}

	public BufferedImage getImage() {

		return PlayerImg;

	}

	public Graphics drawstatus(Graphics g) {
		setSkin();
		if (p.getLifestatus())
		{
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.setColor(Color.red);
		g.drawString(String.valueOf(p.getHealthpoints()), p.getPosX(),
				p.getPosY() - 3);
		if(p.getEnTyp()=="fire")
		{
		g.setColor(Color.orange);
		g.drawString(String.valueOf(p.getFireArmor()), p.getPosX()+20,
				p.getPosY() - 3);
		}else if(p.getEnTyp()=="ice")
		{
			g.setColor(Color.blue);
			g.drawString(String.valueOf(p.getIceArmor()), p.getPosX()+20,
					p.getPosY() - 3);
		}else if(p.getEnTyp()=="nix")
		{
			g.setColor(Color.black);
			g.drawString(String.valueOf(p.getArmor()), p.getPosX()+20,
					p.getPosY() - 3);
		}
		g.drawImage(LifeImg, p.getPosX() - 5, p.getPosY(), null);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 9));
		/*g.drawString(String.valueOf(p.getLifes() - 1), p.getPosX() - 2,
				p.getPosY() + 8);
		g.setColor(Color.blue);
		g.drawString(String.valueOf(p.getManaPoints()), p.getPosX() + 30,
				p.getPosY() - 3);*/
		}
		return g;
	}

	/* Set an image for the Player */
	public void setImage(String ImagePath) {
		/* necessary because the image file might not exist */
		try {
			this.PlayerImg = ImageIO.read(new File(ImagePath));
			
		} catch (IOException e) {
			System.out
					.println("Error in some kind of ViewPlayer (check also extended classes(such as Spieler or Enemy), "
							+ "prolly probs with the playerimage: "
							+ e.getMessage());
		}

	}

	@Override
	/*
	 * possible due to our abstract interface Drawable
	 * 
	 * @see ludigame.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		g.drawImage(PlayerImg, p.getPosX(), p.getPosY(), null);
		setSkin();

	}

}

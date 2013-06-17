/* 
 * View of the playing Field. 
 * This class draws the content of the array of PlayingField
 * and returns it in form of an array of ViewItems.
 * 
 */

package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import models.PlayingField;

public class ViewPlayingField implements Drawable, Observer{

	private PlayingField pf;
	private ViewBlock[][] viewItemArr;
	private ArrayList<ViewItem> itvl = new ArrayList<ViewItem>();

	public ViewPlayingField(PlayingField pf) {
		this.pf = pf;
		pfGUI();
		UpdateItems();
	}

	/* This method loads the images of the items to the ViewItemArr (ay). */
	public void pfGUI() {
		viewItemArr = new ViewBlock[pf.getWidth() / 60][pf.getHeight() / 60];
		for (int i = 0; i < (this.pf.getWidth() / 60); i++) {
			for (int j = 0; j < (this.pf.getHeight() / 60); j++) {
				
				switch (pf.getFieldarray()[i][j].getClass().getName()) {
				case "models.Wall":
					viewItemArr[i][j] = new ViewWall(pf.getFieldarray()[i][j],
							"images/Wand1.png");
					break;
				case "models.Finish":
					viewItemArr[i][j] = new ViewFinish(
							pf.getFieldarray()[i][j], "images/finish.png");
					break;
				case "models.Floor":
					viewItemArr[i][j] = new ViewFloor(pf.getFieldarray()[i][j],
							"images/boden.png");
					break;
				case "models.Trap":
					viewItemArr[i][j] = new ViewTrap(pf.getFieldarray()[i][j],
							"images/enemy.png");
					break;
				case "models.Shop":
					viewItemArr[i][j] = new ViewShop(pf.getFieldarray()[i][j],
							"images/shop.png");
					break;
				}

			}

		}

	}

	// muss ersetzt werden performance...
	public void UpdateItems() {
		itvl.clear();
try{
		for (int i = 0; i < pf.getItemList().size(); i++) {

			ViewItem vitem = new ViewItem(pf.getItemList().get(i));
			if (pf.getItemList().get(i).getClass().getName()
					.equals("models.Shoes")) {
				vitem.setImage("images/bonus.png");
				itvl.add(vitem);
			}
			if (pf.getItemList().get(i).getClass().getName()
					.equals("models.Healthpack")) {
				vitem.setImage("images/hp.png");
				itvl.add(vitem);
			}
			
			if (pf.getItemList().get(i).getClass().getName()
					.equals("models.Key")) {
				vitem.setImage("images/finish.png");
				itvl.add(vitem);
			}
			if (pf.getItemList().get(i).getClass().getName()
					.equals("models.Cash")) {
				vitem.setImage("images/dollar.png");
				itvl.add(vitem);
			}
			if (pf.getItemList().get(i).getClass().getName().equals("models.DmgBall"))
			{
				vitem.setImage("images/balldmg.png");
				itvl.add(vitem);
			}
			if (pf.getItemList().get(i).getClass().getName().equals("models.RangeBall"))
			{
				vitem.setImage("images/rangeball.png");
				itvl.add(vitem);
			}
			if(pf.getItemList().get(i).getClass().getName().equals("models.ManaCosts"))
			{
				vitem.setImage("images/manacosts.png");
				itvl.add(vitem);
			}
			if(pf.getItemList().get(i).getClass().getName().equals("models.DmgLaser"))
			{
				vitem.setImage("images/laserdmg.png");
				itvl.add(vitem);
			}
			if(pf.getItemList().get(i).getClass().getName().equals("models.RangeLaser"))
			{
				vitem.setImage("images/laserrange.png");
				itvl.add(vitem);
			}
			if(pf.getItemList().get(i).getClass().getName().equals("models.Npc"))
			{
				vitem.setImage("images/npc.png");
				itvl.add(vitem);
			}
			
			

		}
}catch(Exception e)
{}
	}

	/*
	 * possible due to our abstract interface drawable this method draws the
	 * playingfield to a graphic g.
	 * 
	 * @see ludigame.Drawable#draw(java.awt.Graphics)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see views.Drawable#draw(java.awt.Graphics)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see views.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		UpdateItems();
	
		for (int i = 0; i < this.pf.getWidth() / 60; i++) {
			for (int j = 0; j < this.pf.getHeight() / 60; j++) {
				try {
					viewItemArr[i][j].draw(g);
				} catch (Exception e) {

				}

			}
		}

		for (int j = 0; j < itvl.size(); j++) {
			itvl.get(j).draw(g);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		pfGUI();
		
	}

}

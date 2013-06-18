/*
 *  This is the View of an Item
 *  it is not a main class which will be extended
 *  because every item has the same property in the view.
 */
package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import models.Block;

public class ViewBlock implements Drawable {

	private Block item;
	private BufferedImage itemImg;
	private BufferedImage bgImg;

	public ViewBlock(Block item, String imagePath) {
		this.item = item;
		setImage(imagePath);
	}

	public Block getItem() {
		return this.item;

	}

	public BufferedImage getItemImg() {
		return this.itemImg;

	}

	public BufferedImage getBgImg() {
		return this.bgImg;

	}

	public void draw(Graphics g) {

		g.drawImage(bgImg, item.getPosX(), item.getPosY(), null);
		g.drawImage(itemImg, item.getPosX(), item.getPosY(), null);// To change
																	// body of
																	// generated
																	// methods,
																	// choose
																	// Tools |
																	// Templates.
	}

	public void setImage(String imagePath) {

		try {
			this.itemImg = ImageIO.read(new File(imagePath));
			this.bgImg = ImageIO.read(new File("images/boden.png"));
		} catch (IOException e) {
			System.out.println("Error in some sort of an Item View: "
					+ "Item Image could not be loaded!" + e.getMessage());
		}

	}
	// To change body of generated methods, choose Tools | Templates.
}

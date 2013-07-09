package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Item;

public class ViewItem implements Drawable {

	private BufferedImage itemImg;
	private BufferedImage bgImg;
	private Item item;

	public ViewItem(Item item) {
		this.item = item;
	}

	public void setImage(String imagePath) {

		try {
			this.itemImg = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("Error in some sort of an Item View: "
					+ "Item Image could not be loaded!" + e.getMessage());
		}

	}

	public Item getItem() {
		return item;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(itemImg, item.getPosX(), item.getPosY(), null);

	}
}
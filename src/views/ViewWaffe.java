package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Weapon;

public class ViewWaffe implements Drawable {

	private BufferedImage image;
	private Weapon waffe;

	public ViewWaffe(Weapon waffe) {
		this.waffe = waffe;

	}

	public Weapon getWaffe() {
		return this.waffe;
	}

	private void setImages(String imagePath) {
		try {
			this.image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("Error in some sort of an Item View: "
					+ "Item Image could not be loaded!" + e.getMessage());
		}
	}

	public void draw(Graphics g) {

		// g.drawImage(image, waffe.getPosX(),waffe.getPosY(),null);

	}

}

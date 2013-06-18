package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.MoveableObjects;

public class ViewMoveableObjects implements Drawable {

	private BufferedImage magicball;
	private MoveableObjects movObj;
	private BufferedImage laser;

	public ViewMoveableObjects(MoveableObjects movObj) {
		this.movObj = movObj;
		setImages();
	}

	private void setImages() {
		try {
			this.magicball = ImageIO.read(new File("images/ball.png"));
			this.laser = ImageIO.read(new File("images/laser.png"));
		} catch (IOException e) {
			System.out.println("Error in some sort of an Item View: "
					+ "Item Image could not be loaded!" + e.getMessage());
		}
	}

	@Override
	public void draw(Graphics g) {
		//setImages();

		for (int i = 0; i < movObj.getRskills().size(); i++) {
			if (movObj.getRskills().get(i).getClass().getName()
					.equals("models.MagicBall")) {
				if (movObj.getRskills().get(i).getDestroyed() == false) {
					g.drawImage(magicball,
							movObj.getRskills().get(i).getPosX(), movObj
									.getRskills().get(i).getPosY(), null);
				}
			}

			if (movObj.getRskills().get(i).getClass().getName()
					.equals("models.LaserEyes")) {
				if (movObj.getRskills().get(i).getDestroyed() == false) {
					g.drawImage(laser, movObj.getRskills().get(i).getPosX(),
							movObj.getRskills().get(i).getPosY(), null);
				}
			}
		}
	}

}

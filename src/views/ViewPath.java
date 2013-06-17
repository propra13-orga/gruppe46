package views;

import AI.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.*;

public class ViewPath implements Drawable {

	private AStar a;
	private BufferedImage pathImg;

	public ViewPath(AStar a) {
		try {
			pathImg = ImageIO.read(new File("images/path.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.a = a;
	}

	@Override
	public void draw(Graphics g) {
		try {
			for (int i = 0; i < a.getPath().size(); i++) {
				g.drawImage(pathImg, a.getPath().get(i).getPosX(), a.getPath()
						.get(i).getPosY(), null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Path in die leere Gezeichnet- nicht schlimm");
		}
	}

}
package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Block;

public class ViewWall extends ViewBlock {
	//private BufferedImage itemImg;
	//private Block item;
	public ViewWall(Block item, String imagePath) {
		super(item, imagePath);
	//	this.item=item;
		//resetImage();
		// TODO Auto-generated constructor stub
	}



public void resetImage() {

	this.setImage("images/Wand1.png");
	/*
		switch(this.item.getKind())
		{
		case "hl":
		this.setImage("images/whledge.png");
		break;
		case "hr":
		this.setImage("images/whredge.png");
		System.out.println("hier");
		break;
		case "hlhr":
			this.setImage("images/whmiddle.png");
			System.out.println("hier");
			break;
	
		}*/

}
}
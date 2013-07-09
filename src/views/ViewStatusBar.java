package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Spieler;

public class ViewStatusBar implements Drawable{
	
	private Spieler sp;
	private int width, height;
	private BufferedImage heart, mana,money, hp;
	
	
	public ViewStatusBar(Spieler sp, int width)
	{
		this.sp=sp;
		this.width=width;
		this.height=30;
		try {
			this.heart= ImageIO.read(new File("images/herz.png"));
			this.mana= ImageIO.read(new File("images/mana.png"));
			this.money= ImageIO.read(new File("images/money.png"));
			this.hp= ImageIO.read(new File("images/hp2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getHeight()
	{
		return this.height;
	}
	@Override
	public void draw(Graphics g) {
		
	
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.drawImage(hp,400,5,null);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.red);
		g.drawString(String.valueOf(sp.getHealthpoints()), 423,
				22);
		
		g.drawImage(mana,465,5,null);
		g.setColor(Color.blue);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(String.valueOf(sp.getManaPoints()), 487,
				22);
		
		g.drawImage(money,530,5,null);
		g.setColor(Color.yellow);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(String.valueOf(sp.getMoney()), 553,
				22);
		for(int i=0; i<sp.getLifes(); i++)
			g.drawImage(heart,(20*i)+5,5,null);
		
		/*g.drawString(String.valueOf(sp.getLifes() - 1), p.getPosX() - 2,
				p.getPosY() + 8);
		g.setColor(Color.blue);
		g.drawString(String.valueOf(p.getManaPoints()), p.getPosX() + 30,
				p.getPosY() - 3);*/
		}
}



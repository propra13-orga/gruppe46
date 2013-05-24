package ludigame;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ViewPlayer implements Drawable{
	
	
	private Player p;
    private BufferedImage PlayerImg;

    public ViewPlayer(Player p, String ImagePath)
    {
    	this.p=p;
    	setImage(ImagePath);
    	
    }

    public void setImage(String ImagePath)
    {
     	try {
			this.PlayerImg=ImageIO.read(new File(ImagePath));
		} catch (IOException e) {
				System.out.println("Error in some kind of ViewPlayer (check also extended Classes), " +
						"prolly probs with the Playerimage: "+e.getMessage());
		}
    }
     @Override
    public void draw(Graphics g) {
                System.out.println("grafik wird gezeichnet");
		g.drawImage(PlayerImg, p.getPosX(), p.getPosY(),null);
               
	}


}

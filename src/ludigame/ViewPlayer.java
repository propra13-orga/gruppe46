/* 
 * This is the main class for the the view of all players
 * it draws every single player (till now enemies and spieler)
 * it extends our abstract interface Drawable
 */
package ludigame;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ViewPlayer implements Drawable{
	
	
	private Player p;
    private BufferedImage PlayerImg;

    /* initialized by a Player */
    public ViewPlayer(Player p, String ImagePath)
    {
    	this.p=p;
    	setImage(ImagePath);
    	
    }
    
    
    /*Set an image for the Player */
    public void setImage(String ImagePath)
    {
    	/* necessary because the image file might not exist */
     	try {
			this.PlayerImg=ImageIO.read(new File(ImagePath));
		} catch (IOException e) {
				System.out.println("Error in some kind of ViewPlayer (check also extended Classes), " +
						"prolly probs with the Playerimage: "+e.getMessage());
		}
    }
     @Override
    /* possible due to our abstract interface Drawable
     * @see ludigame.Drawable#draw(java.awt.Graphics)
     */
    public void draw(Graphics g) {
                System.out.println("grafik wird gezeichnet");
		g.drawImage(PlayerImg, p.getPosX(), p.getPosY(),null);
               
	}


}

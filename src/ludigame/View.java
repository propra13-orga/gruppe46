/*
 *  Main View which is extended from JFrame and is updated
 *  by its observables (implements Observer).
 *  This class draws the game and redraws it on updates.
 */
package ludigame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class View extends JFrame implements Observer {

	private ViewPlayer vp;
	private ViewPlayingField vpf;
	private int height,width;
  

    
	/* Initialize with the dimension of the map and all other views */
	public View(int height, int width, ViewPlayer vp, ViewPlayingField vpf)
	{
	 super("LudiGame");	
     this.height=height;
     this.width=width;
	 this.vpf=vpf;
          window();
          this.vp=vp;

	}
	/*the window is created here with its properties*/
        public void window()
        {            
               this.setSize(this.width+this.getInsets().left, 
            		   this.height+this.getInsets().top);
               /* Exit the whole program if you click on the X on the upper right */
               this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               this.setVisible(true);
               this.setLocationRelativeTo (null);
        }
      
    	/* Draw an image before drawing it on the Window
    	 * to prevent flickering (also highers fps)
    	 * this method returns an image which will be later drawn on this
    	 * view 
    	 */
        public Image draw()
        {  
        	   BufferedImage bimg=new BufferedImage(this.height, 
        			   this.width,BufferedImage.TYPE_INT_RGB);
        	   vpf.draw(bimg.getGraphics());
        	   vp.draw(bimg.getGraphics());
        	   return bimg;
        }
        
       /* this method draws the image which is drawn by draw() to this
        * view
        * @see java.awt.Window#paint(java.awt.Graphics)
        */
       public void paint(Graphics g) {
    	   	g.drawImage(draw(),this.getInsets().left,
    	   			this.getInsets().top,null);
    	
         }
       /*
        * redraws the view on an update of its observables.  
        * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
        */
		@Override
		public void update(Observable arg0, Object arg1) {
		  repaint();
		}

        
}

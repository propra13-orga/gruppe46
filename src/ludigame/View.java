/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Gerhard
 */
public class View extends JFrame implements Observer {

	private ViewPlayer vp;
	private ViewPlayingField vpf;
	private int height,width;
  

    
	
	public View(int height, int width, ViewPlayer vp, ViewPlayingField vpf)
	{
	 super("LudiGame");	
     this.height=height;
     this.width=width;
	 this.vpf=vpf;
          Fenster();
          this.vp=vp;

	}
        public void Fenster()
        {            
               this.setSize(this.width+this.getInsets().left, this.height+this.getInsets().top);
               this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               this.setVisible(true);
               this.setLocationRelativeTo (null);
        }
      
    	
        public Image draw()
        {  
        	   BufferedImage bimg=new BufferedImage(this.height, this.width,BufferedImage.TYPE_INT_RGB);
        	   vpf.draw(bimg.getGraphics());
        	   vp.draw(bimg.getGraphics());
        	   return bimg;
        }
    
       public void paint(Graphics g) {
    	   	g.drawImage(draw(),this.getInsets().left,this.getInsets().top,null);
    	
         }
		@Override
		public void update(Observable arg0, Object arg1) {
		  repaint();
		}

        
}

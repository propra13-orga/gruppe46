/* 
 * View of the playing Field. 
 * This class draws the content of the array of PlayingField
 * and returns it in form of an array of ViewItems.
 * 
 */

package ludigame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

public class ViewPlayingField implements Drawable, Observer {

    private PlayingField pf;
    private ViewItem[] viewItemArr;
    private  BufferedImage floor;

    public ViewPlayingField(PlayingField pf) {
        this.pf = pf;
        pfGUI();
        floor=null;
    }
    
    
    /* This method loads the images of the items to the ViewItemArr (ay). */ 
    public void pfGUI()
    {
        int a=pf.getFieldarray().length;
        viewItemArr = new ViewItem[a];
     for (int i = 0; i < this.pf.getFieldarray().length; i++) {
            if (pf.getFieldarray()[i].getType().equals("wall")) {
                ViewItem viewItem = new ViewItem(pf.getFieldarray()[i], "images/wand2.png");
                viewItemArr[i] = viewItem;
            } else if (pf.getFieldarray()[i].getType().equals("bonus")) {
                ViewItem viewItem = new ViewItem(pf.getFieldarray()[i], "images/bonus.png");
                viewItemArr[i] = viewItem;
            } else if (pf.getFieldarray()[i].getType().equals("finish")) {
            	ViewItem viewItem = new ViewItem(pf.getFieldarray()[i], "images/finish.png");
            	viewItemArr[i] = viewItem;
            } else if (pf.getFieldarray()[i].getType().equals("enemy")) {
            	ViewItem viewItem = new ViewItem(pf.getFieldarray()[i], "images/enemy.png");
            	viewItemArr[i] = viewItem;
            }
            
     }
     System.out.println("Layer="+pf.getLayer());
   
    }
    
    /* return the ViewItemArr(ay) */
    public ViewItem[] getViewItems()
    {
    	return viewItemArr;
    }
    

    /* 
     * possible due to our abstract interface drawable
     * this method draws the playingfield to a graphic g.
     * @see ludigame.Drawable#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
    	  
    				try {
    					floor = ImageIO.read(new File("images/boden.png"));
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    	 for (int j=0; j<pf.getHeight()/60; j++)
         {
        	 for (int k=0;k<pf.getWidth()/60; k++)
        	 {
        		 g.drawImage(floor, j*60, k*60, null);
        	 }
         }
    	  for (int i=0; i<viewItemArr.length; i++)
          {
          	viewItemArr[i].draw(g);
          }
    }

    /*
     * if the model of PlayingField changes this method is called.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
	@Override
	public void update(Observable o, Object arg) {
		pfGUI();
		
	}

    
}

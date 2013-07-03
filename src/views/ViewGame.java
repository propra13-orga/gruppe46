/*
 *  Main View which is extended from JFrame and is updated
 *  by its observables (implements Observer).
 *  This class draws the game and redraws it on updates.
 */
package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import models.*;

public class ViewGame extends JPanel implements Observer {

	private ViewSpieler vsp;
	private ViewPlayingField vpf;
	private ArrayList<ViewPlayer> vken;
	private int height, width;
	public ViewMoveableObjects vMovObj;
	private ArrayList<ViewPlayer> ven;
	private ViewStatusBar vsb;

	public ViewGame() {
		this.setBounds(0, 0, 600, 660);
	}

	/* Initialize with the dimension of the map and all other views */
	public void setVars(int height, int width, ViewSpieler vsp,
			ViewPlayingField vpf, ArrayList<ViewPlayer> vken,
			ArrayList<ViewPlayer> ven, ViewMoveableObjects vMovObj, ViewStatusBar vsb) {
		this.vken = vken;
		this.height = height;
		this.width = width;
		this.ven = ven;
		this.vpf = vpf;
		this.vMovObj = vMovObj;
		this.vsb=vsb;
		window();
		this.vsp = vsp;
	}
	
	public void addEnemy(ViewEnemy ven)
	{
		this.ven.add(ven);
	}

	/* the window is created here with its properties */
	public void window() {
		this.setBounds(this.getInsets().left, this.getInsets().top, this.width,
				this.height+vsb.getHeight());

		/* Exit the whole program if you click on the X on the upper right */
		this.setVisible(true);
	}

	/*
	 * Draw an image before drawing it on the Window to prevent flickering (also
	 * highers fps) this method returns an image which will be later drawn on
	 * this view
	 */
	public Image draw() {
		BufferedImage bimg = new BufferedImage(this.height, this.width,
				BufferedImage.TYPE_INT_RGB);
		vpf.draw(bimg.getGraphics());
		vsp.draw(bimg.getGraphics());
		for (int i = 0; i < vken.size(); i++)
			vken.get(i).draw(bimg.getGraphics());
		for (int i = 0; i < ven.size(); i++)
			ven.get(i).draw(bimg.getGraphics());
		vsp.draw(bimg.getGraphics());
		vMovObj.draw(bimg.getGraphics());
		return bimg;
	}

	/*
	 * this method draws the image which is drawn by draw() to this view
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		g.drawImage(draw(), this.getInsets().left, this.getInsets().top+30, null);
		vsb.draw(g);
	}

	/*
	 * redraws the view on an update of its observables.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}

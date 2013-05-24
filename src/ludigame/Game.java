/*
 * The Game class create all necessary objects for a new game.
 * It decides whether a game is lost or not and tells this the Menue.
 * 
 */
package ludigame;

import java.util.Observable;
import java.util.Observer;


public class Game  extends Observable implements Observer {

	private PlayingField pf;
	private ViewPlayingField vpf;
	private Spieler sp;
	private ViewSpieler vp;
	private Rules rules;
	private ControllerSpieler cp;
	private View view;
	private boolean win;

	
	public Game(int lvl)
	{
		win=true;
	    /*  initialize the PlayingField and its View */
	    pf=new PlayingField(lvl);
	    vpf=new ViewPlayingField(pf);
	    
	    /* initialize the Player and its View */ 
    	sp = new Spieler(pf.getStartX()*60, pf.getStartY()*60);
	    vp=new ViewSpieler(sp, "images/playerR.png");
	 
	    /* initialize the Enemy and its View */ 
	     new Enemy(150,150);
	     new ViewEnemy(sp, "images/enemy.png");
	    
	    /* initialize the Rules which got:
	     * PlayingField
	     * ViewPlayingField
	     * Spieler
	     * ViewSpieler
	     */
	    rules = new Rules(sp, vp, pf, vpf);
	    
	    /* initialize  additional Controllers */ 
	    cp=new ControllerSpieler (rules);

	    /* Create the main View of the Game on wich we will draw everything */ 
        view= new View(pf.getHeight(),pf.getWidth(),vp,vpf);
        
        /* attach the controller of the Player to the view to make interaction possible */ 
        view.addKeyListener(cp);
        /* Spieler is observed by this class and by the view
         * while this class decides wheter a game is won or not
         * the view redraws the player 
         */
        sp.addObserver(view);
        sp.addObserver(this);
        
        /* the playingfield is observerd by it's view*/
        pf.addObserver(vpf);     
	}

    /*This method returns wether the game is won or not */
	public boolean win()
	{
		return win;
	}
	
	
	/* This method is called when the player's position or lifestatus is updated
	 * it sets the win variable to true or false 
	 * this is necessary to decide if the game is lost.
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		if (pf.getWin()==true)
		{
			view.setVisible(false);
			setChanged();
			win=true;
			this.notifyObservers();
		}
		if(sp.getLifestatus()==false)
		{
			view.setVisible(false);
			setChanged();
			win=false;
			this.notifyObservers();
		}
		
	}
}




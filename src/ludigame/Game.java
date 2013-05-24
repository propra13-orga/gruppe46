package ludigame;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class Game  extends Observable implements Observer {

	private PlayingField pf;
	private ViewPlayingField vpf;
	private Spieler sp;
	private ViewSpieler vp;
	private Enemy enemy;
	private ViewEnemy venemy;
	private Rules rules;
	private ControllerSpieler cp;
	private View view;
	private boolean win;

	
	public Game(int lvl)
	{
		win=true;
	    // Initialize Spielfeld + its view
	    pf=new PlayingField(lvl);
	    vpf=new ViewPlayingField(pf);
	    
 	 // Initialize Spieler
	    System.out.println(pf.getStartX());
	    System.out.println(pf.getStartY());
    	sp = new Spieler(pf.getStartX()*60, pf.getStartY()*60);
	    vp=new ViewSpieler(sp, "images/playerR.png");
	 // Initialize Enemy   
	    
	    enemy = new Enemy(150,150);
	    venemy=new ViewEnemy(sp, "images/enemy.png");
	    
	
	    // Initialize Rules
	    rules = new Rules(sp,pf,vp, vpf);
	    
	    // Initialize Controller for Player 
	    cp=new ControllerSpieler (rules);
	    
	    
	  
	    // Create View & attach KeyListener to it
        view= new View(pf.getHeight(),pf.getWidth(),vp,vpf);
        view.addKeyListener(cp);
        
        sp.addObserver(view);
        sp.addObserver(this);
        pf.addObserver(vpf);     
	}


	public boolean win()
	{
		return win;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Ich war hier und Lifestatus ist:"+sp.getLifestatus());
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




/*
 * The Game class create all necessary objects for a new game.
 * It decides whether a game is lost or not and tells this the Menue.
 * 
 */
package ludigame;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.ControllerEnemy;
import controller.ControllerKenemy;
import controller.ControllerSpieler;
import AI.*;
import views.ViewEnemy;
import views.ViewGame;
import views.ViewKEnemy;
import views.ViewMoveableObjects;
import views.ViewPath;
import views.ViewPlayer;
import views.ViewPlayingField;
import views.ViewSpieler;
import views.ViewStatusBar;
import models.*;
import controller.EnemyManager;

public class Game extends Observable implements Observer {

	private PlayingField pf;
	private ViewPlayingField vpf;
	private Spieler sp;
	private KEnemy ken;
	private ViewKEnemy vken;
	private AStar a;
	private ViewSpieler vsp;
	private ControllerSpieler cp;
	private ViewGame vgame;
	private boolean win, openshop;
	private EnemyManager enMan;
	private ViewMoveableObjects vMoveObj;
	private MoveableObjects movObj;
	private ViewStatusBar vsb;
	
	private ArrayList<RangeSkills> rskills = new ArrayList<RangeSkills>();

	public Game(int lvl, ViewGame vgame) {
		this.vgame = vgame;
		openshop = false;
		win=false;

		movObj = new MoveableObjects(vgame);
		pf = new PlayingField(lvl, movObj);
		vpf = new ViewPlayingField(pf);

		 vMoveObj = new ViewMoveableObjects(movObj);
		sp = new Spieler(pf, movObj);
		ViewSpieler vsp = new ViewSpieler(sp, "images/playerV.png");
		 vsb=new ViewStatusBar(sp,600);

		cp = new ControllerSpieler(sp);

		vgame.addKeyListener(cp);
		vgame.requestFocus();
		enMan = new EnemyManager(pf, sp);
		pf.addObserver(enMan);
		pf.addObserver(vpf);
		pf.addObserver(this);
		sp.addObserver(this);

		Updater upd = new Updater();
		upd.addObserver(vgame);
		cp.start();
		vgame.setVars(600, 630, vsp, vpf, enMan.getvKen(), enMan.getven(),
				vMoveObj, vsb);
		

	}

	/* This method returns wether the game is won or not */
	public boolean win() {
		return win;
	}
	
	public void setvEn(Enemy en)
	{
		ViewEnemy ven=new ViewEnemy(en, "images/enemyV2.png");
		vgame.addEnemy(ven);
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public ControllerSpieler getControllerSpieler()
	{
		return cp;
	}
	public Spieler getSpieler() {
		return sp;
	}

	public boolean shop() {
		return openshop;
	}
	public void resetSpieler(Spieler newSpieler)
	{
		this.sp=newSpieler;
	}
	public Player getPlayer()
	{
		return sp;
	}
	
	public PlayingField getPlayingField()
	{
		return pf;
	}
	public MoveableObjects getMovableObjects()
	{
		return movObj;
	}
	public ViewMoveableObjects getViewMoveableObjects()
	{
		return vMoveObj;
	}
	

	@Override
	public void update(Observable o, Object arg) {

		if (pf.getWin())
		{
			setChanged();
			win=true;
			
			pf.deleteObserver(enMan);
			pf.deleteObserver(vpf);
			pf.deleteObserver(this);
			sp.deleteObserver(this);
			for(int i=0; i<pf.getEnemies().size(); i++)
				pf.getEnemies().get(i).remove();
			pf=null;
			sp=null;
			enMan=null;
			notifyObservers();
		}

		if (sp.getLifes() <= 0) {
			setChanged();
			win = false;
			
			pf.deleteObserver(enMan);
			pf.deleteObserver(vpf);
			pf.deleteObserver(this);
			sp.deleteObserver(this);
			for(int i=0; i<pf.getEnemies().size(); i++)
				pf.getEnemies().get(i).remove();
			pf=null;
			sp=null;
			enMan=null;
			notifyObservers();
			
		
			
			
		}
		
		
	}

	/*
	 * This method is called when the player's position or lifestatus is updated
	 * it sets the win variable to true or false this is necessary to decide if
	 * the game is lost.
	 */

}

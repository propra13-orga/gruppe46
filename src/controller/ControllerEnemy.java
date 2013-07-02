/*
 * This Class is not used yet. Therefore there is no documentation.
 */
package controller;

import AI.AStar;
import models.Enemy;
import models.Finish;
import models.Key;
import models.Player;
import models.PlayingField;
import models.Spieler;

public class ControllerEnemy implements Runnable {

	private Player en;
	private AStar ai;
	private Thread t;
	private Spieler sp;
	private PlayingField pf;
	private int x, y, px, py;
	private boolean win = true;
	private boolean alive;
	private int counter=0;
	private int counterdmg=0;
	public ControllerEnemy(Spieler sp, Player en, AStar ai, PlayingField pf) {
		this.pf = pf;
		this.sp = sp;
		this.en = en;
		this.ai = ai;
		this.alive=false;
		t = new Thread(this);
		t.start();

		ai.ClearAll();
		ai.createFieldarray();
		ai.setStart(en.getPosX(), en.getPosY());
		ai.setTarget(sp.getPosX(), sp.getPosY());
		ai.findpath();

		x = (ai.getPath().get(ai.getPath().size() - 2).getPosX() + 10);
		y = (ai.getPath().get(ai.getPath().size() - 2).getPosY() + 10);
		px = en.getPosX();
		py = en.getPosY();

	}

	public boolean getWin() {
		return win;
	}

	
	@Override
	public void run() {
		
		
		while ((en.getLifestatus())&&(en.gotRemoved()==false)) {
		
				 if ((px == x) && (py == y)) {
				ai.ClearAll();
				ai.createFieldarray();
				ai.setStart(en.getPosX(), en.getPosY());
				ai.setTarget(sp.getPosX(), sp.getPosY());
				try
				{
					ai.findpath();
				}catch (Exception e)
				{}
				if (ai.getPath().size() > 2) {
					x = (ai.getPath().get(ai.getPath().size() - 2).getPosX() + 10);
					y = (ai.getPath().get(ai.getPath().size() - 2).getPosY() + 10);
				} else
					win = false;
			}
			px = en.getPosX();
			py = en.getPosY();

			try {
				Thread.sleep(en.getSpeed());
			} catch (Exception e) {
			}
			/* NEVER CHANGE THE ORDER OF THE IF CLAUSES IT IS TROUGHTHOUGHT! */

			if ((x > px)) {
				// en.changePos(px+1, py);
				en.setSpeedX(1);
			}
			if ((x < px)) {
				// en.changePos(px-1, py);
				en.setSpeedX(-1);
			}
			if (y > py) {

				en.setSpeedY(1);
			}

			if (y < py) {

				en.setSpeedY(-1);
			}

			en.changePos(en.getPosX() + en.getSpeedX(),
					en.getPosY() + en.getSpeedY());
			en.setSpeedX(0);
			en.setSpeedY(0);
			en.updateAttributesAndItems();
			en.getDamage();

			if(this.counterdmg==100)
			{
			en.setTypedforSkill(1);
			en.shot();
			en.setManaPoints(20);
			counterdmg=0;
			}
			else
			{
				this.counterdmg++;
			}
		}
		
		 if((en.getLifestatus()==false)&&(en.gotRemoved()==false))
		 {
		 Key key = new Key(null);
		 key.setPos(en.getPosX(), en.getPosY());
		 key.setTarget(en.getTargetLayer(), en.getTargetLvl());
		 pf.getItemList().add(key);
		
		 en.setLifes(en.getLifes()-1);
		 this.counter=1;
		 }
		}

	
}

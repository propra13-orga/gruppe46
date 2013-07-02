package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import AI.AStar;
import models.Player;
import models.PlayingField;
import models.Spieler;
import views.ViewEnemy;
import views.ViewKEnemy;
import views.ViewPlayer;

public class EnemyManager implements Observer {
	PlayingField pf;
	Spieler sp;
	ArrayList<ViewPlayer> vKen = new ArrayList<ViewPlayer>();
	ArrayList<ViewPlayer> ven = new ArrayList<ViewPlayer>();
	AStar a;

	public EnemyManager(PlayingField pf, Spieler sp) {
		this.pf = pf;
		this.sp = sp;
		createEnemies();
	}

	public void createEnemies() {
		for (int i = 0; i < pf.getEnemies().size(); i++) {

			if (pf.getEnemies().get(i).getClass().getName()
					.equals("models.KEnemy")) {
				ControllerKenemy ken = new ControllerKenemy(pf.getEnemies()
						.get(i), sp, pf);
				if(pf.getEnemies().get(i).getEnTyp().equals("fire"))
						{
					vKen.add(new ViewKEnemy(pf.getEnemies().get(i),
								"images/FireEnemy.png"));
					}
				else{
				vKen.add(new ViewKEnemy(pf.getEnemies().get(i),
						"images/Ludi2.png"));}
	

			} else if ((pf.getEnemies().get(i).getClass().getName()
					.equals("models.Enemy"))) {
			
				try
				{
					 a = new AStar(pf);
				ControllerEnemy cen = new ControllerEnemy(sp, pf.getEnemies()
						.get(i), a, pf);

				ven.add(new ViewEnemy(pf.getEnemies().get(i),
						"images/EnemyV.png"));
				}
				catch(Exception e)
				{
					System.out.println("Error mit setzen da und da bei map load aufgetreten");
					createEnemies();
					
				}
				

			}

		}
	}

	public ArrayList<ViewPlayer> getvKen() {
		return vKen;
	}

	public ArrayList<ViewPlayer> getven() {
		return ven;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		
		a=null;
		vKen.clear();
		ven.clear();
		createEnemies();
	}
}
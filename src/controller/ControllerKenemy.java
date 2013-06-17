package controller;

import Sounds.Sound;
import views.ViewCash;
import models.Cash;
import models.KEnemy;
import models.Player;
import models.PlayingField;
import models.Spieler;

public class ControllerKenemy implements Runnable {

	private Player ken;
	private Thread t;
	private int beforeX;
	private int beforeY;
	private Player sp;
	private PlayingField pf;

	public ControllerKenemy(Player ken, Spieler sp, PlayingField pf) {
		this.sp = sp;
		this.ken = ken;
		this.setBeforeX(0);
		this.pf=pf;
		t = new Thread(this);
		t.start();
		
	}

	public int getBeforeX() {
		return this.beforeX;

	}

	private void setBeforeX(int b) {
		this.beforeX = b;
	}

	public int getBeforeY() {
		return this.beforeY;

	}

	private void setBeforeY(int b) {
		this.beforeY = b;
	}

	public int random(int low, int high) {
		high++;
		return (int) (Math.random() * (high - low) + low);
	}

	@Override
	public void run() {

		while ((ken.getLifestatus()==true)&&(ken.gotRemoved()==false)) {
			
			ken.getDamage();

			if (((sp.getPosX() <= ken.getPosX()
					&& sp.getPosX() + 40 >= ken.getPosX() || sp.getPosX() >= ken
					.getPosX() && sp.getPosX() <= ken.getPosX() + 28)
					&& sp.getPosY() >= ken.getPosY() && sp.getPosY() <= ken
					.getPosY() + 50)
					|| ((sp.getPosX() >= ken.getPosX()
							&& sp.getPosX() <= ken.getPosX() + 28 || sp
							.getPosX() <= ken.getPosX()
							&& sp.getPosX() + 40 >= ken.getPosX())
							&& sp.getPosY() <= ken.getPosY() && sp.getPosY() + 40 >= ken
							.getPosY())

			)

			{
				Sound outch=new Sound();
				outch.play("sounds/outch.wav");
				sp.takedamage(1);
				
			}
			try {
				t.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.setBeforeX(this.ken.getPosX());
			this.setBeforeY(this.ken.getPosY());
			if (this.ken.getSpeedX() == 0 && this.ken.getSpeedY() == 0) {

				if (this.ken.getDirection() == 1) {

					this.ken.changePos(this.ken.getPosX() + 1,
							this.ken.getPosY());
					if (this.ken.getPosX() == this.getBeforeX()
							&& this.ken.getPosY() == this.getBeforeY()) {
						this.ken.setSpeedX(0);
						this.ken.setSpeedY(0);
						this.ken.setDirection(this.random(0, 3));
					}
					
				} else if (this.ken.getDirection() == 3) {
					this.ken.changePos(this.ken.getPosX() - 1,
							this.ken.getPosY());
					if (this.ken.getPosX() == this.getBeforeX()
							&& this.ken.getPosY() == this.getBeforeY()) {
						this.ken.setSpeedX(0);
						this.ken.setSpeedY(0);
						this.ken.setDirection(this.random(0, 3));
					}
				

				}

				if (this.ken.getDirection() == 0) {

					this.ken.changePos(this.ken.getPosX(),
							this.ken.getPosY() + 1);

					if (this.ken.getPosX() == this.getBeforeX()
							&& this.ken.getPosY() == this.getBeforeY()) {
						this.ken.setSpeedX(0);
						this.ken.setSpeedY(0);
						this.ken.setDirection(this.random(0, 3));
					}

				}
				if (this.ken.getDirection() == 2) {

					this.ken.changePos(this.ken.getPosX(),
							this.ken.getPosY() - 1);

					if (this.ken.getPosX() == this.getBeforeX()
							&& this.ken.getPosY() == this.getBeforeY()) {
						this.ken.setSpeedX(0);
						this.ken.setSpeedY(0);
						this.ken.setDirection(this.random(0, 3));
					}

				}
			}

		}
		// TODO Auto-generated method stub
		if(ken.getLifestatus()==false)
		{
		Cash cash = new Cash(null);
		cash.setPos((ken.getPosX()/60)*60, (ken.getPosY()/60)*60);
		cash.setValue(10);
		pf.getItemList().add(cash);
		
		}
		
	}

}

/*
 * Every Player which is either controlled by an user or


 * by an AI is extended by this class. The most important methods are included
 * here.
 * 
 * it is extended Observable because its updates are important for their views.
 * 
 */

package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Sounds.Sound;

public class Player extends Observable  {
	private int TLayer, TLvl;
	private int posX, posY, money;
	private boolean alive, typed, openshop;
	private int lifes;
	private PlayingField pf;
	private int speed = 15;
	private int speedX, speedY, healthpoints, manapoints;
	private int direction = 0;
	private int armor = 0;
	private int playersFernschaden = 20;
	private int playersSchaden=10;
	private int playersBallRange=200;
	private int playersLaserRange=40;
	private int itemcount = 0;
	private int typedSkill;
	private MagicBall ball;
	private MoveableObjects movObj;
	private int manaCost;
	private int breite;
	private int hohe;
	private boolean gotRemoved;
	private ArrayList<Item> items = new ArrayList<Item>();

	/* Every Player is initialized with its position */

	public Player(PlayingField pf, MoveableObjects movObj) {
		this.alive = true;
		this.pf = pf;
		this.movObj = movObj;
		this.setHealthpoints(100);
		this.setManaCost(10);
		this.setManaPoints(100);
		setLifes(3);
		this.setManaCost(10);
		this.setBreite(40);
		this.setHohe(40);
		this.gotRemoved=false;
	}
	
	public void setTarget(int TLayer, int TLvl)
	{
		this.TLayer=TLayer;
		this.TLvl=TLvl;
	}
	
	public int getTargetLayer()
	{
		return TLayer;
	}
	
	public int getTargetLvl()
	{
		return TLvl;
	}
	
	public void remove()
	{
		gotRemoved=true;
	}
	
	public boolean gotRemoved()
	{
		return gotRemoved;
	}
	public void setPlayersSchaden(int d)
	{
		this.playersSchaden=d;
	}
	
	public int getPlayersSchaden()
	{
		return this.playersSchaden;
	}
	
	public int getPlayersFernSchaden()
	{
		return this.playersFernschaden;
	}
	
	public void setPlayersFernSchaden(int d)
	{
		this.playersFernschaden=d;
	}
	
	
	
	public int getManaCost() {
		return this.manaCost;
	}

	public void setManaCost(int m) {
		this.manaCost = m;
	}

	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	
	public void setMoney(int m)
	{
		this.money=m;
	}
	public void getDamage() {
		for (int i = 0; i < movObj.getRskills().size(); i++) {
			if (/*(this.posX + 60 >= movObj.getRskills().get(i).getPosX()
					+ movObj.getRskills().get(i).getSkillSize())
					&& (this.posX <= movObj.getRskills().get(i).getPosX()
							+ movObj.getRskills().get(i).getSkillSize())
					&& (this.posY <= movObj.getRskills().get(i).getPosY()
							+ movObj.getRskills().get(i).getSkillSize())
					&& this.posY + 60 >= movObj.getRskills().get(i).getPosY()
							+ movObj.getRskills().get(i).getSkillSize())*/
					
					this.posX< movObj.getRskills().get(i).getPosX() 
					&& this.posX+this.getBreite()>=movObj.getRskills().get(i).getPosX()
					&& this.posY<=movObj.getRskills().get(i).getPosY()
					&& this.posY+this.getHohe()>= movObj.getRskills().get(i).getPosY()
					|| this.posX>= movObj.getRskills().get(i).getPosX()
					&& this.posX<= movObj.getRskills().get(i).getPosX()+20
					&& this.posY<=movObj.getRskills().get(i).getPosY()
					&& this.posY+this.getHohe()>=movObj.getRskills().get(i).getPosY()
					|| this.posX>=movObj.getRskills().get(i).getPosX()
					&& this.posX<=movObj.getRskills().get(i).getPosX()+20
					&& this.posY>=movObj.getRskills().get(i).getPosY()
					&& this.posY<=movObj.getRskills().get(i).getPosY()+20
					|| this.posX<=movObj.getRskills().get(i).getPosX()
					&&this.posX+this.getBreite()>=movObj.getRskills().get(i).getPosX()
					&&this.posY>=movObj.getRskills().get(i).getPosY()
					&&this.posY<=movObj.getRskills().get(i).getPosY()+20
					)
			
			{
				if ((movObj.getRskills().get(i).getOwner() != this)
						&& (!(movObj.getRskills().get(i).getDestroyed()))) {
					this.takedamage(movObj.getRskills().get(i).getDmg());
					movObj.getRskills().get(i).setDestroyed(true);
					if (this.getHealthpoints() < 1) {
						this.killplayer();
					}

				}

			}
		}
	}

	public void setTyped(boolean b) {
		this.typed = b;
	}

	public boolean getTyped() {
		return this.typed;
	}

	public void setTypedforSkill(int b) {
		this.typedSkill = b;
	}

	public int getTypedforSkill() {
		return this.typedSkill;
	}

	public void updateAttributesAndItems() {
		for (int j = 0; j < pf.getItemList().size(); j++) {
			if (this.posX< pf.getItemList().get(j).getPosX() 
					&& this.posX+30>=pf.getItemList().get(j).getPosX()
					&& this.posY<=pf.getItemList().get(j).getPosY()
					&& this.posY+40>= pf.getItemList().get(j).getPosY()
					|| this.posX>= pf.getItemList().get(j).getPosX()
					&& this.posX<= pf.getItemList().get(j).getPosX()+40
					&& this.posY<=pf.getItemList().get(j).getPosY()
					&& this.posY+40>=pf.getItemList().get(j).getPosY()
					|| this.posX>=pf.getItemList().get(j).getPosX()
					&& this.posX<=pf.getItemList().get(j).getPosX()+40
					&& this.posY>=pf.getItemList().get(j).getPosY()
					&& this.posY<=pf.getItemList().get(j).getPosY()+40
					|| this.posX<=pf.getItemList().get(j).getPosX()
					&&this.posX+30>=pf.getItemList().get(j).getPosX()
					&&this.posY>=pf.getItemList().get(j).getPosY()
					&&this.posY<=pf.getItemList().get(j).getPosY()+40
					
					)
					
					
					/*pf.getItemList().get(j).getPosX())
					&& (this.posX < pf.getItemList().get(j).getPosX() + 40)
					&& (this.posY > pf.getItemList().get(j).getPosY())
					&& (this.posY < pf.getItemList().get(j).getPosY() + 40))*/ 
			{
				this.items.add(pf.getItemList().get(j));
				Item item= pf.getItemList().get(j);
				pf.getItemList().remove(j);
				item.setOwner(this);	
			}
		}
	}


	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getLifes() {
		return lifes;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getArmor() {
		return armor;
	}

	public void takedamage(int dmg) {
		
		if ((healthpoints - dmg) > 0)
			this.healthpoints = healthpoints - dmg;
		else
		{
			killplayer();
			playDeadsound();
		}
		
	}

	public void setHealthpoints(int hp) {
		this.healthpoints = hp;
	}

	public void setManaPoints(int mana) {
		this.manapoints = mana;
	}

	public void takeManaDmg() {
		this.manapoints = this.manapoints - this.getManaCost();
	}

	public int getManaPoints() {
		return this.manapoints;
	}

	/* changes the lifestatus of the player */
	public void killplayer() {

	setChanged();
	   this.items.clear();
		lifes--;
		if (lifes == 0)
			alive = false;
		killSpieler();
		
    notifyObservers();


	}
	public void playDeadsound()
	{
		
	}
	public void killSpieler()
	{
		
	}
	public void setLifestatus(boolean alive)
	{
		setChanged();
		this.alive=alive;
		notifyObservers();
	}
	public void addItem(Item item) {
		items.add(item);
	}

	public int getDirection() {
		return this.direction;
	}

	/* setters */
	public void setPos(int posX, int posY) {

		this.posX = posX;
		this.posY = posY;

	}

	public PlayingField getPf() {
		return this.pf;
	}

	public void setDirection(int d) {
		this.direction = d;

	}

	
	public void changePos(int posX, int posY) {

		if ((!((pf.getFieldarray()[(posX) / 60][(posY) / 60]).getClass()
				.getName().equals("models.Wall")))
				&& (!((pf.getFieldarray()[(posX + 40) / 60][(posY) / 60])
						.getClass().getName().equals("models.Wall")))
				&& (!((pf.getFieldarray()[(posX) / 60][(posY + 40) / 60])
						.getClass().getName().equals("models.Wall")))
				&& (!((pf.getFieldarray()[(posX + 40) / 60][(posY + 40) / 60])
						.getClass().getName().equals("models.Wall")))
				&& ((posX + 45 < pf.getWidth()))
				&& (posX >= 0)
				&& (posY + 47 < pf.getHeight()) && (posY >= 0)) {
			setPos(posX, posY);
			if (speedX > 0)
				reactOnBlock(pf.getFieldarray()[(posX + 40) / 60][(posY) / 60]);
			if (speedX < 0)
				reactOnBlock(pf.getFieldarray()[posX / 60][(posY) / 60]);
			if (speedY > 0)
				reactOnBlock(pf.getFieldarray()[posX / 60][(posY + 40) / 60]);
			if (speedY < 0)
				reactOnBlock(pf.getFieldarray()[posX / 60][(posY) / 60]);
		}

	}

	/* Methoden um richtige Entscheidungen beim Positionsetzen zu treffen? */

	private void reactOnBlock(Block block) {
		String blockType = block.getClass().getName();
		switch (blockType) {
		case "models.Trap":
			Sound no=new Sound();
			no.play("sounds/no.wav");
			this.setPos(pf.getStartX() * 60, pf.getStartY() * 60);
			movObj.getRskills().clear();
			this.killplayer();
			break;
		case "models.Shop":
			
			reactShop();
			
			break;
		default:
			break;
		}

	}
	
	public boolean checkAccount(int amount)
	{
		if (this.money+amount>=0)
		{	
			return true;
		} else return false;
	}
	public void pay(int amount)
	{
		this.money=money+amount;
	}

	public void dropItem(int i)
	{
		this.pf.getItemList().add(this.items.get(i));
		this.items.remove(i);
	}
	
	public boolean getOpenShop() {
		return openshop;
	}

	public void reactShop() {

	}

	public void openShop() {
		openshop = true;
	}

	public void reactOnFinish(int TargetLayer, int Tlvl) {

	}

	public void setSpeedX(int speed) {
		speedX = speed;
		if (speed != 0) {
			if (speedX > 0)
				direction = 3;
			else
				direction = 1;
		}
	}

	public void setSpeedY(int speed) {
		speedY = speed;
		if (speed != 0) {
			if (speedY > 0)
				direction = 0;
			else
				direction = 2;
		}
	}

	public void setSpeed(int speed) {
		if ((this.speed + speed) > 4) {
			this.speed = this.speed + speed;
		} else {
			this.speed = 5;
		}
	}

	/* getters */
	public boolean getLifestatus() {
		return alive;
	}

	public int getHealthpoints() {
		return healthpoints;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getSpeed() {
		return speed;
	}

	public void shot() {

		if (this.getTypedforSkill() == 1) {
			if (this.getManaPoints() > this.getManaCost()) {
				this.takeManaDmg();
			

				ball = new MagicBall(this.getPosX(), this.getPosY(), this.pf,
						this);
				ball.setRange(this.getPlayersBallRange());
				switch (direction) {
				case 0:
					ball.setSpeedThread(this.getSpeed() - 2);
					ball.setPos(this.getPosX() + 10, this.getPosY() + 40);
					ball.setSpeedY(1);

					break;
				case 1:
					ball.setSpeedThread(this.getSpeed() - 2);
					ball.setPos(this.getPosX() - 20, this.getPosY() + 20);
					ball.setSpeedX(-1);
					break;
				case 2:
					ball.setSpeedThread(this.getSpeed() - 2);
					ball.setPos(this.getPosX() + 10, this.getPosY() - 20);
					ball.setSpeedY(-1);
					break;
				case 3:
					ball.setSpeedThread(this.getSpeed() - 2);
					ball.setPos(this.getPosX() + 40, this.getPosY() + 20);
					ball.setSpeedX(1);
					break;
				}

				this.movObj.addRskill(ball);
	
			}
		} else if (this.getTypedforSkill() == 2) {
			LaserEyes laser;
			LaserEyes laser2;

			switch (direction) {
			case 0:
				laser = new LaserEyes(this.getPosX() + 12, this.getPosY() + 15,
						this.pf, this);
				laser.setDmg(this.getPlayersSchaden());
				laser2 = new LaserEyes(this.getPosX() + 28,
						this.getPosY() + 15, this.pf, this);
				laser2.setDmg(this.getPlayersSchaden());
				laser.setSpeedThread(this.getSpeed() - 3);
				laser.setRange(25+this.getPlayersLaserRange());
				laser2.setSpeedThread(this.getSpeed() - 3);
				laser2.setRange(25+this.getPlayersLaserRange());

				laser.setSpeedY(1);
				laser2.setSpeedY(1);
				this.movObj.addRskill(laser2);
				this.movObj.addRskill(laser);

				break;
			case 1:
				laser = new LaserEyes(this.getPosX(), this.getPosY() + 10,
						this.pf, this);
				laser.setSpeedX(-1);
				laser.setSpeedThread(this.getSpeed() - 2);
				laser.setDmg(this.getPlayersSchaden());
				laser.setRange(this.getPlayersLaserRange());
				this.movObj.addRskill(laser);
				break;
			case 2:
				laser = new LaserEyes(this.getPosX() + 12, this.getPosY(),
						this.pf, this);
				laser2 = new LaserEyes(this.getPosX() + 28, this.getPosY(),
						this.pf, this);
				laser.setDmg(this.getPlayersSchaden());
				laser2.setDmg(this.getPlayersSchaden());
				laser2.setSpeedThread(this.getSpeed() - 2);
				laser.setSpeedThread(this.getSpeed() - 2);
				laser2.setRange(this.getPlayersLaserRange());
				laser.setRange(this.getPlayersLaserRange());

				laser.setSpeedY(-1);
				laser2.setSpeedY(-1);
				this.movObj.addRskill(laser2);
				this.movObj.addRskill(laser);

				break;
			case 3:
				laser = new LaserEyes(this.getPosX() + 40, this.getPosY() + 10,
						this.pf, this);
				laser.setDmg(this.getPlayersSchaden());
				laser.setSpeedThread(this.getSpeed() - 2);
				laser.setSpeedX(1);
				laser.setRange(this.getPlayersLaserRange());
				this.movObj.addRskill(laser);
				break;
			}


		}

	}

	public int getPlayersBallRange() {
		return playersBallRange;
	}

	public void setPlayersBallRange(int playersRangeBall) {
		this.playersBallRange = playersRangeBall;
	}

	public int getPlayersLaserRange() {
		return playersLaserRange;
	}

	public void setPlayersLaserRange(int playersLaserRange) {
		this.playersLaserRange = playersLaserRange;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHohe() {
		return hohe;
	}

	public void setHohe(int hohe) {
		this.hohe = hohe;
	}
	public int getMoney()
	{
		return this.money;
	}

}

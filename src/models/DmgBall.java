package models;

public class DmgBall extends Item {

	public DmgBall(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setPlayersFernSchaden(this.owner.getPlayersFernSchaden()+10);
	}

}

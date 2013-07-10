package models;

public class DmgBall extends Item {

	public DmgBall(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setPlayersFeuerSchaden(this.owner.getPlayersFeuerSchaden()+10);
	}

}

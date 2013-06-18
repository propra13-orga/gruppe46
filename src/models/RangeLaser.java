package models;

public class RangeLaser extends Item {

	public RangeLaser(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public void setAttributes()
	{
		if(this.owner.getPlayersLaserRange()<100)
		{
	this.owner.setPlayersLaserRange(this.owner.getPlayersLaserRange()+10);	
		}
	}
}

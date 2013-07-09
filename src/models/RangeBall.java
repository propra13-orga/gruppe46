package models;

public class RangeBall extends Item {

	public RangeBall(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setPlayersBallRange(this.owner.getPlayersBallRange()+40);
	}
	

}

package models;

public class DmgLaser extends Item {

	public DmgLaser(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setPlayersSchaden(this.owner.getPlayersSchaden()+1);
	}
	

}

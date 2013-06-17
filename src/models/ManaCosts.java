package models;

public class ManaCosts extends Item {

	public ManaCosts(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		if(this.owner.getManaCost()>0)
			{
			this.owner.setManaCost(this.owner.getManaCost()-2);
			}
	}

}

package models;

public class FireArmor extends Item {

	public FireArmor(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setFireArmor(this.owner.getFireArmor()+20);
	}

}

package models;

public class IceArmor extends Item {

	public IceArmor(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		this.owner.setIceArmor(this.owner.getIceArmor()+20);
	}

}

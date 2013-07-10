package models;

public class Key extends Item{

	private int TargetLayer, Tlvl;
	public Key(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		owner.reactOnFinish(this.TargetLayer,this.Tlvl);
	}
	public void setTarget(int TargetLayer, int Tlvl)
	{
		this.TargetLayer=TargetLayer;
		this.Tlvl=Tlvl;
	}
	
	public int getTargetLayer()
	{
		return TargetLayer;
	}

	public int getTlvl()
	{
		return Tlvl;
	}
}

package models;

public class Healthpack extends Item {

	public Healthpack(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public void setAttributes() {
		owner.setLifes(owner.getLifes() + 1);
	}
}

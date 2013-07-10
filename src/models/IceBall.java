package models;

public class IceBall extends RangeSkills {

	public IceBall(int x, int y, PlayingField pf, Player owner) {
		super(x, y, pf, owner);
		// TODO Auto-generated constructor stub
		this.setDmg(owner.getPlayersEisSchaden());
	}

}

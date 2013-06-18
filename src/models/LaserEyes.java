package models;

public class LaserEyes extends RangeSkills {

	public LaserEyes(int x, int y, PlayingField pf, Player owner) {
		super(x, y, pf, owner);
		this.setDmg(owner.getPlayersSchaden());
	
	}

}

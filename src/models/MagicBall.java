package models;

public class MagicBall extends RangeSkills {

	public MagicBall(int x, int y, PlayingField pf, Player owner) {
		super(x, y, pf, owner);
		// TODO Auto-generated constructor stub
		this.setDmg(owner.getPlayersFeuerSchaden());
	}
}

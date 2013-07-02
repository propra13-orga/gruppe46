package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import views.ViewGame;

public class MoveableObjects implements Observer {

	private ArrayList<RangeSkills> rskills;
	private ViewGame vgame;

	public MoveableObjects(ViewGame vgame) {
		this.vgame = vgame;
		rskills = new ArrayList<RangeSkills>();

	}

	public ArrayList<RangeSkills> getRskills() {
		return rskills;
	}

	public void addRskill(RangeSkills rskill) {
		rskill.addObserver(vgame);
		rskills.add(rskill);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		for (int i = 0; i < rskills.size(); i++) {
			if (rskills.get(i).getDestroyed() == true) {
				RangeSkills dump = rskills.get(i);
				rskills.get(i).deleteObservers();
				rskills.remove(i);
				dump = null;
			}
		}

	}

}

package AI;

import models.*;

public class Field {
	public Block item;
	public int g;
	public int h;
	public int arrayx;
	private int arrayy;
	public Field parent;
	private int f;
	private boolean movable;
	private int[] arraypos;

	public Field(Block i, int g, int h) {
		f = 0;
		h = 0;
		g = 0;
		arraypos = new int[2];
		this.arrayx = 1;
		this.arrayy = 1;
		arraypos[0] = arrayx;
		arraypos[1] = arrayy;

		this.item = i;
		this.g = g;
		this.h = h;
		calcf();
	}

	public void calcf() {
		f = g + h;
	}

	public Block getItem() {
		return item;
	}

	public void setG(int g) {
		this.g = g;
		calcf();
	}

	public void setH(int h) {
		this.h = h;
		calcf();
	}

	public boolean isMovable() {
		if (this.getItem().getClass().getName().equals("models.Wall"))
			return false;
		if (this.getItem().getClass().getName().equals("models.Trap"))
			return false;
		return true;
	}

	public void setArrayPos(int x, int y) {
		arraypos[0] = x;
		arraypos[1] = y;
	}

	public int[] getArrayPos() {
		return arraypos;
	}

	public int getf() {
		return f;
	}

	public void setParent(Field field) {
		parent = field;
	}

	public Field getParent() {
		return parent;
	}
}

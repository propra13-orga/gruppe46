/*
 * We use this interface for the View* classes.
 * This is an abstract interface we need for the draw method.
 */
package views;

/**
 *

 */
import java.awt.Graphics;

public abstract interface Drawable {
	public abstract void draw(Graphics g);
}
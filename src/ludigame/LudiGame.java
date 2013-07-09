/*
 * Starting point of all action. The class which includes the main method.
 * 
 * The Game is developed in the MVC Pattern
 * That means we strictly divided the classes by
 *  VIEW - CONTROLLER - MODEL
 */
package ludigame;

import controller.ControllerSpieler;
import views.MainWindow;

public class LudiGame {

	public static void main(String[] args) throws Exception {

		MainWindow main = new MainWindow();

	}
}

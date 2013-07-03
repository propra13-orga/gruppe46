/*
 * The Menue is the first window you see and where you can decide where
 * you go to. Ofcourse you can start a game from the menu.
 */
package views;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ludigame.Game;

public class MainWindow extends JFrame implements ActionListener, Observer {

	private CardLayout cardLayout = new CardLayout();
	private JPanel p1 = new JPanel(cardLayout);
	private MainMenu mainPanel = new MainMenu(600, 600, this);
	private ViewGame vgame = new ViewGame();
	private LosePanel lostp = new LosePanel(600, 600, this);
	private lvlEditor lvleditor=new lvlEditor(this);
	private ViewMultiplayer multiplayer=new ViewMultiplayer(this,vgame);
	//private lvlEditor lvlEditor = new lvlEditor(600, 600, this);
	private Game game;

	private LevelChooser lvlchooser = new LevelChooser(600, 600, this);

	public MainWindow() {
		/* Define this Window */
		this.setLayout(null);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Ludi the Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Define Main CardLayout Panel */

		this.add(p1);
		p1.setVisible(true);
		p1.setBounds(0, 0, 600, 630);
		p1.add(lvlchooser, "LVLCHOOSER");
		p1.add(multiplayer, "MULTIPLAYER");
		p1.add(lvleditor, "LVLEDITOR");
		p1.add(mainPanel, "MAIN");
		p1.add(vgame, "GAME");
		p1.add(lostp, "LOST");
		// p1.add(settings, "SETTINGS");

		cardLayout.show(p1, "MAIN");
		vgame.requestFocus();

	}

	public void resetSize(int x, int y) {
		this.setSize(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		btnActions(e.getActionCommand());
	}

	public void btnActions(String cmd) {
		switch (cmd) {
		case "1":
			p1.setSize(610,640);
			cardLayout.show(p1, "GAME");
			game = new Game(1, vgame);
			resetSize(p1.getWidth() + this.getInsets().left, p1.getHeight()
					+ this.getInsets().top);
			game.addObserver(this);
			break;
		case "2":
			cardLayout.show(p1, "GAME");
			game = new Game(2, vgame);
			p1.setSize(610,640);
			resetSize(p1.getWidth() + this.getInsets().left, p1.getHeight()
					+ this.getInsets().top);
			game.addObserver(this);
			break;
		case "3":
			p1.setSize(610,640);
			cardLayout.show(p1, "GAME");
			game = new Game(3, vgame);
			resetSize(p1.getWidth() + this.getInsets().left, p1.getHeight()
					+ this.getInsets().top);
			game.addObserver(this);
			break;
		case "GAME":
			cardLayout.show(p1, "GAME");
			break;
		case "SETTINGS":
			cardLayout.show(p1, "SETTINGS");
			break;
		case "CREDITS":
			cardLayout.show(p1, "SETTINGS");
			break;
		case "MULTIPLAYER":
			cardLayout.show(p1, "MULTIPLAYER");
			p1.setSize(600,600);
			this.setSize(600,600);
			break;
		case "BACK":
			cardLayout.show(p1, "MAIN");
			p1.setSize(600,600);
			this.setSize(600,600);
			break;
		case "QUIT":
			System.exit(0);
			break;
		case "LVLCHOOSER":
			cardLayout.show(p1, "LVLCHOOSER");
			break;
		case "LVLEDITOR":

			resetSize(900+ this.getInsets().left, lvleditor.getHeight()+100
					+ this.getInsets().top);
			p1.setSize(900, lvleditor.getHeight()+100);
			cardLayout.show(p1, "LVLEDITOR");
			lvleditor.setVisible(true);
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (game.win() == false) {
			cardLayout.show(p1, "MAIN");
			ImageIcon icon = new ImageIcon("images/playerHappy.png");
			JOptionPane.showMessageDialog(this,
				    "YOU LOST",
				    "FRCKING LOSER",
				    JOptionPane.INFORMATION_MESSAGE,
				    icon);
			game.deleteObserver(this);
			game=null;

		}
		else if (game.win() == true) {
			cardLayout.show(p1, "MAIN");
			ImageIcon icon = new ImageIcon("images/playerHappy.png");
			JOptionPane.showMessageDialog(this,
				    "You Won",
				    "CONGRATZ",
				    JOptionPane.INFORMATION_MESSAGE,
				    icon);
			game.deleteObserver(this);
			game=null;

		}

	}

}

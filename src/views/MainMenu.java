package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JPanel {

	private JButton Starten;
	private JButton lvlEditor;
	private JButton Credits;
	private JButton Ende;
	private JButton back;
	private int height;
	private int width;
	private int percentH = 10;
	private int percentW = 40;
	private ActionListener al;

	public MainMenu(int width, int height, ActionListener al) {
		this.width = width;
		this.height = height;
		setLayout(null);
		setVisible(true);
		this.al = al;

		Starten = new JButton("Spiel starten");
		Starten.setBounds(width / 4 - this.getInsets().left, (height / 12) * 2,
				width / 75 * percentW, height / 120 * percentH);
		add(Starten);
		Starten.setActionCommand("LVLCHOOSER");
		Starten.addActionListener(al);

		lvlEditor = new JButton("Level Editor");
		lvlEditor.setBounds(width / 4 - this.getInsets().left,
				(height / 12) * 4, width / 75 * percentW, height / 120
						* percentH);
		add(lvlEditor);
		lvlEditor.setActionCommand("LVLEDITOR");
		lvlEditor.addActionListener(al);

		Credits = new JButton("Credits");
		Credits.setBounds(width / 4 - this.getInsets().left, (height / 12) * 6,
				width / 75 * percentW, height / 120 * percentH);
		add(Credits);
		Credits.setActionCommand("CREDITS");
		Credits.addActionListener(al);

		Ende = new JButton("Ende");
		Ende.setBounds(width / 4 - this.getInsets().left, (height / 12) * 8,
				width / 75 * percentW, height / 120 * percentH);
		add(Ende);
		Ende.setActionCommand("QUIT");
		Ende.addActionListener(al);

	}

}

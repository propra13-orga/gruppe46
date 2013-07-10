package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LosePanel extends JPanel {

	private int height;
	private int width;
	private ActionListener al;
	private JButton back;
	private JButton again;
	private int percentH = 10;
	private int percentW = 40;

	public LosePanel(int width, int height, ActionListener al) {
		this.width = width;
		this.height = height;
		this.setLayout(null);

		again = new JButton();
		again.setText("Play Again");
		again.setBounds(width / 4 - this.getInsets().left, (height / 12) * 2,
				width / 75 * percentW, height / 120 * percentH);
		add(again);
		again.setActionCommand("LVLCHOOSER");
		again.addActionListener(al);

		back = new JButton();
		back.setText("Back to Mainmenu");
		back.setBounds(width / 4 - this.getInsets().left, (height / 12) * 3,
				width / 75 * percentW, height / 120 * percentH);
		add(back);
		back.setActionCommand("BACK");
		back.addActionListener(al);

	}

}

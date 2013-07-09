package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainSettings extends JPanel {

	private JButton na;

	private int height;
	private int width;
	private int percentH = 10;
	private int percentW = 40;
	private ActionListener al;

	public MainSettings(int width, int height, ActionListener al) {
		this.width = width;
		this.height = height;
		setLayout(null);
		setVisible(true);
		this.al = al;

		na = new JButton();
		na.setText("Not Available - click2get back");
		na.setBounds(width / 4 - this.getInsets().left, (height / 12) * 2,
				width / 75 * percentW, height / 120 * percentH);
		add(na);
		na.setActionCommand("BACK");
		na.addActionListener(al);

	}

}

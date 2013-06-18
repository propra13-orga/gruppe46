package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelChooser extends JPanel {

	private JButton back;
	private int percentH = 10;
	private int percentW = 40;

	public LevelChooser(int width, int height, ActionListener al) {

		GridLayout grid = new GridLayout(2, 3);
		this.setLayout(grid);

		File file = new File("level/");
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

		for (int i = 1; i < directories.length + 1; i++) {
			JButton btn = new JButton("LEVEL " + Integer.toString(i));
			btn.setBounds(width / 4 - this.getInsets().left, (height / 12) * 2,
					width / 75 * percentW, height / 120 * percentH);
			add(btn);
			btn.setActionCommand(Integer.toString(i));
			btn.addActionListener(al);
		}

		back = new JButton();
		back.setText("Back");
		back.setBounds(width / 4 - this.getInsets().left, (height / 12) * 2,
				width / 75 * percentW, height / 120 * percentH);
		add(back);
		back.setActionCommand("BACK");
		back.addActionListener(al);

	}

}

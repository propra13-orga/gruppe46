package views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Network.Client;
import Network.Server;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMultiplayer extends JPanel {
	private JTextField textField;
	private ActionListener al;
	private ViewGame vgame;

	/**
	 * Create the panel.
	 */
	public ViewMultiplayer(final ActionListener al, final ViewGame vgame) {
		
		setLayout(null);
		this.al=al;
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			Client client = new Client(textField.getText(), al, vgame);
			}
		});
		btnNewButton.setBounds(86, 162, 402, 72);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setText("127.0.0.1");
		textField.setBounds(252, 112, 236, 39);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Server IP:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(97, 97, 145, 54);
		add(lblNewLabel);
		
		JButton btnHostGame = new JButton("Host Game");
		btnHostGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			Server srv=new Server(vgame, al);
			}
		});
		btnHostGame.setBounds(86, 259, 402, 72);
		add(btnHostGame);
		
		JButton btnBackToMain = new JButton("Back to Main");
		btnBackToMain.addActionListener(al);
		btnBackToMain.setActionCommand("BACK");
		btnBackToMain.setBounds(86, 353, 402, 72);
		add(btnBackToMain);
		this.setVisible(true);

	}
}

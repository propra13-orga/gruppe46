package views;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;


import models.Block;
import models.Floor;
import models.PlayingField;
import models.Trap;
import models.Wall;

import AI.Field;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class lvlEditor extends JPanel implements MouseListener {

	private String current="floor";
	private JLabel testlabel;
	private JPanel mappanel;
	private Block[][] Fieldarray=new Block[10][10];
	private Image	currentImage ;
	private JLabel lblLoad;
	private final Choice choiceLayer = new Choice();
	private final Choice choiceLevel = new Choice();
	/**
	 * Create the panel.
	 */
	public lvlEditor( ActionListener al) {
		setLayout(null);
		mappanel = new JPanel();
		mappanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mappanel.setBounds(176, 53, 602, 602);
		add(mappanel);
		mappanel.addMouseListener(this);
		
		testlabel = new JLabel("");
		testlabel.setBounds(480, 33, 46, 14);
		add(testlabel);
		
		JPanel tools = new JPanel();
		tools.setBounds(10, 271, 156, 384);
		add(tools);
		tools.setLayout(null);
		current="floor";
		
		for (int i=0; i<Fieldarray.length; i++)
			for (int j=0; j<Fieldarray[0].length; j++)
				Fieldarray[i][j]=new Floor(i*60, j*60);
		
		
		
		JButton floorBtn = new JButton("");
		floorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current="floor";
				
			}
		});
		
		floorBtn.setSelectedIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/lvleditor/boden.png")));
		floorBtn.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/boden.png")));
		floorBtn.setBounds(6, 3, 60, 60);
		tools.add(floorBtn);
		
		
		JButton wallBtn = new JButton("");
		wallBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="wall";
			}
		});
		wallBtn.setSelectedIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/lvleditor/Wand1.png")));
		wallBtn.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/Wand1.png")));
		wallBtn.setBounds(6, 67, 60, 60);
		tools.add(wallBtn);
		
		JButton holeBtn = new JButton("");
		holeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="hole";
			}
		});
		holeBtn.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/hole.png")));
		holeBtn.setSelectedIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/lvleditor/hole.png")));
		holeBtn.setBounds(90, 3, 60, 60);
		tools.add(holeBtn);
		
		JButton button = new JButton("");
		button.setBounds(6, 152, 60, 60);
		tools.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(90, 152, 60, 60);
		tools.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(6, 223, 60, 60);
		tools.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(90, 223, 60, 60);
		tools.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(6, 313, 60, 60);
		tools.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(90, 313, 60, 60);
		tools.add(button_5);
		
		Panel panel = new Panel();
		panel.setBounds(10, 53, 160, 212);
		add(panel);
		panel.setLayout(null);
		
		
		choiceLayer.setBounds(77, 49, 67, 20);
		panel.add(choiceLayer);
		choiceLayer.setName("choiceLayer");
		
		
		choiceLevel.setBounds(77, 25, 67, 20);
		panel.add(choiceLevel);
		choiceLevel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				choiceLayer.removeAll();
				File subfolder = new File("./level/"+choiceLevel.getSelectedItem());
				for (int i=0; i<subfolder.list().length; i++)
					choiceLayer.insert(subfolder.list()[i], i);
			}
		});
		choiceLevel.setName("choiceLevel");
		
		
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(10, 29, 45, 14);
		panel.add(lblLevel);
		
		JLabel lblLayer = new JLabel("Layer");
		lblLayer.setBounds(10, 54, 47, 14);
		panel.add(lblLayer);
		
		lblLoad = new JLabel("LOAD");
		lblLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoad.setBounds(10, 5, 46, 14);
		panel.add(lblLoad);
		
		JLabel lblNew = new JLabel("NEW");
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNew.setBounds(9, 143, 46, 14);
		panel.add(lblNew);
		
		JButton btnNewLevel = new JButton("New Level");
		btnNewLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("./level/");
				String length=String.valueOf(file.list().length+1);
				File dir = new File("./level/"+length);
				dir.mkdir();
				JOptionPane.showMessageDialog(null, "Directory for Level "+length+" was successfully created!");
				refreshLevelAndLayer();
				
			}
		});
		btnNewLevel.setBounds(10, 160, 139, 20);
		panel.add(btnNewLevel);
		
		JButton btnNewLayer = new JButton("New Layer");
		btnNewLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					File subdir = new File("./level/"+choiceLevel.getSelectedItem()+"/");
					String newLayer=String.valueOf(subdir.list().length);
					File newLayerFile=new File("./level/"+choiceLevel.getSelectedItem()+"/"+newLayer+".lvl");
					try {
						newLayerFile.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnNewLayer.setBounds(10, 181, 138, 20);
		panel.add(btnNewLayer);
		
		JButton btnNewButton = new JButton("LOAD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayingField pf = new PlayingField(1,null);
				pf.LoadLayer(Integer.valueOf(choiceLayer.getSelectedItem().replaceFirst("[.][^.]+$", "")),Integer.valueOf(choiceLevel.getSelectedItem()));
			
				Fieldarray=pf.getFieldarray();
				ViewPlayingField vpf=new ViewPlayingField(pf);
				BufferedImage bimg = new BufferedImage(mappanel.getHeight(), mappanel.getWidth(),
						BufferedImage.TYPE_INT_RGB);
				vpf.draw(bimg.getGraphics());
				mappanel.getGraphics().drawImage(bimg,1,1,null);
			}
		});
		btnNewButton.setBounds(10, 109, 134, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLevelAndLayer();
			}
		});
		btnNewButton_2.setBounds(10, 82, 134, 25);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					//FileWriter fw = new FileWriter("/level/3/1.lvl");
					//BufferedWriter bw = new BufferedWriter(fw);
					File output=new File("./level/"+choiceLevel.getSelectedItem()+"/"+choiceLayer.getSelectedItem());
					PrintWriter out = new PrintWriter(output.getAbsolutePath());
					out.println("<?xml version=\"1.0\"?>");
					out.println("<!-- XML Level Datei Version Alpha 210413  @author Gerhard Klassen -->");
					out.println("<level>");
					out.println("<author>Gerhard Klassen</author>");
					out.println("<lvlname>close your eyes</lvlname>");
					out.println("<Layer>");
					out.println("<layerdetail Height=\"600\" Width=\"600\" StartX=\"0\" StartY=\"9\"/>");
					
					for (int i =0; i<Fieldarray.length; i++)
					{
						for (int j=0; j<Fieldarray[0].length; j++)
						{
							switch(Fieldarray[i][j].getClass().getName())
							{
							case "models.Wall":
								out.println("<Block Type=\"wall\" X=\""+i+"\" Y=\""+j+"\"/>");
								break;
					
							case "models.Trap":
								out.println("<Block Type=\"trap\" X=\""+i+"\" Y=\""+j+"\"/>");
								break;
							default:
								out.println("<Block Type=\"floor\" X=\""+i+"\" Y=\""+j+"\"/>");
								break;	
							}
						}
						
					}  
					out.println("</Layer>");
					out.println("</level>");
				out.close();
				JOptionPane.showMessageDialog(null,"Level "+choiceLevel.getSelectedItem()+ "Layer "+choiceLayer.getSelectedItem()+" saved successfully!");
				} catch (FileNotFoundException es) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"no map selected!");
				} 
			
			}
			
		});
		btnNewButton_1.setBounds(628, 11, 89, 23);
		add(btnNewButton_1);
		
		JButton btnBackToMain = new JButton("back to main");
		btnBackToMain.addActionListener(al);
		btnBackToMain.setActionCommand("BACK");
		btnBackToMain.setBounds(10, 11, 136, 23);
		add(btnBackToMain);
		
		
	}
	
	
	
	public void refreshLevelAndLayer()
	{
		choiceLevel.removeAll();
		File file = new File("./level/");
		for (int i=0; i<file.list().length; i++)
			choiceLevel.insert(file.list()[i], i); 
		choiceLayer.removeAll();
		
		File subfolder = new File("./level/"+choiceLevel.getSelectedItem());
		for (int i=0; i<subfolder.list().length; i++)
			choiceLayer.insert(subfolder.list()[i], i);
	}
	
	public void drawBlock(int x, int y)
	{
		currentImage = null;
		try {
			switch(current)
			{
			case "hole":
				currentImage = ImageIO.read(new File("images/hole.png"));
				break;
			case "floor":
				currentImage = ImageIO.read(new File("images/boden.png"));
				break;
			case "wall":
			currentImage = ImageIO.read(new File("images/Wand1.png"));
			break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mappanel.getGraphics().drawImage(currentImage, x, y , null);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int posX=((((int)arg0.getPoint().getX())/60)*60);
		int posY=((((int)arg0.getPoint().getY())/60)*60);
		if ((posX<11*60)&&(posY<11*60))
		{
			drawBlock(posX+1, posY+1);
			switch (current)
			{
			case "hole":
				Fieldarray[posX/60][posY/60]=new Trap(posX/60, posY/60);
				break;
			case "wall":
				Fieldarray[posX/60][posY/60]=new Wall(posX/60, posY/60);
				break;
			case "floor":
				Fieldarray[posX/60][posY/60]=new Floor(posX/60, posY/60);
				break;
			}
			
		}
	  
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

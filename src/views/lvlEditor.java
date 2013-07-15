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
import models.Enemy;
import models.Floor;
import models.Item;
import models.KEnemy;
import models.Key;
import models.Player;
import models.PlayingField;
import models.Shoes;
import models.Shop;
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
import java.util.ArrayList;

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
	private int startX,startY;
	private boolean startset=false;
	private ArrayList<Item> itemList=new ArrayList();
	private ArrayList<Player> enemyList = new ArrayList();

	/**
	 * Create the panel.
	 */
	public lvlEditor( ActionListener al) {
		startset=false;
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
		button.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/start.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="start";
				 
			}
		});
		button.setBounds(6, 152, 60, 60);
		tools.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="key";
				
			}
		});
		button_1.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/finish.png")));
		button_1.setBounds(90, 152, 60, 60);
		tools.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="shop";
			}
		});
		button_2.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/shop.png")));
		button_2.setBounds(6, 223, 60, 60);
		tools.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="shoes";
			}
		});
		button_3.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/bonus.png")));
		button_3.setBounds(90, 223, 60, 60);
		tools.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="enemy";
			}
		});
		button_4.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/Ludi2.png")));
		button_4.setBounds(6, 313, 60, 60);
		tools.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current="boss";
			}
		});
		button_5.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/enemyV2.png")));
		button_5.setBounds(90, 313, 60, 60);
		tools.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/itemdelete.png")));
		button_6.setSelectedIcon(new ImageIcon(lvlEditor.class.getResource("/view/images/itemdelete.png")));
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current="itemdelete";
			}
		});
		button_6.setBounds(90, 67, 60, 60);
		tools.add(button_6);
		
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
				enemyList.clear();
				itemList.clear();
				startset=false;
				PlayingField pf = new PlayingField(1,null);
				pf.LoadLayer(Integer.valueOf(choiceLayer.getSelectedItem().replaceFirst("[.][^.]+$", "")),Integer.valueOf(choiceLevel.getSelectedItem()));
				Fieldarray=pf.getFieldarray();
				enemyList=pf.getEnemies();
				itemList=pf.getItemList();
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
					out.println("<layerdetail Height=\"600\" Width=\"600\" StartX=\""+startX+"\" StartY=\""+startY+"\"/>");
					
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
							case "models.Shop":
								out.println("<Block Type=\"shop\" X=\""+i+"\" Y=\""+j+"\"/>");
								break;
							default:
								out.println("<Block Type=\"floor\" X=\""+i+"\" Y=\""+j+"\"/>");
								break;	
							}
						}
						
						
					}  
					for (int k=0; k<itemList.size(); k++)
					{
						switch (itemList.get(k).getClass().getName())
						{
						case "models.Key":
							out.println("<Block Type=\"finish\" X=\""+(itemList.get(k).getPosX()/60)+"\" Y=\""+(itemList.get(k).getPosY()/60)+"\" Tlayer=\""+2+"\" Tlvl=\""+1+"\"/>");
							break;
						case "models.Shoes":
							out.println("<Block Type=\"shoes\" X=\""+(itemList.get(k).getPosX()/60)+"\" Y=\""+(itemList.get(k).getPosY()/60)+"\"/>");
							break;
						
						}
		
					}
					
					for (int h=0; h<enemyList.size(); h++)
					{
						switch (enemyList.get(h).getClass().getName())
						{
						case "models.KEnemy":
							out.println("<Block Type=\"enemy\" X=\""+(enemyList.get(h).getPosX()/60)+"\" Y=\""+(enemyList.get(h).getPosY()/60)+"\"/>");
							break;
						case "models.Shoes":
							out.println("<Block Type=\"boss\" X=\""+(enemyList.get(h).getPosX()/60)+"\" Y=\""+(enemyList.get(h).getPosY()/60)+"\"/>");
							break;
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
		btnNewButton_1.setBounds(689, 19, 89, 23);
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
			case "itemdelete":
				currentImage = ImageIO.read(new File("images/boden.png"));
				break;
			case "wall":
				currentImage = ImageIO.read(new File("images/Wand1.png"));
				break;
			case "start":
				if (!startset)
				{
				currentImage = ImageIO.read(new File("images/start.png"));
				startX=x/60;
				startY=y/60;
				}
				break;
			case "shoes":
				currentImage = ImageIO.read(new File("images/bonus.png"));
				Shoes shoes=new Shoes(null);
				itemList.add(shoes);
				shoes.setPos(x, y);
				break;
			case "key":
				currentImage = ImageIO.read(new File("images/finish.png"));
				Key key = new Key(null);
				itemList.add(key);
				key.setPos(x, y);
				break;
			case "enemy":
				currentImage = ImageIO.read(new File("images/Ludi2.png"));
				KEnemy ken = new KEnemy(null,null);
				enemyList.add(ken);
			    ken.setPos(x, y);
				break;
			case "boss":
				currentImage = ImageIO.read(new File("images/EnemyV.png"));
				Enemy boss = new Enemy(null,null);
				enemyList.add(boss);
			    boss.setPos(x, y);
				break;
			case "shop":
				currentImage = ImageIO.read(new File("images/shop.png"));
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
			case "start":
				startset=true;
				break;
			case "shop":
				Fieldarray[posX/60][posY/60]=new Shop(posX/60, posY/60);
				break;	
			case "itemdelete":
				for (int i=0; i<itemList.size(); i++)
				{
					
					
					if ( ((itemList.get(i).getPosX()<=(posX))&&((itemList.get(i).getPosX()+60)>=(posX) ))
							&&((itemList.get(i).getPosY()<=(posY))&&((itemList.get(i).getPosY()+60)>=(posY) )))
					{
						System.out.println(itemList.get(i).getPosX()+" und "+itemList.get(i).getPosY() );
						itemList.remove(i);
						
					}
				}
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

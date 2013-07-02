/*
 * The playingfield parses the xml .lvl files 
 * and writes the information in an array of Items.
 * The PlayingField is not more than an array of Items.
 * 
 */
package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import views.ViewKEnemy;

import controller.ControllerKenemy;
import controller.EnemyManager;

public class PlayingField extends Observable {

	private int nodeZahl;
	private int lvl, layer;
	private int height, width, startX, startY;
	private Block[][] field;
	private ArrayList<Item> itlist = new ArrayList<Item>();
	private ArrayList<Player> enList = new ArrayList<Player>();
	private boolean win;
	private MoveableObjects movObj;

	/*
	 * The PlayingField is initialized with a level which the user chooses in
	 * the menu
	 */

	public void removeItem(int index) {

		itlist.remove(index);

	}

	public void removeEnemy(int index) {

		enList.remove(index);

	}
	

	public PlayingField(int lvl, MoveableObjects movObj) {
		win = false;
		this.lvl = lvl;
		this.layer = 0;
		this.movObj = movObj;

		/*
		 * parsing won't work if the corresponding file does not exist thats why
		 * we catch errors here
		 */
		try {
			parse(lvl, layer);
		} catch (Exception e) {
			System.out
					.println("Layer or Level could not be loaded. Please check"
							+ "if the corresponding file exists. It is about Level:"
							+ lvl + "Layer:" + layer);
		}
		
	}
	public int getLevel()
	{
		return this.lvl;
	}
	/*
	 * This method reads xml files (.lvl in our case) into an array of the type
	 * Item[] []
	 */
	public void parse(int lvl, int layer)   {
	
		String name = "./level/" + String.valueOf(lvl) + "/"
				+ String.valueOf(layer) + ".lvl";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
        try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(new File(name));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			setChanged();
			win = true;
			this.notifyObservers();
		}

		/* Read height and width of layer */
		NodeList itemListe = document.getElementsByTagName("layerdetail");
		Element node = (Element) itemListe.item(0);
		height = Integer.parseInt(node.getAttribute("Height"));
		width = Integer.parseInt(node.getAttribute("Width"));

		/* Read start pos of Player */
		this.startX = Integer.parseInt(node.getAttribute("StartX"));
		this.startY = Integer.parseInt(node.getAttribute("StartY"));

		itemListe = document.getElementsByTagName("Block");
		nodeZahl = document.getElementsByTagName("Block").getLength();
		field = new Block[width / 60][height / 60];
		fieldInit();
		itlist.clear();
		int itemPosX;
		int itemPosY;

		for (int i = 0; i < nodeZahl; i++) {
			node = (Element) itemListe.item(i);
			itemPosX = Integer.parseInt(node.getAttribute("X"));
			itemPosY = Integer.parseInt(node.getAttribute("Y"));
			String type = node.getAttribute("Type");
			
			switch (type) {
			case "wall":
				field[itemPosX][itemPosY] = new Wall(itemPosX * 60,
						itemPosY * 60);
				break;
			case "finish":
				Key key= new Key(null);
				key.setPos(itemPosX * 60, itemPosY * 60);
				key.setTarget(Integer.parseInt(node.getAttribute("Tlayer")), Integer.parseInt(node.getAttribute("Tlvl")));
				itlist.add(key);
				break;
			case "shoes":
				Shoes shoes = new Shoes(null);
				shoes.setPos(itemPosX * 60, itemPosY * 60);
				itlist.add(shoes);
				break;
			case "healthpack":
				Healthpack hp = new Healthpack(null);
				hp.setPos(itemPosX * 60, itemPosY * 60);
				itlist.add(hp);
				break;
			case "trap":
				field[itemPosX][itemPosY] = new Trap(itemPosX * 60,
						itemPosY * 60);
				break;
			case "shop":
				field[itemPosX][itemPosY] = new Shop(itemPosX * 60,
					itemPosY * 60);
				break;
			case"firearmor":
				FireArmor fArmor= new FireArmor(null);
				fArmor.setPos(itemPosX*60,itemPosY*60);
				itlist.add(fArmor);
				
				break;
				
			case"icearmor":
				IceArmor iArmor= new IceArmor(null);
				iArmor.setPos(itemPosX*60, itemPosY*60);
				itlist.add(iArmor);
				break;
			case "fireenemy":
				KEnemy fen = new KEnemy(this, movObj);
				fen.setPos(itemPosX * 60, itemPosY * 60);
				fen.setEnTyp("fire");
				fen.setFireArmor(100);
				enList.add(fen);
				break;
				
			case "iceenemy":
				KEnemy ien = new KEnemy(this, movObj);
				ien.setPos(itemPosX * 60, itemPosY * 60);
				ien.setEnTyp("ice");
				ien.setIceArmor(100);
				enList.add(ien);
				break;
				
			case "enemy":
				KEnemy en = new KEnemy(this, movObj);
				en.setPos(itemPosX * 60, itemPosY * 60);
				en.setEnTyp("nix");
				en.setArmor(100);
				enList.add(en);
				break;
			case"cash":
				Cash cash= new Cash(null);
				cash.setPos(itemPosX*60,itemPosY*60);
				itlist.add(cash);
				break;
			case"balldmg":
				DmgBall dmgball= new DmgBall(null);
				dmgball.setPos(itemPosX*60,itemPosY*60);
				itlist.add(dmgball);
				break;
			case"rangeball":
				RangeBall rangeball= new RangeBall(null);
				rangeball.setPos(itemPosX*60,itemPosY*60);
				itlist.add(rangeball);
				break;
			case"manacosts":
				ManaCosts manacosts= new ManaCosts(null);
				manacosts.setPos(itemPosX*60, itemPosY*60);
				itlist.add(manacosts);
				break;
				
			case"laserdmg":
				DmgLaser laserdmg= new DmgLaser(null);
				laserdmg.setPos(itemPosX*60, itemPosY*60);
				itlist.add(laserdmg);
				break;
			case"laserrange":
				RangeLaser laserrange= new RangeLaser(null);
				laserrange.setPos(itemPosX*60, itemPosY*60);
				itlist.add(laserrange);
				break;
			case "boss":
				Enemy boss = new Enemy(this, movObj);
				boss.setPos(itemPosX * 60, itemPosY * 60);
				boss.setTarget(Integer.parseInt(node.getAttribute("TLayer")), Integer.parseInt(node.getAttribute("TLvl")));
				enList.add(boss);
				break;
				
			case "npc":
				Npc npc= new Npc(null);
				npc.setPos(itemPosX * 60, itemPosY * 60);
				itlist.add(npc);
				break;
			}

		}
	
	}

	/*public void setWallEdges()
	{
		for(int i=0; i<field.length; i++)
		{
			for (int j=0; j<field[0].length; j++)
			{
				if(field[i][j].getClass().getName().equals("models.Wall"))
				{
				 if((i-1>=0)&&(i+1<field.length))
				 {
					if(field[i-1][j].getClass().getName().equals("models.Wall"))
						field[i][j].setKind(field[i][j].getKind()+"hl");
					if(field[i+1][j].getClass().getName().equals("models.Wall"))
						field[i][j].setKind(field[i][j].getKind()+"hr");
					
				 }
				 
			

				}
			}
		}
	}*/
	public void updateField()
	{
		setChanged();
		this.notifyObservers();
	}

	public ArrayList<Item> getItemList() {
		return itlist;
	}

	public void fieldInit() {
		
		for (int i = 0; i < (width / 60); i++) {
			for (int j = 0; j < (height / 60); j++) {
				field[i][j] = new Floor(i * 60, j * 60);

			}
		}
	}

	/* is used to enter the next layer within a level */
	public void LoadLayer(int layer, int lvl) {
		setChanged();
		this.lvl=lvl;
		this.layer=layer;
		fieldInit();
		for(int i=0; i<enList.size();i++)
		{
			enList.get(i).remove();
			
			enList.set(i,null);
		}
		enList.clear();
		itlist.clear();
		try {
			parse(lvl, layer);
		} catch (Exception e) {
			setChanged();
			win = true;
			this.notifyObservers();
		}
		
		notifyObservers();
	}
	
	


	public ArrayList<Player> getEnemies() {
		return enList;
	}
	
	public void createEnemies()
	{
	
	}

	/* getters */
	public boolean getWin() {
		return win;
	}

	public int getLayer() {
		return layer;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public Block[][] getFieldarray() {
		return field;
	}

}

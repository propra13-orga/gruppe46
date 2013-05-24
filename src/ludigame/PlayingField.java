/*
 * The playingfield parses the xml .lvl files 
 * and writes the information in an array of Items.
 * The PlayingField is not more than an array of Items.
 * 
 */
package ludigame;

import java.io.File;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList;

public class PlayingField extends Observable {

    int nodeZahl;
    Item[] fieldarray;
    int lvl, layer;
    int height,width,startX,startY;
    boolean win;
    
    /* The PlayingField is initialized with a level which the user chooses 
     * in the menu
     */
    public PlayingField(int lvl) {
    	win=false;
        this.lvl = lvl;
        this.layer = 0;
    
        /* parsing won't work if the corresponding file does not exist 
         * thats why we catch errors here
         */
        try {
			parse(lvl,layer);
		} catch (Exception e) {
			System.out.println("Layer or Level could not be loaded. Please check" +
					"if the corresponding file exists. It is about Level:"+lvl+
					"Layer:"+layer);
		}
    }

    /* This method reads xml files (.lvl in our case) into an array */
    public void parse(int lvl, int layer) throws Exception {
        String name= "./level/"+String.valueOf(lvl)+"/"+String.valueOf(layer)+".lvl";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(name));
        
        /* Read height and width of layer*/
        NodeList itemListe = document.getElementsByTagName("layerdetail");
        Element node = (Element) itemListe.item(0);            
        height=Integer.parseInt(node.getAttribute("Height"));
        width=Integer.parseInt(node.getAttribute("Width"));
    
        /* Read start pos of Player */
        startX=Integer.parseInt(node.getAttribute("StartX"));
        startY=Integer.parseInt(node.getAttribute("StartY"));

        
        itemListe = document.getElementsByTagName("Block");//Das ist die Liste mit Item Pointern
        nodeZahl = document.getElementsByTagName("Block").getLength();
        fieldarray = new Item[nodeZahl];
        for (int i = 0; i < nodeZahl; i++) {
            node = (Element) itemListe.item(i);
            fieldarray[i]= new Item(Integer.parseInt(node.getAttribute("X"))*60, 
            		Integer.parseInt(node.getAttribute("Y"))*60, node.getAttribute("Type"));
        }
    }

    /* is used to enter the next layer within a level */
    public void increaseLayer() {
        layer++;
       
        try {
            parse(lvl, layer);
            setChanged();
            this.notifyObservers();
        } catch (Exception e) {
        	setChanged();
        	win=true;
            this.notifyObservers();
        }
    }
    
    /*getters*/
    public boolean getWin()
    {
    	return win;
    }
    public int getLayer()
    {
    	return layer;
    }
    
    public int getStartX()
    {
    	return startX;
    }
    public int getStartY()
    {
    	return startY;
    }
    
    public int getHeight()
    {
    	return this.height;
    }
    public int getWidth()
    {
    	return this.width;
    }

    public Item[] getFieldarray() {
        return fieldarray;
        
    }

    
}

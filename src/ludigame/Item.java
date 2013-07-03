/*
 *This is the main class for all blocks (we call them items) on the map
 *Every block is an item and will be extended by this class.
 */
package ludigame;
import java.util.Observable;

public class Item extends Observable {
	
   private int posX;
   private int posY;
   private String type;
   
   /* Every Item is initialized with a position and a type */ 
   public Item(int posX, int posY, String type)
   {
   this.posX=posX;
   this.posY=posY;
   this.type= type;
   }
   
   /* getters */
   public int getPosX() {
        return posX;
    }
   
   public int getPosY() {
        return this.posY;
    }
   
   
    /*setters*/
    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }
    
    String getType(){
    return this.type;
    }
    
    public void setType(String type){
    this.type=type;
    }
}

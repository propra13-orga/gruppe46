/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;
import java.util.Observable;
/**
 *
 * @author pixel
 */
public class Item extends Observable {
	
   private int posX;
   private int posY;
   private String type;
   
   public Item(int posX, int posY, String type)
   {
   this.posX=posX;
   this.posY=posY;
   this.type= type;
   }
   
   public int getPosX() {
        return posX;
    }

   public int getPosY() {
        return this.posY;
    }

   public void setPosX(int x) {
        this.posX = x;

    }

   public void setPosY(int y) {
        this.posY = y;
    }
    
    String getType(){
    return this.type;
    }
    
    public void setType(String type)
    {
    this.type=type;
    
    }
}

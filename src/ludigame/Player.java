/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;

import java.util.Observable;

/**
 *
 * @author Sergej
 */
public class Player extends Observable{
   private int posX;
   private int posY;
   private boolean alive;

   public Player (int posX, int posY)
   {
	    this.alive=true;
	    this.posX = posX;
	    this.posY = posY;
            
   }
   public void setPos (int posX, int posY)
   {
	   setChanged();
	   this.posX = posX;
       this.posY = posY;
       this.notifyObservers();
   }
   
   public void killplayer()
   {
	   setChanged();
	   alive=false;
	   this.notifyObservers();
   }
   public boolean getLifestatus()
   {
	   return alive;
   }
   
   public int getPosX ()
   {
       return this.posX;
   }
   
   public int getPosY ()
   {
       return this.posY;
   }
}


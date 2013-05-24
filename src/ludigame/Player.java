/*
 * Every Player which is either controlled by an user or
 * by an AI is extended by this class. The most important methods are included
 * here.
 * 
 * it is extended Observable because its updates are important for their views.
 * 
 */

package ludigame;

import java.util.Observable;

public class Player extends Observable{
   private int posX;
   private int posY;
   private boolean alive;

   /* Every Player is initialized with its position */
   public Player (int posX, int posY)
   {
	    this.alive=true;
	    this.posX = posX;
	    this.posY = posY;
            
   }
 
   /* changes the lifestatus of the player */
   public void killplayer()
   {
	   setChanged();
	   alive=false;
	   this.notifyObservers();
   }
   
   
   /* setters */ 
   public void setPos (int posX, int posY)
   {
	   setChanged();
	   this.posX = posX;
       this.posY = posY;
       this.notifyObservers();
   }
   
   /* getters */
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


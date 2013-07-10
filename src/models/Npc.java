package models;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Sounds.Sound;

public class Npc extends Item {

	public Npc(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void setAttributes()
	{
		
		
			
			if(owner.getSpeedX()==1)
				this.owner.setPos(owner.getPosX()-1, owner.getPosY());
			if(owner.getSpeedX()==-1)
				this.owner.setPos(owner.getPosX()+1, owner.getPosY());
			
			if(owner.getSpeedY()==1)
				this.owner.setPos(owner.getPosX(), owner.getPosY()-1);
			if(owner.getSpeedY()==-1)
				this.owner.setPos(owner.getPosX(), owner.getPosY()+1);

		//	this.owner.setPos(1,1);
			this.owner.setSpeedX(0);
			this.owner.setSpeedY(0);
			this.owner.dropItem(this.owner.getItems().size()-1);
			ImageIcon icon = new ImageIcon("images/npc.png");
			if(this.owner.getqFeuerball()<20)
			{
			JOptionPane.showMessageDialog(null,
				    "Schiesse 20 Feuerbaelle ab und du wirst belohnt!"+"\n"+"Abgeschossene Feuerbaelle:"+this.owner.getqFeuerball() ,
				    "THE NpcFuzzi",
				    JOptionPane.INFORMATION_MESSAGE,
				    icon);
			}else if(this.owner.getqFeuerball()>=20)
			{
				
					JOptionPane.showMessageDialog(null,
						    "Glueckwunsch!"+"\n"+"Falls du nochmal etwas Geld verdienen willst, komme wieder :) ",
						    "THE NpcFuzzi",
						    JOptionPane.INFORMATION_MESSAGE,
						    icon);
				this.owner.setqFeuerball(0);
				this.owner.setMoney(this.owner.getMoney()+10);
			}
	
			
		
	
	}

}

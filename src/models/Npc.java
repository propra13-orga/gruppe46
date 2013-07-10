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
			JOptionPane.showMessageDialog(null,
				    "Ey digger, dein Auftrag: Jeder dieser Geister hat nen eigenen Thread der" +
				    "erst gestoppt wird wenn der Geist tot ist... ruckelts? Du weisst also was zu tun ist... ",
				    "THE NpcFuzzi",
				    JOptionPane.INFORMATION_MESSAGE,
				    icon);
	
			
		
	
	}

}

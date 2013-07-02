package models;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Sounds.Sound;

public class Cash extends Item{

	private int value;
	public Cash(Spieler owner) {
		super(owner);
		this.value=-10;
		// TODO Auto-generated constructor stub
	}
	
	public void setValue(int value)
	{
		this.value=value;
	}
	
	public void setAttributes()
	{
		
		if(this.owner.checkAccount(this.value)==false)
		{
			Sound money=new Sound();
			money.play("sounds/yourmam.wav");
			if(owner.getSpeedX()==1)
				this.owner.setPos(owner.getPosX()-1, owner.getPosY());
			if(owner.getSpeedX()==-1)
				this.owner.setPos(owner.getPosX()+1, owner.getPosY());
			
			if(owner.getSpeedY()==1)
				this.owner.setPos(owner.getPosX(), owner.getPosY()-1);
			if(owner.getSpeedY()==-1)
				this.owner.setPos(owner.getPosX(), owner.getPosY()+1);

		
			this.owner.setSpeedX(0);
			this.owner.setSpeedY(0);
			this.owner.dropItem(this.owner.getItems().size()-1);
			ImageIcon icon = new ImageIcon("images/dollar2.png");
			JOptionPane.showMessageDialog(null,
				    "You need more money, honey! Go kill some Ghosts!",
				    "THE SHOPMASTER",
				    JOptionPane.INFORMATION_MESSAGE,
				    icon);
	
			
		} else 
			{
			owner.pay(this.value);
			Sound money=new Sound();
			money.play("sounds/money.wav");
			}
	
	}
	

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;


import javax.swing.*;

/**
 *
 * @author Sebastian
 */
public class Menue extends JFrame implements ActionListener, Observer{
		private int height;
		private int width;
		private int percentH;
        private int percentW;
        private JButton Starten;
		private JButton Einstellungen;
		private JButton Credits;
		private JButton Ende;
        private JButton na;
        private JButton back;
        private JButton Level1;
        private JButton Level2;
        private JButton Level3;
        private JFrame Levelfenster;
        private JFrame Einstellungsfenster;
        private JFrame Creditsfenster;
        private Game game;
                
public Menue (String titel, int height, int width)
{
        super(titel);
        this.height=height;
        this.width=width;
        this.percentH=10;
        this.percentW=40;
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo (null);
        this.setVisible(true);

        setLayout(null);
        
        Starten = new JButton("Spiel starten");
        Starten.setBounds(width/4-this.getInsets().left,(height/12)*2,width/75*percentW,height/120*percentH );
        add (Starten);
        Starten.addActionListener(this);
        
        Einstellungen = new JButton("Einstellungen");
        Einstellungen.setBounds(width/4-this.getInsets().left,(height/12)*4,width/75*percentW,height/120*percentH );
        add (Einstellungen);
        Einstellungen.addActionListener(this);
        
        Credits = new JButton("Credits");
        Credits.setBounds(width/4-this.getInsets().left,(height/12)*6,width/75*percentW,height/120*percentH );
        add (Credits);
        Credits.addActionListener(this);            
        
        Ende = new JButton("Ende");
        Ende.setBounds(width/4-this.getInsets().left,(height/12)*8,width/75*percentW,height/120*percentH );
        add (Ende);
        Ende.addActionListener(this);
        
        back = new JButton ("Zurück zum Hauptmenü");
        back.setBounds(width/4-this.getInsets().left,(height/12)*8,width/75*percentW,height/120*percentH );
        back.addActionListener(this);

}

@Override
public void actionPerformed(ActionEvent e) 
{  
    if(e.getSource() == Starten)
    { 
    	
        this.setVisible(false);
        
        Levelfenster = new JFrame("Levelauswahl");
        Levelfenster.setSize(600,600);
        Levelfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Levelfenster.setLocationRelativeTo(null);
        Levelfenster.setVisible(true);
        Levelfenster.setLayout(null);
        
        File lvl0 = new File ("level/1");
        
        if (lvl0.exists())
        { 
        Level1 = new JButton ("Level 1");   
        Level1.setBounds(width/4-this.getInsets().left,(height/12)*2,width/75*percentW,height/120*percentH );
        Levelfenster.add(Level1);
        Level1.addActionListener(this);
        }
        
        File lvl1 = new File ("level/2");
        
        if (lvl1.exists())
        {
        Level2 = new JButton ("Level 2");
        Level2.setBounds(width/4-this.getInsets().left,(height/12)*4,width/75*percentW,height/120*percentH );
        Levelfenster.add(Level2);
        Level2.addActionListener(this);   
        }
    
        File lvl2 = new File ("level/3");
        
        if (lvl2.exists())
        {
        Level3 = new JButton ("Level 3");
        Level3.setBounds(width/4-this.getInsets().left,(height/12)*6,width/75*percentW,height/120*percentH );
        Levelfenster.add(Level3);
        Level3.addActionListener(this);
        }
 
       
        
        Levelfenster.add(back);
       
        
    }
    
    if(e.getSource() == Einstellungen)
    { 
        this.setVisible(false);
        
     
        Einstellungsfenster = new JFrame("Einstellungen");
        Einstellungsfenster.setSize(600,600);
        Einstellungsfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Einstellungsfenster.setLocationRelativeTo(null);
        Einstellungsfenster.setVisible(true);
        Einstellungsfenster.setLayout(null);
        
        na = new JButton ("Zurzeit nicht verfügbar");
        na.setBounds(width/4-this.getInsets().left,(height/12)*4,width/75*percentW,height/120*percentH );
        Einstellungsfenster.add(na);
        na.addActionListener(this);
        
      
        Einstellungsfenster.add(back);
       
    }
    
    if(e.getSource() == Credits)
    {  
        this.setVisible(false);   
        
        Creditsfenster = new JFrame("Credits");        
        Creditsfenster.setSize(600,600);
        Creditsfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Creditsfenster.setLocationRelativeTo(null);       
        Creditsfenster.setVisible(true);
        Creditsfenster.setLayout (null);
        
        JLabel label1 = new JLabel();
        label1.setText("NICHTS");
        Creditsfenster.add(label1);
  
        Creditsfenster.add(back);
     
     
    }
      
    if(e.getSource() == Ende)
    { 
    	System.exit(0);
    }
     // WE WILL CHANGE THIS SHIT. 
    if(e.getSource() == back)
    {  
    	this.setVisible(true);
        try {
        	Levelfenster.setVisible(false);
        } catch (Exception es) {
        
        }
        try {
        	Einstellungsfenster.setVisible(false);
        } catch (Exception es) {
   
        }

        try {
        	Creditsfenster.setVisible(false);
        } catch (Exception es) {
        	
        }
     
        

        
    }
   
    if(e.getSource() == Level1)
    { 
    	Levelfenster.setVisible(false);
         game = new Game(1);
         game.addObserver(this);
    }
    
    if(e.getSource() == Level2)
    { 
        Levelfenster.setVisible(false);
    	 game = new Game(2);   
    	 game.addObserver(this);
    }
    
    if(e.getSource() == Level3)
    { 
    	Levelfenster.setVisible(false);
         game = new Game(3);
         game.addObserver(this);
    }
    
                    


}

@Override
public void update(Observable arg0, Object arg1) {
	// TODO Auto-generated method stub
    System.out.println("Ich war hier für nippes");
	if (game.win()==false)
	{
		this.setVisible(true);
		ImageIcon icon = new ImageIcon("./images/playerDead.png");
        JOptionPane.showMessageDialog(null, "Du bist zu Staub zerfallen!", "Verloren", JOptionPane.INFORMATION_MESSAGE, icon);
	}
	if (game.win()==true)
	{
		this.setVisible(true);
		ImageIcon icon2 = new ImageIcon("./images/playerHappy.png");
        JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch. Die Prinzessin kommt später.", "Gewonnen", JOptionPane.INFORMATION_MESSAGE, icon2);
	}
}

}

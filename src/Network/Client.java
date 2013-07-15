package Network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import javax.swing.JOptionPane;

import controller.ControllerSpieler2;

import views.ViewEnemy;
import views.ViewGame;



import ludigame.Game;
import models.Enemy;
import models.Key;

public class Client extends Observable {

	private Socket server;
	private Thread t;
	private ActionListener al;
	private ViewGame vgame;
	private String pressedKey, keycode;
	private ControllerSpieler2 csp2;
	private BufferedReader in;
	private PrintWriter out;
	private Enemy en;
	private Game game;
	private ClientReader cr;
	private ClientWriter cw;
	int zu;
	boolean enAlive;
	boolean meAlive;
	
	public Client(String ip, ActionListener al, ViewGame vgame)
	{
		this.vgame=vgame;
		this.al=al;
		pressedKey="";
		connect(ip);
		cw=new ClientWriter();
		cr=new ClientReader();
		zu=1;
		enAlive=true;
		meAlive=true;
	}

	public void connect(String ip)
	{
		try {
			server = new Socket(ip, 4444);
			ActionEvent event = new ActionEvent(server,0,"GAME");
			al.actionPerformed(event);
			game = new Game(3, vgame);
			game.getSpieler().setLifes(0);
			this.addObserver(game);
			en = new Enemy (game.getPlayingField(), game.getMovableObjects());
			en.setHealthpoints(100);
			
			en.setTarget(1, 1);
			game.setvEn(en);
			en.setPos(5, 5);
			csp2 = new ControllerSpieler2(en);
			 out = new PrintWriter(server.getOutputStream(),
					true);
			in= new BufferedReader(
					new InputStreamReader(server.getInputStream())
					);
		// out = 
		
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	public class ClientReader implements Runnable
	{
		private String pressedKey;
		private Thread t2;
		public ClientReader()
		{
			t2 = new Thread(this);
			t2.start();
		}
		
		
		public String getPressedKey()
		{
			return pressedKey;
		}
		@Override
		public void run() {
			while (meAlive==true){
			try {
				pressedKey=in.readLine();
				if(pressedKey.equals("lost"))
				{
					
					game.getSpieler().setLifestatus(false);
					System.out.println("Client: meAlive="+meAlive);
					meAlive=false;
				}
			} catch (IOException e) {
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				zu=0;
				
				
				//e.printStackTrace();
			}
		
			if(pressedKey!=null)
				csp2.keyIsPressed(pressedKey);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
		
			 
	}
	
	}
	
	public class ClientWriter implements Runnable
	{
		private Thread t3;
		public ClientWriter()
		{
			t3=new Thread(this);
			t3.start();
		}
		
		@Override
		public void run() {
			while (enAlive==true) {
				  if(en.getLifestatus()==false)
				  {
					  enAlive=false;
					  System.out.println("Client: enAlive auf false gesetzt");
					  out.println("lost");
				  }
				
					if (game.getSpieler().getSpeedX() !=0) {
					out.println("x."+String.valueOf(game.getSpieler().getPosX()));
						
					
					}
					if (game.getSpieler().getSpeedY()!=0)
					{
						out.println("y."+String.valueOf(game.getSpieler().getPosY()));
						
					}
					if(game.getSpieler().isBallerzeugt()==true)
					{
						
						out.println("feuerball");
					game.getSpieler().setBallerzeugt(false);
					} else if(game.getSpieler().isEisballerzeugt()==true)
					{
						out.println("eisball");
						game.getSpieler().setEisballerzeugt(false);
					} else if(game.getSpieler().isLasererzeugt()==true)
					{
						out.println("laser");
						game.getSpieler().setLasererzeugt(false);
						
					}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//this.listen();
				}
			
	

		}}
		
	


	


	
	
}


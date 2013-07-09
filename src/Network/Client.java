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

import controller.ControllerSpieler2;

import views.ViewEnemy;
import views.ViewGame;



import ludigame.Game;
import models.Enemy;

public class Client {

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
	
	public Client(String ip, ActionListener al, ViewGame vgame)
	{
		this.vgame=vgame;
		this.al=al;
		pressedKey="";
		connect(ip);
		cw=new ClientWriter();
		cr=new ClientReader();
	}

	public void connect(String ip)
	{
		try {
			server = new Socket(ip, 4444);
			ActionEvent event = new ActionEvent(server,0,"GAME");
			al.actionPerformed(event);
			game = new Game(3, vgame);
			en = new Enemy (game.getPlayingField(), game.getMovableObjects());
			en.setHealthpoints(100);
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
			while (true){
			try {
				pressedKey=in.readLine();
				System.out.println(pressedKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(pressedKey!=null)
				csp2.keyIsPressed(pressedKey);
			
			try {
				Thread.sleep(5);
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
			while (true) {
				  
				
					if (game.getSpieler().getSpeedX() !=0) {
					out.println("x."+String.valueOf(game.getSpieler().getPosX()));
						if (out.checkError()==true)
						System.out.println("bin im thread");
					
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
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//this.listen();
				}

		}}
		
	


	


	
	
}


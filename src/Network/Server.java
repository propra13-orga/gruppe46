package Network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

import org.omg.CORBA.portable.OutputStream;

import controller.ControllerSpieler2;

import Sounds.Sound;

import views.ViewEnemy;
import views.ViewGame;
import views.ViewSpieler;

import ludigame.Game;
import models.Enemy;
import models.Key;
import models.MoveableObjects;
import models.Player;
import models.PlayingField;
import models.Spieler;

public class Server {

	private ServerSocket srvSock;
	private Socket clientSock;
	private ViewGame vgame;
	private ActionListener al;
	
	private String key;
	ControllerSpieler2 csp2;
	private Game game;
	PrintWriter out;
	BufferedReader in;
	Thread listenThread;
    Thread writeThread;
    Enemy en;
    int zu;
    boolean enAlive;
    boolean meAlive;
	public Server(ViewGame vgame, ActionListener al) {
		
		this.vgame = vgame;
		this.al = al;
		
		this.startServer();
		

	}

	public void write(String keyCode) {

	
		out.println(keyCode);
		

	}

	public void listen() {
	
		try {
			
			
			key=in.readLine();
			if(key.equals("lost"))
			{
				
				this.game.getSpieler().setLifestatus(false);
				System.out.println("Server: meAlive="+this.meAlive);
				this.meAlive=false;
			}
			
			csp2.keyIsPressed(key);
			
			
		} catch (IOException e) {
			
			zu=0;
			//e.printStackTrace();
		}
	}

	public void startServer() {
		try {
			srvSock = new ServerSocket(4444);
			srvSock.setSoTimeout(10000);

			clientSock = srvSock.accept();
			

			ActionEvent event = new ActionEvent(srvSock, 0, "GAME");
			al.actionPerformed(event);
			game = new Game(3, vgame);
			game.getSpieler().setLifes(0);
			 en = new Enemy(game.getPlayingField(),
					game.getMovableObjects());
			
			game.setvEn(en);
			en.setPos(5, 5);
			csp2 = new ControllerSpieler2(en);
				out = new PrintWriter(clientSock.getOutputStream(),
					true);
				in = new BufferedReader(new InputStreamReader(
						clientSock.getInputStream()));
				zu=1;
				enAlive=true;
				meAlive=true;
				writeThread= new WriteThread();
			writeThread.start();
			
			
			listenThread = new ListenThread();
			listenThread.start();
			
		} catch (IOException e) {
			try {
				srvSock.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Could not listen on port: 111");
			
		}

	}

	public class ListenThread extends Thread {
	
		
		@Override
		public void run()
		{
			while (meAlive==true)
			{
				listen();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
	}
	public class WriteThread extends Thread{
	
	
	
	@Override
		public void run() {

		while (enAlive==true) {
		  if(en.getLifestatus()==false)
		  {
			  out.println("lost");
			  enAlive=false;
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


	}

	}
}

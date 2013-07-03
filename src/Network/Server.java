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
import models.MoveableObjects;
import models.Player;
import models.PlayingField;
import models.Spieler;

public class Server implements KeyListener, Runnable {

	private ServerSocket srvSock;
	private Socket clientSock;
	private ViewGame vgame;
	private ActionListener al;
	private Thread t;
	private String key;
	ControllerSpieler2 csp2;
	public Server(ViewGame vgame, ActionListener al)
	{
		
		this.vgame=vgame;
		this.al=al;
		
		t= new Thread(this);
		startServer();
		
		
	}
	
	public void write(String keyCode)
	{
		try {
			PrintWriter out= new PrintWriter(clientSock.getOutputStream(), true);
			//System.out.println("geschrieben");
			out.write(keyCode);
			out.flush();
			//out.close();
			//clientSock.close();
			//srvSock.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void listen()
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			key=in.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startServer()
	{		
			try {
				srvSock = new ServerSocket(4444);
				srvSock.setSoTimeout(10000);
				
				clientSock= srvSock.accept();
				
				ActionEvent event = new ActionEvent(srvSock,0,"GAME");
				al.actionPerformed(event);
				Game game = new Game(3, vgame);
				Enemy en = new Enemy (game.getPlayingField(), game.getMovableObjects());
				System.out.println("war hier");
				game.setvEn(en);
				en.setPos(5, 5);
				csp2 = new ControllerSpieler2(en);
				
				t.start();
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
	@Override
	public void run()
	{
	
			//this.listen();
			csp2.keyIsPressed("p39");
			write("p39");
			
			//System.out.println("thread im server");
		/*	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		System.out.println("test");
		if (e.getKeyCode() == 39) {
			write("39");
		}
		if (e.getKeyCode() == 37) {
			write("37");
		}
		if (e.getKeyCode() == 38) {
			write("38");
		}
		if (e.getKeyCode() == 40) {
			write("40");
		}
		if (e.getKeyCode() == 32) {
			
			write("32");
		}
		
        if (e.getKeyCode() == 87) {
			
        	write("87");
		}
		if (e.getKeyCode() == 81)// 81=q
		{
			

			write("81");

		}
		
	}

	/*
	 * following stuff is integrated because it is a must if you implement the
	 * KeyListener
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == 39) || (e.getKeyCode() == 37)) {
			write("r39orr37");
		}
		if ((e.getKeyCode() == 40) || (e.getKeyCode() == 38)) {
			write("r40orr38");
		}
		if (e.getKeyCode() == 32 || e.getKeyCode()==87) {
			write("r32orr87");
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}

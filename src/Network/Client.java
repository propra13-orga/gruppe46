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

public class Client implements Runnable, KeyListener{

	private Socket server;
	private Thread t;
	private ActionListener al;
	private ViewGame vgame;
	private String pressedKey;
	private ControllerSpieler2 csp2;
	
	public Client(String ip, ActionListener al, ViewGame vgame)
	{
		this.vgame=vgame;
		t=new Thread(this);
		this.al=al;
		pressedKey="";
		
		connect(ip);
		


	}

	public void connect(String ip)
	{
		try {
			server = new Socket(ip, 4444);
			ActionEvent event = new ActionEvent(server,0,"GAME");
			al.actionPerformed(event);
			Game game = new Game(3, vgame);
			Enemy en = new Enemy (game.getPlayingField(), game.getMovableObjects());
			
			game.setvEn(en);
			en.setPos(5, 5);
			csp2 = new ControllerSpieler2(en);
		
			t.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen()
	{
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(server.getInputStream())
					);
			
			pressedKey=in.readLine();
			
			csp2.keyIsPressed(pressedKey);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write()
	{
		try {
			PrintWriter out = new PrintWriter(server.getOutputStream(), true);
			out.println("write");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("thread");	
		listen();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		pressedKey="p"+Integer.toString(arg0.getKeyCode());
		write();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		pressedKey="r"+Integer.toString(arg0.getKeyCode());
		write();
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}


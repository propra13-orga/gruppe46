package models;

import java.util.Observable;

public class Updater extends Observable implements Runnable {

	Thread t;
	public Updater()
	{
		 t=new Thread(this);
		t.start();
	}
	@Override
	public void run() {
	while(true)
	{
		setChanged();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.notifyObservers();
	}
	}

}

package server;

import java.net.Socket;
import java.util.Scanner;

public class ServerMessage extends Thread
{
	private ServerConnection sc;
	private boolean running = true;
	private Scanner serverinput;
	
	public ServerMessage(ServerConnection sc)
	{
		this.sc = sc;
	}
	public void run()
	{
		
		getinput();
	}
	public void getinput()
	{
		
		serverinput = new Scanner(System.in);
		while(running)
		{
			//System.out.println("running");
			while(!serverinput.hasNextLine()) 
			{
				try
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			
			String toclient = serverinput.nextLine();
			if (toclient.toLowerCase().equals("quit"))
			{
				serverinput.close();
				sc.close();
				break;
			}
			//System.out.println("sending: "+toclient);
			sc.sendtoallclient("Server: "+toclient);
		}
	}
}

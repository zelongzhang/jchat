package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class ClientSend implements Runnable,Serializable{

	private Client c;
	private PrintWriter out;
	private ObjectOutputStream senduserinfo;
	private boolean running = true;
	
	public ClientSend(Client c)
	{
		this.c = c;
		String [] userinfo = {c.getUsername(),c.getUserpw()};
		try 
		{
			this.out = new PrintWriter(new OutputStreamWriter(c.getCsocket().getOutputStream()));
			this.senduserinfo = new ObjectOutputStream(c.getCsocket().getOutputStream());
			senduserinfo.writeObject(userinfo);
			senduserinfo.flush();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() 
	{
		while(running)
		{
			sendtoserver(getinput());
		}
	}

	private void sendtoserver(String toserver) 
	{
		out.println(toserver);
		out.flush();
	}

	private String getinput() 
	{
		Scanner input = new Scanner(System.in);
		while(!input.hasNextLine())
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
		String toserver = input.nextLine();
		if (toserver.toLowerCase().equals("quit"))
		{
			c.close();
		}
		else
		{
			return toserver;
		}
		return null;
		
	}

}

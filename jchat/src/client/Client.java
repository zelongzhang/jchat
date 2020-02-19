package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client 
{
	private ClientConnection cc;
	private boolean running = true;
	private Scanner userinput;

	public static void main(String[] args) 
	{
		new Client();
	}
	
	public Client()
	{
		userinput = new Scanner(System.in);
		try 
		{
			Socket s = new Socket("localhost",8008);
			System.out.print("Enter your display name: ");
			cc = new ClientConnection(s,this,userinput.nextLine());
			cc.start();
			getinput();
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void getinput()
	{
		
		while(running)
		{
			//System.out.print("Enter your message: ");
			while(!userinput.hasNextLine()) 
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
			String toserver = userinput.nextLine();
			if (toserver.toLowerCase().equals("quit"))
			{
				userinput.close();
				cc.close();
				break;
			}
			cc.sendtoserver(toserver);
		}
		cc.close();
	}

}

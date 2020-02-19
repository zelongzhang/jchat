package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection extends Thread
{
	private Socket socket;
	private Client client;
	private BufferedReader in;
	private PrintWriter out;
	private String username;
	private boolean running= true;
	
	public ClientConnection(Socket s, Client client,String username)
	{
		this.socket =s;
		this.client = client;
		this.username = username;
	}
	
	public void run()
	{
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.println(username+" has joined the room.");
			out.flush();
			while (running)
			{
				while (!in.ready())
				{
					Thread.sleep(1);
				}
				
				String fromserver = in.readLine();
				System.out.println(fromserver);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			close();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			close();
		}
	}
	
	public void sendtoserver(String data)
	{
		out.println(username+": "+data);
		out.flush();
	}
	public void close()
	{
		try 
		{
			in.close();
			out.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}

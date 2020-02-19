package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListen implements Runnable{
	
	private Client c;
	private BufferedReader in;
	private boolean running = true;
	
	public ClientListen(Client c)
	{
		this.c = c;
		try 
		{
			in = new BufferedReader(new InputStreamReader(c.getCsocket().getInputStream()));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void run()
	{
		while (running)
		{
			listen();
		}
	}
	private void listen() 
	{
		try 
		{
			while (!in.ready())
			{
				Thread.sleep(1);
			}
			String fromserver = in.readLine();
			System.out.println(fromserver);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}

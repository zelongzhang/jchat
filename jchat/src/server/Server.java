package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
	private ServerSocket ss;
	private ServerConnection sc;
	private ServerMessage sm;
	private boolean running = true;
	public ArrayList<ServerConnection> connections= new ArrayList<ServerConnection>();

	public static void main(String[] args) 
	{
		
		new Server();
	}

	
	public Server()
	{
		try 
		{
			ss = new ServerSocket(9889);
			
			while (running)
			{
				
				Socket s = ss.accept();
				System.out.println("connection accepted");
				sc = new ServerConnection(s,this);
				connections.add(sc);
				sc.start();
				sm = new ServerMessage(sc);
				sm.start();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	

}

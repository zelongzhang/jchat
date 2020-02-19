package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection extends Thread
{
	Socket socket;
	Server server;
	BufferedReader in;
	PrintWriter out;
	boolean running = true;
	
	
	public ServerConnection(Socket s, Server server)
	{
		super("ScThread");
		this.socket = s;
		this.server = server;
	}
	
	public void sendtoclient(String data)
	{
		//System.out.println("sending: "+data);
		out.println(data);
		out.flush();
	}
	
	public void sendtoallclient(String data)
	{
		for (int i = 0 ; i<server.connections.size();i++)
		{
			server.connections.get(i).sendtoclient(data);
		}
	}
	
	public void run()
	{
		//System.out.println("serverthreadstarted");
		try 
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(running)
			{
				while(!in.ready())
				{
					Thread.sleep(1);
				}
				//System.out.println("serverinready");
				String datain = in.readLine();
				if (datain!=null)
					sendtoallclient(datain);
			}
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

package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private Socket csocket=null;
	private Thread listenthread;
	private Thread sendthread;
	private String userpw;
	private String username;
	

	public static void main(String[] args) 
	{
		new Client();
	}
	
	public Client()
	{
		Scanner userinput = new Scanner(System.in);
		System.out.print("enter your display name: ");
		this.username = userinput.nextLine();
		this.userpw= "password";
		
		try {
			this.csocket = new Socket("localhost",8008);
			listenthread = new Thread(new ClientListen(this));
			sendthread = new Thread(new ClientSend(this));
			listenthread.start();
			sendthread.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try 
		{
			csocket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Socket getCsocket() {
		return csocket;
	}

	public void setCsocket(Socket csocket) {
		this.csocket = csocket;
	}
	public Thread getListenthread() {
		return listenthread;
	}

	public void setListenthread(Thread listenthread) {
		this.listenthread = listenthread;
	}

	public Thread getSendthread() {
		return sendthread;
	}

	public void setSendthread(Thread sendthread) {
		this.sendthread = sendthread;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

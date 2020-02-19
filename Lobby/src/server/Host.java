package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Host 
{
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<User> users = new ArrayList<User>();
	private Thread hostecho;
	private Thread hostsend;
	private Socket socket;
	private ObjectInputStream userinfoin;
	private boolean running = true;
	private int userid=0;
	private int roomid=0;
	
	public static void main(String[] args)
	{
		new Host();
	}
	public Host()
	{
		try 
		{
			ServerSocket ss = new ServerSocket(8008);
			while(running)
			{
				socket = ss.accept();
				System.out.println("connection made");
				userinfoin = new ObjectInputStream(socket.getInputStream());
				String[] userinfo = (String [])userinfoin.readObject();
				User user = new User(userinfo[0],userinfo[1],userid,socket);
				userid++;
				users.add(user);
				System.out.println(user);
				hostecho = new Thread(new HostEcho(this,user));
				hostecho.start();
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	public Thread getHostsend() {
		return hostsend;
	}
	public void setHostsend(Thread hostsend) {
		this.hostsend = hostsend;
	}
	
}

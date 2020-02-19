package server;

import java.net.Socket;

public class User 
{
	private String username, userpw;
	private int userid;
	private Socket socket;
	private boolean inRoom = false;
	private int roomid = -1;
	private HostEcho hostecho;
	private HostSend hostsend;

	public User(String username, String userpw, int userid, Socket socket) 
	{
		this.username = username;
		this.userpw = userpw;
		this.userid = userid;
		this.socket = socket;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isInRoom() {
		return inRoom;
	}

	public void setInRoom(boolean inRoom) {
		this.inRoom = inRoom;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomId) {
		this.roomid = roomId;
	}

	public HostEcho getHostecho() {
		return hostecho;
	}

	public void setHostecho(HostEcho hostEcho) {
		this.hostecho = hostEcho;
	}

	public HostSend getHostsend() {
		return hostsend;
	}

	public void setHostsend(HostSend hostsend) {
		this.hostsend = hostsend;
	}
	

}

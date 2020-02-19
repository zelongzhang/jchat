package server;

import java.util.ArrayList;

public class Room 
{
	private ArrayList<User> usersinroom;
	private int roomid;
	private int capacity;
	private String description;
	
	public Room(int roomid,User user)
	{
		this.roomid=roomid;
		usersinroom=new ArrayList<User>();
		addUser(user);
		System.out.println("room "+roomid+ " was made");
	}

	public void addUser(User user)
	{
		user.setRoomid(roomid);
		user.setInRoom(true);
		usersinroom.add(user);
		System.out.println("User "+user.getUsername()+" joined room "+roomid);
		
	}
	public void removeUser(User user)
	{
		user.setRoomid(-1);
		for (int i =0; i<usersinroom.size();i++)
		{
			if(usersinroom.get(i).getUserid()==user.getUserid())
			{
				 usersinroom.remove(i);
				 user.setInRoom(false);
				 break;
			}
		}
	}

	
	public ArrayList<User> getUsersinroom() {
		return usersinroom;
	}

	public void setUsersinroom(ArrayList<User> usersinroom) {
		this.usersinroom = usersinroom;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}

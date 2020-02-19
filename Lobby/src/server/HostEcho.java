package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HostEcho implements Runnable{
	
	private Host h;
	private BufferedReader in;
	private PrintWriter out;
	
	private boolean running = true;
	private User user;
	
	public HostEcho(Host h, User user)
	{
		this.h = h;
		this.user = user;
		user.setHostecho(this);
		try
		{
			in = new BufferedReader(new InputStreamReader(h.getSocket().getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(h.getSocket().getOutputStream()));
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
			if(!user.isInRoom())
			{
				String fromclient = listen();
				switch(fromclient)
				{
				case "1":
				{
					System.out.println("making new room");
					Room room = new Room(h.getRoomid(),user);
					h.getRooms().add(room);
					h.setRoomid(h.getRoomid()+1);
					break;
				}
				case "2":
					{
						int rid= Integer.parseInt(listen());
						System.out.println("trying to join room "+rid);
						for (int i = 0; i<h.getRooms().size();i++)
						{
							if (h.getRooms().get(i).getRoomid()==rid)
							{
								System.out.println("found room to join "+rid);
								h.getRooms().get(i).addUser(user);

								break;
							}
						}
						break;
						
					}
				}
			}
			else
			{
				System.out.println(user.getUsername()+" already in room");
				String fromclient = listen();
				switch(fromclient)
				{
				case "3":
				{
					for (int i = 0; i<h.getRooms().size();i++)
					{
						if (h.getRooms().get(i).getRoomid()==user.getRoomid())
						{
							System.out.println("found room to join "+user.getRoomid());
							h.getRooms().get(i).removeUser(user);
							break;
						}
					}
				}
				default:
					sendtoroom(user.getRoomid(),fromclient);
				}
			}

		}
	}
	private String listen() 
	{
		try
		{
			String fromclient = in.readLine();
			while (fromclient==null)
			{
				Thread.sleep(1);
			}
			
			return fromclient;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	private void sendtoroom(int roomid,String toroom)
	{
		System.out.println("sending "+toroom+" to roomid: "+roomid);
		for(int i=0;i<h.getRooms().size();i++)
		{
			if(h.getRooms().get(i).getRoomid()==roomid)
			{
				System.out.println("found room "+roomid);
				Room room = h.getRooms().get(i);
				for(int j=0; j<room.getUsersinroom().size();j++)
				{
					PrintWriter out = room.getUsersinroom().get(j).getHostecho().getOut();
					out.println(toroom);
					out.flush();
				}
				break;
			}
		}
	}
	
	
	public PrintWriter getOut() {
		return out;
	}

	
	

}

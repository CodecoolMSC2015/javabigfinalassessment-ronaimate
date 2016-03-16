package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import dataParser.CSVDataReader;
import dataParser.DataReader;
import enums.SearchType;

public class PersonStoreServerSocket
{
	DataReader store;
	static SearchType searchType;

	public void start()
	{
		try
		{
			DataReader store = new CSVDataReader("Documentation\\persons.csv");
			System.out.println("Server Started.");
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Waiting for clients...");
			Socket socket = serverSocket.accept();
			System.out.println("Client connected.");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			while (true)
			{
				if (ois.read() > -1)
				{
					Object readOis;
					try
					{
						readOis = ois.readObject();
						if (readOis instanceof SearchType && readOis == SearchType.OPTIONAL)
						{
							searchType = SearchType.OPTIONAL;
						} else if (readOis instanceof SearchType && readOis == SearchType.MANDATORY)
						{
							searchType = SearchType.MANDATORY;
						} else if (searchType == SearchType.OPTIONAL)
						{
							store.setSearchType(searchType.OPTIONAL);
							store.setSearchCriteria((String) readOis);
							oos.writeObject(store.getPersons());
							break;

						} else if (searchType == SearchType.MANDATORY)
						{
							store.setSearchType(searchType.MANDATORY);
						} else if (readOis.equals("Exit"))
						{
							break;
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			oos.close();
			ois.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		PersonStoreServerSocket ps = new PersonStoreServerSocket();
		ps.start();
	}
}

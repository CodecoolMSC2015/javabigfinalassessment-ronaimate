package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.SearchType;
import person.Person;

public class Client extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		String criteria = request.getParameter("searchCriteria");
		SearchType type;
		if (request.getParameter("searchType").equals("OPTIONAL"))
		{
			type = SearchType.OPTIONAL;
		} else
		{
			type = SearchType.MANDATORY;
		}
		try
		{

			System.out.println("Client Started.");
			Socket socket = new Socket("localhost", 8000);
			System.out.println("Client Connecing to Server.");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			while (true)
			{
				oos.write(1);
				oos.writeObject(type);
				oos.write(1);
				oos.writeObject(criteria);
				oos.write(1);
				break;
			}
			HashSet<Person> persons = (HashSet<Person>) ois.readObject();
			for (Person person : persons)
			{
				pw.println(person);
			}
			oos.close();
			ois.close();
			socket.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

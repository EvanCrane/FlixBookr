package main.java;

import java.time.LocalDateTime;
import main.server.DBConnector;

//Logout Controller Class
public class LogoutController implements Controller
{
	private DBConnector connector;
	private User user;
	
	public LogoutController(User user)
	{
		connector = new DBConnector();
		this.user = user;
	}

	
	public void confirm()
	{
		LocalDateTime a = LocalDateTime.now();
		connector.saveEvent(user, a, "Logout");
	}
	
	public void OpenHomeScreen(boolean isAdmin) {
	
	}
}
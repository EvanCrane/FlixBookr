import main;
import java.time.LocalDateTime;
import main.client.LogoutForm;
import main.java.User;
import main.server

//Logout Conroller Class
public class LogoutController implements Controller
{
	private DBConnector connector;
	private LogoutForm logoutForm;
	private User user;
	
	public LogoutController(User user)
	{
		connector = new DBConnector();
		logoutForm = new LogoutForm(this);
		this.user = user;
	}
	
	public void logout()
	{
	}
	
	public void confirm()
	{
		LocalDateTime a = LocalDateTime.now();
		connector.saveEvent(user, a, "Logout");
	}
}
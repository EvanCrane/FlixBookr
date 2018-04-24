import main;
import java.time.LocalDateTime;
import main.client.ScheduleMovieForm;
import main.client.ConfirmWindow;
import main.client.ErrorWindow;
import main.java.Movie;
import main.server; 

//Schedule Movie Controller Class 
public class ScheduleController implements Controller 
{
	private DBConnector connector;
	private ScheduleMovieForm movieForm;
	private ConfirmWindow confirmWindow;
	private ErrorWindow errorWindow;
	
	public Movie movie = new Movie();
	
	public ScheduleController()
	{
		connector = new DBConnector();
		movieForm = new ScheduleMovieForm(this);
		confirmWindow = new ConfirmWindow(this);
		errorWindow = new ErrorWindow(this);
	}
	
	public void scheduleNewMovie()
	{
		movieForm.initialize();
	}
	
	public void confirm()
	{
		saveMovie(movie);
	}
}
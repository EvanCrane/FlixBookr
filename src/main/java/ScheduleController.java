package main.java;

import java.time.LocalDateTime;
import main.client.ScheduleMovieForm;
import main.java.Movie;
import main.server.DBConnector;

//Schedule Movie Controller Class 
public class ScheduleController implements Controller 
{
	private DBConnector connector;

	
	public ScheduleController()
	{
		connector = new DBConnector();
	}
	
	public void scheduleMovie(String title,
							  String rate,
							  LocalDateTime stT,
							  LocalDateTime enT,
							  LocalDateTime[] shT,
							  String poster,
							  String descr)
	{
		Movie movie = new Movie();
		movie.setRating(rate);
		movie.setTitle(title);
		movie.setStartTime(stT);
		movie.setEndTime(enT);
		movie.setShowTime(shT);
		movie.setPromoImage(poster);
		movie.setDescription(descr);

		connector.saveMovie(movie);
	}
	public void confirm()
	{

	}
}
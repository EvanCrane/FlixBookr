package main.java;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.client.ScheduleMovieForm;
import main.server.DBConnector;

//Schedule Movie Controller Class 
public class ScheduleController implements Controller 
{
	private DBConnector connector;
	private ScheduleMovieForm scheduleMovieForm;

	
	public ScheduleController()
	{
		connector = new DBConnector();
	}
	
	public void scheduleMovie(Stage stage){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/ScheduleMovie.fxml"));
			scheduleMovieForm = new ScheduleMovieForm();
			Parent root1 = fxmlLoader.load();
			scheduleMovieForm = fxmlLoader.getController();
			scheduleMovieForm.initialize(this);
			stage.setScene(new Scene(root1));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Schedule Movie");
			stage.show();
			// Hide this current window (if this is what you want)
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void confirm(String title,
						String rate,
						LocalDateTime stT,
						LocalDateTime enT,
						LocalDateTime[] shT,
						String descr) {
		Movie movie = new Movie();
		movie.setRating(rate);
		movie.setTitle(title);
		movie.setStartTime(stT);
		movie.setEndTime(enT);
		movie.setShowTime(shT);
		movie.setDescription(descr);

		connector.saveMovie(movie);
	}
	
	public void OpenHomeScreen(boolean isAdmin){
	
	}
}
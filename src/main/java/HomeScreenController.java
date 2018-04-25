package main.java;

import main.server.DBConnector;
import java.util.HashSet;
import main.java.Movie;

public class HomeScreenController implements Controller{
    private DBConnector connector;

    public HomeScreenController() {
        connector = new DBConnector();
    }

    public HashSet<Movie> getMovieList() {
        return connector.getAllMovies();
    }

    public void OpenHomeScreen(){
        return;
    }
}

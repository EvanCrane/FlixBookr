package main.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import main.java.User;
import main.java.Reservation;
import main.java.Movie;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Database Controller.
 * A connector to the database. 
 * @author Bilal Sellak
 */
public class DBConnector {



    /**
     * Method to open a connection to the database
     * @return Theater Table Connection 
     */
    private Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost/theater?" +
                        "user=root&password=Moodey251");
            }
            catch(SQLException except) {
                System.out.println("SQLException: " + except.getMessage());
                System.out.println("SQLState: " + except.getSQLState());
                System.out.println("VendorError: " + except.getErrorCode());
            }
        }
        catch(ClassNotFoundException except) {
            System.out.println("ClassNotFoundException: " + except.getMessage());
        }


        return null;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }
    }


    /**
     * Save a movie entity into database
     * @param movie A movie that doesn't exist in the database 
     */
    public void saveMovie(Movie movie) {

        Connection connection = createConnection();
        String query = "Insert into movies(title, rating, starttime, endtime, showtime, image, description)" +
                        "values(?,?,?,?,?,?,?)";

        LocalDateTime[] showtimes = movie.getShowTimes();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < showtimes.length; i++) {
                try {

                    statement.setString(1, movie.getTitle());
                    statement.setString(2, movie.getRating());
                    statement.setTimestamp(3, Timestamp.valueOf(movie.getStartTime()));
                    statement.setTimestamp(4, Timestamp.valueOf(movie.getEndTime()));
                    statement.setTimestamp(5, Timestamp.valueOf(showtimes[i]));
                    statement.setString(6,movie.getPromoImage());
                    statement.setString(7, movie.getDescription());

                    statement.addBatch();
                }

                catch (SQLException except){
                    System.out.println("SQLException: " + except.getMessage());
                    System.out.println("SQLState: " + except.getSQLState());
                    System.out.println("VendorError: " + except.getErrorCode());
                }
            }

            try { statement.executeBatch(); }
            catch (SQLException except){
                System.out.println("SQLException: " + except.getMessage());
                System.out.println("SQLState: " + except.getSQLState());
                System.out.println("VendorError: " + except.getErrorCode());
            }
        }
        catch (SQLException except){
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        closeConnection(connection);
    }




    /**
     * Method to save a reservation entity into database
     * @param res A users reservation for a specific time and mvoie 
     */
    public void saveReservation(Reservation res) {

        Movie movie = res.getMovie();
        User user = res.getUser();

        Connection connection = createConnection();
        String query = "Insert into reservations(username, movie, price, restime, amount)" +
                        "values(?,?,?,?,?)";

        Timestamp resTime = Timestamp.valueOf(res.getResTime());

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getuName());
            statement.setString(2, movie.getTitle());
            statement.setDouble(3, res.getPrice());
            statement.setTimestamp(4, resTime);
            statement.setInt(5, res.getAmount());

            statement.executeUpdate();
        }

        catch (SQLException except){
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        closeConnection(connection);
    }




    /**
     * Method to save events such as login to the database
     * @param user A user, either a customer or admin
     * @param eventtime The time that the event occurred
     * @param event A event(either login or logout)
     */
    public void saveEvent(User user, LocalDateTime eventtime, String event) {

        Connection connection = createConnection();
        String query = "Insert into sessions(username, time, event) values (?,?,?)";

        Timestamp time = Timestamp.valueOf(eventtime);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getuName());
            statement.setTimestamp(2, time);
            statement.setString(3,event);
            statement.executeUpdate();
        }
        
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        try {
            connection.close();
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }
    }
    /*
    public void saveUser(User user) {

        Connection connection = createConnection();
        String query = "Insert into users( username,password, privilege) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getuName());
            statement.setString(2,user.getpWord());
            statement.setString(3,user.getPriv());
            statement.executeUpdate();
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        closeConnection(connection);
    }
    */
    /**
     * Method to save session events to the database
     * @param uName A username
     * @return User and hashed password that match up to that username
     */
    public User getUser(String uName) {

        Connection connection = createConnection();
        String query = "select username,password,privilege from users where username =?";
        User user = new User();
        try {
            PreparedStatement statement1 = connection.prepareStatement(query);
            statement1.setString(1,uName);

            ResultSet rs = statement1.executeQuery();

            while(rs.next()) {
                user.setuName(rs.getString("username"));
                user.setpWord(rs.getString("password"));
                user.setPriv(rs.getBoolean("privilege"));
            }

        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        closeConnection(connection);
        return user;
    }


    
    /**
     * Method to get all movies
     * @return HashSet of Movies objects with title and image path set
     */
    public HashSet<Movie> getAllMovies() {
        Connection connection = createConnection();
        String query = "Select title, image FROM movies";
        HashSet<Movie> movieset = new HashSet<Movie>();
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            Movie movie = new Movie();
            ResultSet rs = statement.executeQuery(query);


            while(rs.next()) {
                movie.setTitle(rs.getString("title"));
                movie.setPromoImage(rs.getString("image"));
                movieset.add(movie);
            }
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }


        closeConnection(connection);
        return movieset;
    }


    /**
     * Method to get Price of movie
     * @param mTitle Title of movie
     * @return Movie objecg with title, regPrice, and matPrice set
     */
    public Movie getPrice(String mTitle) {

        Connection connection = createConnection();
        String query = "Select title, regprice, matprice FROM movies WHERE title = ?";

        
        Movie movie = new Movie();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, mTitle);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                movie.setTitle(rs.getString("title"));
                movie.setRegPrice(rs.getDouble("regprice"));
                movie.setMatPrice(rs.getDouble("matprice"));
            }
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }
        closeConnection(connection);
        return movie;

    }
}

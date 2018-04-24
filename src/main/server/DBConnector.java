package main.server;


import java.sql.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.date.Timestamp;

import main.java.User;
import main.java.Reservation;
import main.java.Movie;

import java.time.LocalDateTime;

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
    public Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost/theater?" +
                                           "user=root&password=Moodey251");
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
        PreparedStatement statement = connection.prepareStatement(query);
        Timestamp resTime = Timestamp.valueOf(res.resTime);

        try {
            statement.setString(1, movie.title);
            statement.setString(2, movie.rating);
            statement.setTimestamp(3, movie.starttime);
            statement.setTimestamp(4, movie.endtime);
            statement.setTimestamp(5, movie.showtime);
            statement.setString(6,movie.image);
            statement.setString(7, movie.description);

            statement.executeUpdate();
        }

        catch (SQLException except){
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        conn.close();
    }




    /**
     * Method to save a reservation entity into database
     * @param res A users reservation for a specific time and mvoie 
     */
    public void saveReservation(Reservation res) {

        Movie movie = res.movie;
        User user = res.user;

        Connection connection = createConnection();
        String query = "Insert into reservations(username, movie, price, restime, amount)" +
                        "values(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        Timestamp resTime = Timestamp.valueOf(res.resTime);

        try {
            statement.setString(1, user.uName);
            statement.setString(2, movie.title);
            statement.setDouble(3, res.price);
            statement.setTimestamp(4, resTime);
            statement.setInt(5, res.amount);

            statement.executeUpdate();
        }

        catch (SQLException except){
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        conn.close();
    }




    /**
     * Method to save events such as login to the database
     * @param user A user, either a customer or admin
     * @param eventtime The time that the event occurred
     * @param event A event(either login or logout)
     */
    public void saveEvent(User user, LocalDateTime eventtime, String event) {

        Connection connection = createConnection();
        String query = "Insert into sessions(username, time, event) values (?, ?," +
                        event + ")";
        PreparedStatement statement = connection.prepareStatement(query);
        Timestamp time = Timestamp.valueOf(eventtime);
        try {
            statement.setString(1, user.uName);
            statement.setTimestamp(2, time);
            statement.executeUpdate();
        }
        
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }
        
        conn.close();
    }


    /**
     * Method to save events such as login to the database
     * @param uName A username
     * @return User and hashed password that match up to that username
     */
    public User getUser(String uName) {

        Connection connection = createConnection();
        String query = "Select username,password, privilege FROM users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        try {
            statement.setString(1, uName);
            ResultSet rs = preparedStatement.executeQuery(query);
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        User user = new User();
        while(rs.next()) {
            user.setuName(rs.getString(username));
            user.setpWord(rs.getString(password));
            user.setPriv(rs.getString(privilege));
        }
        return user;
    }

    public Movie getPrice(String mTitle) {

        Connection connection = createConnection();
        String query = "Select title, regprice, matprice FROM movies WHERE title = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        Movie movie = new Movie();
        try {
            statement.setString(1, mTitle);
            ResultSet rs = preparedStatement.executeQuery(query);
            while(rs.next()) {
                movie.setTitle(rs.getString(title));
                movie.setregPrice(rs.getDouble(regprice));
                movie.setmatPrice(rs.getDouble(matprice));
            }
        }
        catch(SQLException except) {
            System.out.println("SQLException: " + except.getMessage());
            System.out.println("SQLState: " + except.getSQLState());
            System.out.println("VendorError: " + except.getErrorCode());
        }

        return movie;

    }
}

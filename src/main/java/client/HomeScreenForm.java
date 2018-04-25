package main.java.client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import main.FlixBookr;
import main.java.LoginController;
import main.java.LogoutController;
import main.java.TicketController;
import main.java.ScheduleController;
import main.java.HomeScreenController;
import main.java.Movie;

import java.util.HashSet;
import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.text.TextAlignment.CENTER;

public class HomeScreenForm
{
    private FlixBookr flixBookr;
    private BorderPane rootLayout;

    private HomeScreenController homeScreenController;
    //Temporary Image generator index
    private int tempIndex = 9;
    private Boolean isAdmin = false;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private Label welcomeName;
    @FXML
    private Button btnScheduleMovie;
    @FXML
    private Button btnLogout;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridMovies;
    @FXML
    private StackPane scrollChildStack;
    @FXML
    private AnchorPane anchorRowA;
    @FXML
    private AnchorPane anchorRowB;
    @FXML
    private AnchorPane anchorRowC;
    
    // called by the FXML loader after the labels declared above are injected:
    @FXML
    public void initialize() {
        //Make sure that the user logs in
        //Temp Session Name
        homeScreenController = new HomeScreenController();
        welcomeName.setText("Welcome Evan Crane");
        HashSet<Movie> movies = homeScreenController.getMovieList();
        CreateImageViews(6,movies);
        
    }
    
    public void enableAdminControls(boolean isAdmin) {
        if(isAdmin){
            btnScheduleMovie.setVisible(true);
            btnScheduleMovie.setDisable(false);
        }
    }
    
    private void CreateImageViews(int index,HashSet<Movie> movies) {
        ImageView[] image = new ImageView[index];
        Label[] label = new Label[index];
        Image defaultImg = new Image("/images/defaultPoster.png");
        String defaultTitle = "Fightman ";
        double minAnchorHeight = 183.0;
        double rowScaler = 150.0;
        for (int i = 0; i < index; i++) {
            boolean newRow = false;
            newRow = i % 3 == 0;
            if (newRow && i != 0) {
                rowScaler += 366.0;
                minAnchorHeight += rowScaler;
            }
            image[i] = new ImageView();
            image[i].setId("imgMovie" + Integer.toString(i));
            image[i].setImage(defaultImg);
            image[i].setFitHeight(183.0);
            image[i].setFitWidth(120.0);
            image[i].pickOnBoundsProperty();
            image[i].preserveRatioProperty();
            image[i].setLayoutX(140.0);
            image[i].setLayoutY(70.0 + rowScaler);
            //Adding click actions to each of the image icons
            image[i].setCursor(Cursor.HAND);
            image[i].setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Opening the reserve ticket window");
                    //Need to pass in movie title
                    TicketController ticketController = new TicketController();
                    ticketController.ReserveTicket(new Stage(), defaultTitle);
                }
            });
            
            //Adding Labels for title to go beneath each movie.
            label[i] = new Label();
            label[i].setId("label" + defaultTitle + Integer.toString(i));
            label[i].setFont(Font.font(18));
            label[i].setTextFill(WHITE);
            label[i].setText(defaultTitle + Integer.toString(i));
            label[i].setLayoutX(125.0);
            label[i].setLayoutY(270.0 + rowScaler);
            label[i].setAlignment(Pos.CENTER);
            label[i].setTextAlignment(CENTER);
            label[i].setPrefWidth(150);
            label[i].setPrefHeight(75);
            label[i].setWrapText(true);
            //Adding click actions to each of the title labels
            label[i].setCursor(Cursor.HAND);
            label[i].setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Opening the reserve ticket window");
                    //Need to pass in movie title

                    TicketController ticketController = new TicketController();
                    ticketController.ReserveTicket(new Stage(),defaultTitle);
                }
            });
        }
        
        //Adjust the height of the scroll child anchor
        scrollChildStack.setPrefHeight(minAnchorHeight);
        gridMovies.prefWidthProperty().bind(rootAnchor.widthProperty());
        
        //Adding the Images to each 3 Anchors
        for (int i = 0; i < index; i++) {
            anchorRowA.getChildren().addAll(image[i], label[i]);
            i++;
            anchorRowB.getChildren().addAll(image[i], label[i]);
            i++;
            anchorRowC.getChildren().addAll(image[i], label[i]);
        }
    }
    
    private void OpenReserveTicketWindow(MouseEvent event, String movieTitle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReserveTicket.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Reserve Ticket");
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void HandleScheduleMovie(MouseEvent event) {
        btnScheduleMovie.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the Schedule Movie Form");
                ScheduleController scheduleController = new ScheduleController();
                scheduleController.scheduleMovie(new Stage());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                //OpenScheduleMovie(event);
            }
        });
    }
    
    private void OpenScheduleMovie(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleMovie.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Schedule Movie");
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void HandleLogout(MouseEvent event) {
        btnLogout.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the Logout Alert");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout of FlixBookr", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    //We have to have a username at minimum on this forms attributes
                    //LogoutController logoutController = new LogoutController(user);
                    //logoutController.confirm();
                    OpenLogin(event);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    
    private void OpenLogin(MouseEvent event) {
    
        LoginController login = new LoginController();
        login.login(new Stage());
        // Hide this current window (if this is what you want)
        //TODO MAY NEED TO ACTUALLY CLOSE THE MAIN WINDOW PROCESS
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}

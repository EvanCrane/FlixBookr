package main.java.client;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.ScheduleController;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ScheduleMovieForm
{
    private ScheduleController scheduleController;
    private Desktop desktop = Desktop.getDesktop();
    private List<LocalDateTime> timeSelections;
    @FXML
    private TextField txtMovieTitle;
    @FXML
    private ChoiceBox choiceRating;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private ListView timesListView;
    @FXML
    private TextField txtMatDollar;
    @FXML
    private TextField txtMatCent;
    @FXML
    private TextField txtStandDollar;
    @FXML
    private TextField txtStandCent;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnChooseImage;
    @FXML
    private Button btnScheduleMovie;
    @FXML
    private Button btnCancelChanges;
    
    @FXML
    public void initialize(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
        HandleScheduleMovie();
        HandleCancelChanges();
        SetTimesList();
        SetRatingChoices();
    }
    
    private void SetRatingChoices() {
        choiceRating.setItems(FXCollections.observableArrayList(
                "G", "PG ", "PG-13", "R"));
    }
    private void SetTimesList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("12:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00",
                "07:00", "08:00", "09:00", "10:00", "11:00");
        timesListView.setItems(list);
        timesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        timesListView.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                ObservableList<String> selectedItems =  timesListView.getSelectionModel().getSelectedItems();
    
                for(String s : selectedItems){
                    System.out.println("selected item " + s);
                    timeSelections.add(LocalDateTime.of(LocalDate.now(),(LocalTime.parse(s))));
                }
            }
        });
    }
    
    @FXML
    private void HandleChooseImage(MouseEvent event) {
        btnChooseImage.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            final FileChooser fileChooser = new FileChooser();
            
            @Override
            public void handle(MouseEvent event) {
                ConfigureFileChooser(fileChooser);
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    openFile(file);
                }
            }
        });
    }
    
    private static void ConfigureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void HandleScheduleMovie() {
        btnScheduleMovie.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                //Validations for Schedule Movie
                if (txtMovieTitle.getText().isEmpty() || txtMovieTitle.getText().contains(";") ||
                        txtMovieTitle.getText().contains("(") || txtMovieTitle.getText().contains(")")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter a valid Movie Title", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtMovieTitle.getLength() > 50) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The Movie Title is too long", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (dateStart.getValue() == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter a Start Date", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (dateEnd.getValue() == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter an EndDate", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (choiceRating.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a rating", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (timesListView.getSelectionModel().getSelectedItems() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select showtimes", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtMatDollar.getText().isEmpty() || txtStandDollar.getText().length() > 5
                        || Pattern.matches("[a-zA-Z]+" ,txtMatDollar.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Matinee dollar price", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtStandDollar.getText().isEmpty() || txtStandDollar.getText().length() > 5
                        || Pattern.matches("[a-zA-Z]+" ,txtStandDollar.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Standard dollar price", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtMatCent.getText().isEmpty() || txtMatCent.getText().length() > 2
                        || Pattern.matches("[a-zA-Z]+" ,txtMatCent.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Matinee cent price", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtStandCent.getText().isEmpty() || txtStandCent.getText().length() > 2
                        || Pattern.matches("[a-zA-Z]+" ,txtStandCent.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Standard cent price", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (txtDescription.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a movie description", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if(txtDescription.getText().length() > 500) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Standard cent price", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
    
                //If no problems open the movie form
                System.out.println("Opening the schedule movie confirmation");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to confirm this reservation?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    //GETTING INFO FROM COMPONENTS
                    String title = txtMovieTitle.getText();
                    String ratingChoice = choiceRating.getSelectionModel().getSelectedItem().toString();
                    LocalDateTime startTime = LocalDateTime.of(dateStart.getValue(), LocalTime.of(0,0));
                    LocalDateTime endTime = LocalDateTime.of(dateEnd.getValue(), LocalTime.of(0,0));
                    List<LocalDateTime> timeSelect = timeSelections;
                    LocalDateTime[] timeSelectArray = new LocalDateTime[timeSelect.size()];
                    timeSelectArray = timeSelect.toArray(timeSelectArray);
                    Double matPrice = Double.parseDouble(txtMatDollar.getText().toString() + "." + txtMatCent.getText().toString());
                    Double standPrice = Double.parseDouble(txtStandDollar.getText().toString() + "." + txtStandCent.getText().toString());
                    String description = txtDescription.getText().toString();
                    
                    ScheduleController scheduleController = new ScheduleController();
                    scheduleController.confirm(title, ratingChoice,startTime, endTime, timeSelectArray, description);
                    alert.close();
                    
                    OpenHomeScreen(event);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    @FXML
    private void HandleCancelChanges() {
        btnCancelChanges.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the OpenHomeScreen changes confirmation");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to OpenHomeScreen changes?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    OpenHomeScreen(event);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    private void OpenHomeScreen(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

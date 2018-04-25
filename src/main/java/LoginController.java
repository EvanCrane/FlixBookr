package main.java;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.org.mindrot.jbcrypt.BCrypt;
import main.server.DBConnector;
import main.java.client.LoginForm;


public class LoginController implements Controller {

    private BCrypt crypt;
    private DBConnector connector;

    public LoginController() {
        connector = new DBConnector();
        crypt = new BCrypt();
    }

    public void login(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/Login.fxml"));
            LoginForm loginForm = new LoginForm();
            fxmlLoader.setController(loginForm);
            Parent root = fxmlLoader.load();
            loginForm = fxmlLoader.getController();
            loginForm.initialize(this);
            stage.initStyle(StageStyle.UNDECORATED);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void OpenHomeScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/HomeScreen.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to submit Username and check if credentials are solid
     * @param uName A Username
     * @param pWord User's plaintext password
     * @return A Boolean array where 0 position is true if the passwords match 
     * and 1 position is true if user is an admin;
     */
    public Boolean[] submit(String uName, String pWord) {

        Boolean[] userInfo = new Boolean[2];
        User user = connector.getUser(uName);
        userInfo[0] = crypt.checkpw(pWord, user.getpWord());

        if (userInfo[0]) {
            if(user.getPriv() == "admin")
                userInfo[1] = true;
            saveLogin(user);
        }
        
        return userInfo;

    }

    private void saveLogin(User user) {
        connector.saveEvent(user, LocalDateTime.now(), "login");
    }

}
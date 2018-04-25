package main.java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.client.LoginForm;
import main.org.mindrot.jbcrypt.BCrypt;
import main.server.DBConnector;

import java.io.IOException;
import java.time.LocalDateTime;


public class LoginController implements Controller {

    private DBConnector connector;

    public LoginController() {
        connector = new DBConnector();
    }

    public void login(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/Login.fxml"));
            LoginForm loginForm = new LoginForm();
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
    public boolean[] submit(String uName, String pWord) {

        boolean[] userInfo = new boolean[2];
        User user = connector.getUser(uName);
        if(user.getuName() == null)
            return userInfo;
        userInfo[0] = BCrypt.checkpw(pWord, user.getpWord());

        if (userInfo[0]) {
            if(user.getPriv())
                userInfo[1] = true;
            saveLogin(user);
        }
        
        return userInfo;

    }

    private void saveLogin(User user) {
        connector.saveEvent(user, LocalDateTime.now(), "login");
    }

}
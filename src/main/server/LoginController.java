package main.server;

import java.time.LocalDateTime;

import main.org.mindrot.jbcrypt.BCrypt;
import main.server.DBConnector;
import main.server.Controller;
import main.client.LoginForm;
import main.java.User;


public class LoginController implements Controller {

    private BCrypt crypt;
    private DBConnector connector;
    private LoginForm loginForm;

    public LoginController() {
        connector = new DBConnector();
        loginForm = new LoginForm();
        crypt = new BCrypt();
    }

    public void login() {
        loginForm.initialize();
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
        userInfo[0] = crypt.checkpw(pWord, user.pWord);

        if (userInfo[0]) {
            if(user.priv == "admin")
                userInfo[1] = true;
            saveLogin(user);
        }
        
        return userInfo;

    }

    private void saveLogin(User user) {
        connector.saveEvent(user, LocalDateTime.now(), "login");
    }

}
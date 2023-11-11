package me.piguy.baddesk.pages;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.PrimerLight;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.piguy.baddesk.CredentialChecker;
import me.piguy.baddesk.SimpleCredentialChecker;
import me.piguy.baddesk.database.Database;
import me.piguy.baddesk.database.MongoUserDB;
import me.piguy.baddesk.router.Page;

import java.io.IOException;

public class LoginViewController implements ViewController {
    Database db;

    @FXML
    Label text;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        CredentialChecker credentialChecker = new SimpleCredentialChecker();

        if (credentialChecker.checkPassword(username, password)) {
            // login succesfull
            try {
                Page.Dashboard.navigate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Login failed
            text.setText("Wrong username or password.");
        }
    }
    public void initialize() {
        db = new MongoUserDB();
    }
}
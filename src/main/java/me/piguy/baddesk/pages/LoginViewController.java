package me.piguy.baddesk.pages            ;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.PrimerLight;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.piguy.baddesk.database.Database;
import me.piguy.baddesk.database.MongoUserDB;
import me.piguy.baddesk.router.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements ViewController {
    Database db;

    @FXML
    Label text;

    @FXML
    private void login() {
        //db.get();

        //text.getScene().setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());


        try {
            Page.Dashboard.navigate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
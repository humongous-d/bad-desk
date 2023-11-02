package me.piguy.baddesk;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.stage.Stage;
import me.piguy.baddesk.api.*;
import me.piguy.baddesk.router.Router;
import me.piguy.baddesk.router.Page;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Router.initiate(stage);

        Page.Login.navigate();

        ApiAdapter api = new PythonAPI();
        api.connect("127.0.0.1", "8000");
        api.login("theanimeman", "nonsensejp");
        api.currentUser();

        stage.show();
    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        launch();
    }
}
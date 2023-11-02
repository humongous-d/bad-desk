package me.piguy.baddesk;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.stage.Stage;
import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.api.Ctx;
import me.piguy.baddesk.api.PythonAPI;
import me.piguy.baddesk.api.Request;
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

        Request request = new Request(api, "/test", "GET");
        request.Do();

        stage.show();
    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        launch();
    }
}
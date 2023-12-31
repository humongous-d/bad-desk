package me.piguy.baddesk;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.stage.Stage;
import me.piguy.baddesk.api.*;
import me.piguy.baddesk.pages.theme.PageTheme;
import me.piguy.baddesk.router.Router;
import me.piguy.baddesk.router.Page;

import java.io.IOException;

public class Main extends Application {
    private ApiAdapter api;
    @Override
    public void start(Stage stage) throws IOException {
        Router.initiate(stage);

        // Set API
        this.api = new PythonAPI();
        ConfigurationManager.getInstance().api = this.api;

        // API Testing
        api.connect("136.244.100.94", "5000");
//        System.out.println(api.getTickets());


//        api.newTicket("some ticket", "a", 1, "Open", "a", "a");

        Page.Login.navigate();

        stage.show();

    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(PageTheme.defaultTheme().getThemeDark().getUserAgentStylesheet());
        launch();
    }
}
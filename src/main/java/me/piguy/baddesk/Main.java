package me.piguy.baddesk;

import javafx.application.Application;
import javafx.stage.Stage;
import me.piguy.baddesk.router.BadRouter;
import me.piguy.baddesk.router_me.MeRouter;
import me.piguy.baddesk.router_me.Page;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MeRouter.initiate(stage);

        // Bind Router to the app and stage
        BadRouter.bind(this, stage);
        // Create a few sample routes
        BadRouter.newRoute("main","hello-view.fxml", "BadDesk");
        BadRouter.newRoute("secondmain","hello-view.fxml", "BadDesk 2");
        // Go to main page
        BadRouter.to( "main", null);
        // Show our application

        // you can do this
        MeRouter.router.load(Page.Login);
        // or this ¯\_(ツ)_/¯
        Page.Login.navigate();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
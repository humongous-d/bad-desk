package me.piguy.baddesk;

import javafx.application.Application;
import javafx.stage.Stage;
import me.router.BadRouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Bind Router to the app and stage
        BadRouter.bind(this, stage);
        // Create a few sample routes
        BadRouter.newRoute("main","hello-view.fxml", "BadDesk");
        BadRouter.newRoute("secondmain","hello-view.fxml", "BadDesk 2");
        // Go to main page
        BadRouter.to( "main", null);
        // Show our application
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package me.piguy.baddesk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.piguy.baddesk.router.BadRouter;
import me.piguy.baddesk.router_me.MeRouter;
import me.piguy.baddesk.router_me.Page;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");

        MeRouter.router.load(Page.Dashboard);
    }
}
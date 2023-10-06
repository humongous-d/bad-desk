package me.piguy.baddesk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.router.BadRouter;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        BadRouter.to( "secondmain", null);
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
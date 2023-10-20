package me.piguy.baddesk.router;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.piguy.baddesk.Main;
import me.piguy.baddesk.pages.ViewController;

import java.io.IOException;

/**
 * Singleton class which performs as a router to navigate between different pages.<br/>
 * Pages for MeRouter are all part of the <a href=#{@link}>{@link Page}</a> enum.
 */
public class Router {
    public static final Router router = new Router();

    /**
     * This initiates the singleton class. It is recommended to run this when your program starts up in
     * <a href=#{@link}>{@link javafx.application.Application#start}</a>.
     *
     * @param stage the <a href=#{@link}>{@link Stage}</a> for your javafx application
     */
    public static void initiate(Stage stage) {
        router.stage = stage;
    }

    private Stage stage;

    /**
     * Navigate to a new screen. This loads a new screen, state will not be saved if loading like this.
     *
     * @param page a member of <a href=#{@link}>{@link Page}</a> which has a valid fxml file bound to it
     * @throws IOException if error occurs during loading of the fxml layout
     */
    public void load(Page page) throws IOException {
        FXMLLoader loader = new FXMLLoader(ViewController.class.getResource(page.fxml));
        stage.setTitle(page.title);

        stage.setScene(new Scene(loader.load()));

        stage.show();
    }
}

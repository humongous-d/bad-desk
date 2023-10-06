package me.router;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
public class BadRouter {
    // Our Application instance must be stored in the singleton class so that we can later access it's resources
    private static Object mainRef;
    // Our router instance, which is a singleton instance
    private static BadRouter router;
    // Our main window that we draw to
    private static Stage window;
    // Routes that our application has
    private static AbstractMap<String, RouteScene> routes = new HashMap<>();
    // Our current route
    private static RouteScene currentRoute;


    private BadRouter() {}

    // Creation of a new route is easy,
    // it takes a route name, scene path as well as a window title

    public static void newRoute(String routeName, String scenePath, String windowTitle) {
        getInstance(mainRef, window);
        RouteScene scene = new RouteScene(scenePath, windowTitle);
        routes.put(routeName, scene);
    }

    // this goes to a scene we have added using newRoute
    public static void to( String routeName, Object withData) throws IOException {
        getInstance(mainRef, window);
        RouteScene route = routes.get(routeName);
        route.data = withData;
        loadRoute( route);
    }


    // Bind our router to the application, this is used to talk to the app itself and the stage
    public static void bind(Object ref, Stage win){
        getInstance(ref, win);
    }


    // Loads a route
    public static void loadRoute(RouteScene route) throws IOException {
        getInstance(mainRef, window);
        currentRoute = route;
        FXMLLoader fxmlLoader = new FXMLLoader(mainRef.getClass().getResource("hello-view.fxml"));
        Parent parent = (Parent)fxmlLoader.load();
        window.setTitle(route.windowTitle);
        window.setScene(new Scene(parent));
        window.show();
    }

    public static void getInstance(Object ref, Stage win) {
        if (mainRef == null) {
            mainRef = ref;
        }

        if (router == null) {
            router = new BadRouter();
        }

        if (window == null) {
            window = win;
        }
    }
}

package me.piguy.baddesk.router;

public class RouteScene {
    public Object data;
    public String scenePath;
    public String windowTitle;

    public RouteScene(String scenePath, String windowTitle) {
        this.scenePath = scenePath;
        this.windowTitle = windowTitle;
    }
}

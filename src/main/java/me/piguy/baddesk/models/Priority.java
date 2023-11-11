package me.piguy.baddesk.models;

import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public enum Priority {
    Critical(Feather.ALERT_TRIANGLE), High(Feather.ALERT_OCTAGON), Medium(Feather.ALERT_CIRCLE), Low(Feather.CLOCK);

    Priority(Ikon icon) {
        this.icon = new FontIcon(icon);
    }

    public final FontIcon icon;

    public static int compare(FontIcon a, FontIcon b) {
        String aString = a.getIconLiteral();
        String bString = b.getIconLiteral();

        List<String> priorities = List.of("fth-alert-triangle", "fth-alert-octagon", "fth-alert-circle", "fth-clock");

        return priorities.indexOf(aString) - priorities.indexOf(bString);
    }
    public static int compare(Priority a, Priority b) {
        List<Priority> priorities = List.of(Critical, High, Medium, Low);

        return priorities.indexOf(a) - priorities.indexOf(b);
    }
}

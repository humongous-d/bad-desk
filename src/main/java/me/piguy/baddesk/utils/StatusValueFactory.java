package me.piguy.baddesk.utils;

import atlantafx.base.theme.Styles;
import javafx.beans.NamedArg;
import me.piguy.baddesk.models.Priority;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;


/**
 * Renders colours in the cell factory
 */
public class StatusValueFactory<S> extends RecordValueFactory<S, FontIcon> {
    public StatusValueFactory(@NamedArg("value") String value) {
        super(value);
    }

    @Override
    protected FontIcon convertValue(Object value) {
        if (value instanceof Priority priority) {
            FontIcon icon;
            switch (priority) {
                case Critical -> {
                    icon = new FontIcon(Feather.ALERT_TRIANGLE);
                    icon.getStyleClass().add(Styles.DANGER);
                }
                case High -> {
                    icon = new FontIcon(Feather.ALERT_OCTAGON);
                    icon.getStyleClass().add(Styles.WARNING);
                }
                // Icon colour stays default here
                case Low -> {
                    icon = new FontIcon(Feather.CLOCK);
                }
                // This assumes MEDIUM priority
                default -> {
                    icon = new FontIcon(Feather.ALERT_CIRCLE);
                    icon.getStyleClass().add(Styles.ACCENT);
                }
            }
            return icon;
        } else {
            return null;
        }
    }
}

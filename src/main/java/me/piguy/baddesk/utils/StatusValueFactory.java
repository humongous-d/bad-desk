package me.piguy.baddesk.utils;

import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.fluentui.FluentUiFilledMZ;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.fluentui.FluentUiRegularMZ;
import org.kordamp.ikonli.javafx.FontIcon;


/**
 * Renders colours in the cell factory
 */
public class StatusValueFactory<S> implements Callback<TableColumn.CellDataFeatures<S, FontIcon>, ObservableValue<FontIcon>> {
    private final String priority;

    public StatusValueFactory(@NamedArg("priority") String priority) {
        this.priority = priority;
    }

    public FontIcon getIcon() {
        return switch (priority) {
            case "critical" -> new FontIcon(Feather.ALERT_TRIANGLE);
            case "high" -> new FontIcon(Feather.ALERT_OCTAGON);
            case "medium" -> new FontIcon(Feather.ALERT_CIRCLE);
            case "low" -> new FontIcon(Feather.CLOCK);
            default -> new FontIcon(FluentUiRegularMZ.QUESTION_CIRCLE_24);
        };
    }

    @Override
    public ObservableValue<FontIcon> call(TableColumn.CellDataFeatures<S, FontIcon> param) {
        return new ReadOnlyObjectWrapper<>(getIcon());
    }
}

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
public class StatusValueFactory<S> extends RecordValueFactory<S, FontIcon> {
    public StatusValueFactory(@NamedArg("value") String value) {
        super(value);
    }

    @Override
    protected FontIcon convertValue(Object value) {
        if (value instanceof String) {
            String priority = (String) value;
            return null;
        } else {
            return null;
        }
    }
}

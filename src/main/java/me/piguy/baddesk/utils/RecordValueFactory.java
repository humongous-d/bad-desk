package me.piguy.baddesk.utils;

import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;

public class RecordValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> {
    protected String valueRef;

    public RecordValueFactory(@NamedArg("value") String value) {
        this.valueRef = value;
    }

    @SuppressWarnings("unchecked")
    protected T convertValue(Object value) {
        if (value != null) {
            return (T) value;
        } else {
            return null;
        }
    }

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> param) {
        if (param != null && param.getValue() != null) {
            try {
                java.lang.reflect.Method getter = param.getValue().getClass().getMethod(valueRef);
                Object value = getter.invoke(param.getValue());

                if (value != null) {
                    return new ReadOnlyObjectWrapper<>(convertValue(value));
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}

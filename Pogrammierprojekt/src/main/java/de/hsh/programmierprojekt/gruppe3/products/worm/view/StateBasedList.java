package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateListener;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateTracker;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class StateBasedList<T> extends ListBinding<T> implements StateListener {
    private StateTracker<List<T>> tracked;

    @Override
    public void update() {
        invalidate();
    }

    @Override
    protected ObservableList<T> computeValue() {
        return FXCollections.observableList(tracked.get());
    }

    public void track(StateTracker<List<T>> tracked) {
        this.tracked = tracked;
        tracked.addListener(this);
    }

    public void untrack(StateTracker<List<T>> tracked) {
        this.tracked = null;
        tracked.removeListener(this);
    }

}

package de.hsh.programmierprojekt.gruppe3.products.worm.util;

import java.util.HashSet;
import java.util.Set;

public abstract class StateTrackerBase<T> implements StateTracker<T> {
    private final Set<StateListener> listeners = new HashSet<>();
    private T value;

    @Override
    public void addListener(StateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(StateListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void update() {
        this.value = computeValue();
        listeners.forEach(StateListener::update);
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
        listeners.forEach(StateListener::update);
    }

    protected abstract T computeValue();
}

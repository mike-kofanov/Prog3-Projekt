package de.hsh.programmierprojekt.gruppe3.products.worm.util;

public interface StateTracker<T> extends StateListener {
    default void track(StateTracker<?> tracking) {
        tracking.addListener(this);
    }

    default void untrack(StateTracker<?> tracking) {
        tracking.removeListener(this);
    }

    T get();
    void set(T t);

    void addListener(StateListener listener);

    void removeListener(StateListener listener);

}

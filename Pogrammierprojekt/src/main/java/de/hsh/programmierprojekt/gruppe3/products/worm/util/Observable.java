package de.hsh.programmierprojekt.gruppe3.products.worm.util;

import java.util.Collection;

public interface Observable {
    default void addObserver(Runnable observer) {
        getObservers().add(observer);
    }

    default void deleteObserver(Runnable observer) {
        getObservers().remove(observer);
    }

    default void deleteObservers() {
        getObservers().clear();
    }

    default void notifyObservers() {
        getObservers().forEach(Runnable::run);
    }

    Collection<Runnable> getObservers();
}

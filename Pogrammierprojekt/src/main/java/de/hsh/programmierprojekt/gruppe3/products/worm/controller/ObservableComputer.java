package de.hsh.programmierprojekt.gruppe3.products.worm.controller;

import de.hsh.programmierprojekt.gruppe3.products.worm.model.Computer;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Execution;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateTrackerBase;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ObservableComputer extends StateTrackerBase<Computer> {
    private static final ConcurrentMap<Computer, ObservableComputer> instances = new ConcurrentHashMap<>();

    public ObservableComputer() {}

    private ObservableComputer(Computer computer) {
        super();
        set(computer);
        computer.addObserver(this::update);
    }

    public void runProgram(Execution execution) {
        var computation = get().run(execution);
        computation.addObserver(this::update);
        update();
    }

    public static ObservableComputer from(Computer computer) {
        return instances.computeIfAbsent(computer, ObservableComputer::new);
    }

    @Override
    protected Computer computeValue() {
        return get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTrackerBase<?> that = (StateTrackerBase<?>) o;
        return Objects.equals(get(), that.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get());
    }
}

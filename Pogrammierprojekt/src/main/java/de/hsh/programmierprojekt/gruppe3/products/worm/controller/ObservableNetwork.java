package de.hsh.programmierprojekt.gruppe3.products.worm.controller;

import de.hsh.programmierprojekt.gruppe3.products.worm.model.Computer;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Network;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateTrackerBase;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObservableNetwork extends StateTrackerBase<List<ObservableComputer>> {
    private final Network network;
    private final ObservableComputer selected = new ObservableComputer();

    public ObservableNetwork(Network network, Computer selected) {
        super();
        this.network = network;
        track(this.selected);
        this.selected.set(selected);
    }

    private Stream<Computer> content() {
        var selected = getSelected();
        return Stream.concat(Stream.ofNullable(selected), network.getVisibleNeighbours(selected).stream());
    }

    public List<ObservableComputer> getNeighbours() {
        return get().stream().filter(Predicate.not(selectedProperty()::equals))
                .collect(Collectors.toUnmodifiableList());
    }

    public Computer getSelected() {
        return selected.get();
    }

    public ObservableComputer selectedProperty() {
        return selected;
    }

    @Override
    protected List<ObservableComputer> computeValue() {
        return content().map(ObservableComputer::from).collect(Collectors.toUnmodifiableList());
    }
}

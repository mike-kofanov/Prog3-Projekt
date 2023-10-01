package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestNetworkFactory {
    private int count;
    private Network network;
    private List<Computer> computers;

    public synchronized Network createTestNetwork() {
        network = new Network();
        count = 0;
        computers = Stream.generate(this::createComputer).limit(9).collect(Collectors.toUnmodifiableList());
        network.addAll(computers);
        computers.stream().skip(1).limit(3).forEach(computer -> network.addConnection(computers.get(0), computer, false));
        computers.stream().skip(4).limit(3).forEach(computer -> network.addConnection(computers.get(3), computer, false));
        network.addConnection(computers.get(6), computers.get(7), true);
        network.addConnection(computers.get(6), computers.get(8), false);
        network.addConnection(computers.get(0), computers.get(8), false);
        return network;
    }

    private Computer createComputer() {
        var computer = new Computer(this.network, "Computer " + ++count, Collections.emptyList());
        computer.discover();
        return computer;
    }

    public List<Computer> getComputers() {
        return computers;
    }

}

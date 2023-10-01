package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NetworkFactory {

    private final int networkSize, minConnections, maxConnections;
    private final double openPortProbability;

    private List<Vulnerability> vulnerabilities;

    private Computer start;

    public NetworkFactory(int networkSize, int averageConnections, double connectionVariance, double openPortProbability) {
        this.networkSize = networkSize;
        var clampedVariance = (Math.max(0, connectionVariance) + 1);
        this.minConnections = (int) Math.rint((1 / clampedVariance) * averageConnections);
        this.maxConnections = (int) Math.rint(clampedVariance * averageConnections) + 1;
        this.openPortProbability = Util.clamp(openPortProbability, 0, 1);
    }

    public NetworkFactory() {
        this(100,6,.5, .05);
    }

    public Network getNewNetwork() {
        var network = new Network();
        var compFactory = new ComputerFactory(network, vulnerabilities);
        var computers = Stream.generate(compFactory::getRandomComputer).limit(networkSize).collect(Collectors.toList());
        var indices = IntStream.range(0, computers.size()).boxed().collect(Collectors.toList());
        for (Computer computer : computers) {
            network.add(computer);
            var connCount = getRandomConnectionCount();
            Collections.shuffle(indices);
            for(Integer i : indices) {
                if (network.getConnectionsFrom(computer).size() >= connCount)
                    break;
                var connectionCandidate = computers.get(i);
                if (computer == connectionCandidate)
                    continue;
                var isOpen = Common.random().nextDouble() < openPortProbability;
                if (network.getConnectionsFrom(connectionCandidate).size() < connCount)
                    network.addConnection(computer, connectionCandidate, isOpen);
            }
        }
        start = getStart(computers);
        return network;
    }

    private Computer getStart(List<Computer> computers) {
        var selected = computers.get(Common.random().nextInt(0, computers.size()));
        selected.escalatePrivileges();
        selected.escalatePrivileges();
        selected.discover();
        return selected;
    }

    private int getRandomConnectionCount() {
        return Common.random().nextInt(minConnections, maxConnections);
    }

    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public Computer getStart() {
        return start;
    }
}

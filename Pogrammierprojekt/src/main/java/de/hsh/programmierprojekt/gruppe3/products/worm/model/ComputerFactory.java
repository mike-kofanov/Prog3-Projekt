package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerFactory {
    private final Network network;
    private final List<Vulnerability> vulnerabilities;

    public ComputerFactory(Network network, List<Vulnerability> vulnerabilities) {
        this.network = network;
        this.vulnerabilities = vulnerabilities;
    }

    public Computer getRandomComputer() {
        return new Computer(network, generateName(), selectRandomVulnerabilities());
    }

    private String generateName() {
        var builder = new StringBuilder();
        for (int i = 0; i < 15; ++i) {
            if (i == 5 || i == 10)
                builder.append("-");
            builder.append(Common.getRandomNumeralOrLetter());
        }
        return builder.toString();
    }

    private Collection<Vulnerability> selectRandomVulnerabilities() {
        var count = Common.random().nextInt(6);
        if (count >= vulnerabilities.size())
            return vulnerabilities;
        var selected = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0; i < count; i++) {
            while (selected.contains(index))
                index = Common.random().nextInt(vulnerabilities.size());
            selected.add(index);
        }
        return selected.stream().map(vulnerabilities::get).collect(Collectors.toList());
    }
}

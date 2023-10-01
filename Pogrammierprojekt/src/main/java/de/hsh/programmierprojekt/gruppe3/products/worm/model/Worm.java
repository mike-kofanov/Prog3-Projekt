package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import java.util.*;

public class Worm {
    private final Set<Vulnerability> knownVulnerabilities = new LinkedHashSet<>();
    private double bitcoin;

    public Worm(Collection<Vulnerability> knownVulnerabilities) {
        this.knownVulnerabilities.addAll(knownVulnerabilities);
    }

    public void addVulnerability(Vulnerability vulnerability) {
        knownVulnerabilities.add(vulnerability);
    }

    public Collection<Vulnerability> getKnownVulnerabilities() {
        return Collections.unmodifiableCollection(knownVulnerabilities);
    }

    public double getBitcoin() {
        return bitcoin;
    }

    public void addBitcoin(double added) {
        bitcoin += added;
    }

    public boolean subtractBitcoin(double subtracted) {
        if (subtracted > bitcoin)
            return false;
        bitcoin -= subtracted;
        return true;
    }
}

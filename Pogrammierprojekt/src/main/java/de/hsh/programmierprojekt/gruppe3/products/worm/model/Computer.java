package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Observable;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Computer implements Observable {
    private final Collection<Runnable> observers = new ArrayList<>();
    private final Network network;
    private final String name;
    private final Collection<LocalVulnerability> vulnerabilities;
    private final Set<Program> installedPrograms = new HashSet<>();
    private final AtomicReference<PermissionLevel> permissionLevel = new AtomicReference<>(PermissionLevel.None);
    private final AtomicReference<Computation> runningComputation = new AtomicReference<>();
    private volatile boolean discovered;

    Computer(Network network, String name, Collection<Vulnerability> vulnerabilities) {
        this.network = network;
        this.name = name;
        this.vulnerabilities = vulnerabilities.stream().map(LocalVulnerability::new).collect(Collectors.toList());
    }

    private boolean applyOnSuccess(Execution execution) {
        var success = permissionLevel.get().checkSuccess(execution.program, execution.vulnerability);
        if (success)
            execution.program.accept(this, execution);
        runningComputation.set(null);
        useVulnerability(execution.vulnerability);
        notifyObservers();
        return success;
    }

    private void useVulnerability(Vulnerability vulnerability) {
        if (vulnerability != null)
            vulnerabilities.stream()
                    .filter(localVul -> localVul.getVulnerability().equals(vulnerability))
                    .findAny().ifPresent(LocalVulnerability::use);
        notifyObservers();
    }

    void install(Program program) {
        if (program.isInstallable())
            installedPrograms.add(program);
        notifyObservers();
    }

    public Set<Program> getInstalledPrograms() {
        return Collections.unmodifiableSet(installedPrograms);
    }

    public boolean isPermittedToRun(Execution execution) {
        return !isBusy() && permissionLevel.get().isPermittedToRun(execution.program, execution.vulnerability);
    }

    void escalatePrivileges() {
        permissionLevel.updateAndGet(PermissionLevel::nextLevel);
        notifyObservers();
    }

    void discover() {
        this.discovered = true;
        notifyObservers();
    }

    void discoverVulnerabilities(Collection<Vulnerability> knownVulnerabilities) {
        vulnerabilities.stream()
                .filter(vul -> knownVulnerabilities.contains(vul.getVulnerability()))
                .forEach(LocalVulnerability::discover);
        notifyObservers();
    }

    Collection<LocalVulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public Collection<Vulnerability> getUsableVulnerabilities() {
        return vulnerabilities.stream()
                .filter(Predicate.not(LocalVulnerability::isUsed))
                .filter(LocalVulnerability::isDiscovered)
                .map(LocalVulnerability::getVulnerability)
                .collect(Collectors.toList());
    }

    public Collection<Vulnerability> getUsedVulnerabilities() {
        return vulnerabilities.stream()
                .filter(LocalVulnerability::isUsed)
                .map(LocalVulnerability::getVulnerability)
                .collect(Collectors.toList());
    }

    public boolean isBusy() {
        return runningComputation.get() != null;
    }

    public Computation run(Execution execution) {
        if (isBusy() || !isPermittedToRun(execution))
            return null;
        if (runningComputation.compareAndSet(null, newComputation(execution)))
            return runningComputation.get();
        else
            return null;
    }

    private Computation newComputation(Execution execution) {
        var duration = permissionLevel.get().calculateRunTime(execution.program);
        return new Computation(duration, () -> applyOnSuccess(execution));
    }

    @Override
    public String toString() {
        return name;
    }

    public Network getNetwork() {
        return network;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public boolean isAccessible() {
        return !permissionLevel.get().equals(PermissionLevel.None);
    }

    public Computation getRunningComputation() {
        return runningComputation.get();
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel.get();
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<Runnable> getObservers() {
        return observers;
    }
}

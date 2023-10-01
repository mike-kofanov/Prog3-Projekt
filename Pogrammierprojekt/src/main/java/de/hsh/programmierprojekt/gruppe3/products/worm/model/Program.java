package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Program implements BiConsumer<Computer, Execution> {
    ScanPorts("Scan Ports", false, false, true, pl -> 1.05,
            (computer, execution) -> computer.getNetwork().getConnectionsFrom(computer).forEach(Computer::discover)),
    EscalatePrivileges("Escalate Privileges", false, false, true,
            pl -> pl.getPermissionLevel() + .5, (computer, execution) -> computer.escalatePrivileges()),
    ScanVulnerabilities("Scan Vulnerabilities", false, false, false, pl -> 0D,
            (computer, execution) -> computer.discoverVulnerabilities(execution.worm.getKnownVulnerabilities())),
    Phishing("Phishing", false, false, false, pl -> 1D,
            (computer, execution) -> computer.getNetwork().getVisibleNeighbours(computer).forEach(Program::phish)),
    SpearheadPhishing("Spearhead Phishing", false, true, false, pl -> 1D,
            (computer, execution) -> computer.getNetwork().getVisibleNeighbours(computer).forEach(Program::spearheadPhish));


    private final String name;
    private final boolean installable, hasTarget, usesVulnerability;
    private final Function<PermissionLevel, Double> permission;
    private final BiConsumer<Computer, Execution> action;

    Program(String name, boolean installable, boolean hasTarget, boolean usesVulnerability,
            Function<PermissionLevel, Double> permission, BiConsumer<Computer, Execution> action) {
        this.name = name;
        this.installable = installable;
        this.hasTarget = hasTarget;
        this.usesVulnerability = usesVulnerability;
        this.permission = permission;
        this.action = action;
    }

    public String getName() {
        return name().replaceAll("/([A-Z]+)/g"," $1").trim();
    }

    public boolean isInstallable() {
        return installable;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public boolean usesVulnerability() {
        return usesVulnerability;
    }

    public double getBaseRisk(PermissionLevel pl) {
        return permission.apply(pl);
    }

    @Override
    public void accept(Computer computer, Execution execution) {
        action.accept(computer, execution);
    }

    private static void phish(Computer computer) {
        phishInternal(computer, .8);
    }

    private static void spearheadPhish(Computer computer) {
        phishInternal(computer, .4);
    }

    private static void phishInternal(Computer computer, double risk) {
        if (!computer.isAccessible() && Common.random().nextDouble() > risk)
            computer.escalatePrivileges();
    }
}

package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum PermissionLevel {
    None(-1), Restricted(0), User(1), Root(2);

    private final double permissionLevel;

    PermissionLevel(double permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    PermissionLevel nextLevel() {
        var nextLevel = Stream.of(values()).dropWhile(Predicate.not(this::equals)).skip(1).findFirst();
        return nextLevel.orElse(Root);
    }

    boolean isPermittedToRun(Program program, Vulnerability vulnerability) {
        var risk = getEffectiveRisk(program, vulnerability);
        return this != None && risk < 1;
    }

    private double getEffectiveRisk(Program program, Vulnerability vulnerability) {
        var vulnerabilityValue = getVulnerabilityValue(vulnerability);
        var effectiveRisk = program.getBaseRisk(this) - permissionLevel - vulnerabilityValue;
        return Math.max(effectiveRisk, 0);
    }

    private double getVulnerabilityValue(Vulnerability vulnerability) {
        return vulnerability != null ? vulnerability.getValue() : 0;
    }

    int calculateRunTime(Program program) {
        var risk = program.getBaseRisk(this) - permissionLevel;
        var factor = 1 - (1 / (1 + (5 * risk)));
        var defaultDuration = 10;
        return (int) (defaultDuration * factor + 1) * 1000;
    }

    boolean checkSuccess(Program program, Vulnerability vulnerability) {
        var risk = getEffectiveRisk(program, vulnerability);
        return Common.random().nextDouble() > risk;
    }

    public double getPermissionLevel() {
        return permissionLevel;
    }

}

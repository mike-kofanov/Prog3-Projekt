package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import java.util.Optional;

public class Execution {
    public final Worm worm;
    public final Program program;
    public final Computer target;
    public final Vulnerability vulnerability;

    public Execution(Worm worm, Program program, Computer target, Vulnerability vulnerability) {
        this.worm = worm;
        this.program = program;
        this.target = target;
        this.vulnerability = vulnerability;
    }

    public Execution(Worm worm, Program program) {
        this(worm, program, null, null);
    }

    public Execution(Worm worm, Program program, Vulnerability vulnerability) {
        this(worm, program, null, vulnerability);
    }

    public Execution(Worm worm, Program program, Computer target) {
        this(worm, program, target, null);
    }
}

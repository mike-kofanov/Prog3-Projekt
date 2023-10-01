package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

public class Computation implements Observable {
    private final Collection<Runnable> observers = new ArrayList<>();
    private final ScheduledFuture<?> task;

    private final BooleanSupplier execution;
    private final int duration;

    private final AtomicLong elapsed = new AtomicLong();
    private volatile long lastRun = -1;
    private boolean success;

    Computation(int duration, BooleanSupplier execution) {
        this.duration = duration;
        this.execution = execution;
        this.task = Common.submitProcess(this::run);
    }

    public boolean isSuccess() {
        return success;
    }

    public double getProgress() {
        return Math.min(1, elapsed.get() / duration);
    }

    public int getDuration() {
        return duration;
    }

    private void run() {
        if (lastRun > 0)
            updateState();
        lastRun = System.currentTimeMillis();
    }

    public void updateState() {
        var current = elapsed.addAndGet(System.currentTimeMillis() - lastRun);
        if (current >= duration)
            done();
    }

    private void done() {
        task.cancel(true);
        success = execution.getAsBoolean();
        notifyObservers();
    }

    @Override
    public Collection<Runnable> getObservers() {
        return observers;
    }
}

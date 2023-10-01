package de.hsh.programmierprojekt.gruppe3.products.worm.util;

import java.util.concurrent.*;

public enum Common {
    instance;

    private final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);

    public static ScheduledFuture<?> submitProcess(Runnable run) {
        Runnable safeRun = () -> {
            try {
                run.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return instance.service.scheduleAtFixedRate(safeRun, 0, 200, TimeUnit.MILLISECONDS);
    }

    public static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    public static char getRandomNumeralOrLetter() {
        if (Common.random().nextDouble() < (1D / 3))
            return (char) (Common.random().nextInt(26) + 'a');
        else
            return (char) (Common.random().nextInt(10) + '0');
    }

    public static ScheduledExecutorService getService() {
        return instance.service;
    }
}

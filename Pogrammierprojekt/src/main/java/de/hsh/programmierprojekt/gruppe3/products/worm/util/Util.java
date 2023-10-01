package de.hsh.programmierprojekt.gruppe3.products.worm.util;

import de.hsh.programmierprojekt.gruppe3.products.worm.WormLauncher;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Util {
    private Util() {}

    public static double clamp(double num, double lower, double upper) {
        return Math.max(Math.min(num, upper), lower);
    }

    public static InputStream getResourceAsStream(String name) {
        return WormLauncher.class.getResourceAsStream(name);
    }

    public static URL getResource(String name) {
        return WormLauncher.class.getResource(name);
    }

    public static <T, U, R> Function<Map.Entry<T, U>, R> fromEntry(BiFunction<T, U, R> func) {
        return (entry -> func.apply(entry.getKey(), entry.getValue()));
    }

    public static EventHandler<WindowEvent> toEventHandler(SideEffect effect) {
        return event -> {
            event.consume();
            effect.execute();
        };
    }
}

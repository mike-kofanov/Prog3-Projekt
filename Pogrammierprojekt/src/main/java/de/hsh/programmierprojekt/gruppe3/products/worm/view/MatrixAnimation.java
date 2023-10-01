package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.Duration;
import java.util.Arrays;

public class MatrixAnimation extends AnimationTimer {
    private static final Color LETTER_FILL = Color.web("#0f0");
    private static final double COLUMN_RESTART_PROB = .05;

    private final String[] letters;
    private final int fontsize = 10;
    private final SimpleDoubleProperty width = new SimpleDoubleProperty();
    private final Effect effect = new ColorAdjust(0,0,-0.09,0);
    private long now;
    private Canvas canvas;
    private int[] drops = new int[100];

    public MatrixAnimation() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZアイウエオカキクケコタチツテトラリルレロマミムメモハヒフヘホサシスセソナニヌネノワヲヤユヨンABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.letters = letters.split("(?!^)");
        width.addListener((obs, oldVal, newVal) -> {
            drops = Arrays.copyOf(drops, (int)(newVal.doubleValue() / fontsize));
            if (oldVal.intValue() < newVal.intValue()) {
                Arrays.fill(drops, (int)(oldVal.doubleValue() / fontsize), drops.length, 10000);
            }
        });
    }

    public void setCanvas(Canvas canvas)  {
        this.canvas = canvas;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Noto Mono", fontsize));
        width.unbind();
        width.bind(canvas.widthProperty());
        Arrays.fill(drops, 10000);
    }

    @Override
    public void handle(long now) {
        if (canvas == null || now - this.now < Duration.ofMillis(33).toNanos())
            return;
        this.now = now;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.applyEffect(effect);
        gc.setFill(LETTER_FILL);
        for (int i = 0; i < drops.length; ++i) {
            String letter = letters[Common.random().nextInt(letters.length)];
            gc.fillText(letter, i * fontsize, drops[i] * fontsize);
            ++drops[i];
            gc.clearRect(i * fontsize, drops[i] * fontsize, fontsize, fontsize);
            if (drops[i] * fontsize > canvas.getHeight() && Common.random().nextDouble() < COLUMN_RESTART_PROB) {
                drops[i] = 0;
                gc.clearRect(i * fontsize, 0, fontsize, fontsize);
            }
        }
    }
}

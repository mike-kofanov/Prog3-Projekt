package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import javafx.scene.canvas.Canvas;

public class MatrixAnimationCanvas extends Canvas {

    private final MatrixAnimation animation = new MatrixAnimation();

    {
        animation.setCanvas(this);
        animation.start();
    }

    public MatrixAnimation getAnimation() {
        return animation;
    }
}

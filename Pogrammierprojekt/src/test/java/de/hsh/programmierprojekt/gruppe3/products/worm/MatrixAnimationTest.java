package de.hsh.programmierprojekt.gruppe3.products.worm;

import de.hsh.programmierprojekt.gruppe3.products.worm.view.MatrixAnimation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MatrixAnimationTest extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas();
        MatrixAnimation animation = new MatrixAnimation();
        animation.setCanvas(canvas);
        root.setCenter(canvas);
        root.setStyle("-fx-background-color: rgba(0,0,0,1);");


        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        stage.setTitle("Hello World!!!");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        stage.show();

        animation.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

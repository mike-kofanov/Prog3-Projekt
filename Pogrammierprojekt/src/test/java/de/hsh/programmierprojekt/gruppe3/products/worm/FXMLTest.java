package de.hsh.programmierprojekt.gruppe3.products.worm;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

public class FXMLTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Platform.setImplicitExit(true);
        Parent root = FXMLLoader.load(Util.getResource("worm.fxml"));
        stage.setScene(new Scene(root, 640, 480, false, SceneAntialiasing.DISABLED));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

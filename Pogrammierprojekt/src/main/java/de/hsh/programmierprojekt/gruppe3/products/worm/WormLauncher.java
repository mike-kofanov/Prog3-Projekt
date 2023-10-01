package de.hsh.programmierprojekt.gruppe3.products.worm;

import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class WormLauncher {
    public static Stage start(final Stage primaryStage) throws IOException {
        Stage wormStage = new Stage();
        Parent root = FXMLLoader.load(Util.getResource("worm.fxml"));
        wormStage.setScene(new Scene(root, 800, 600));
        wormStage.setOnShown(Util.toEventHandler(primaryStage::hide));
        wormStage.setOnHidden(Util.toEventHandler(primaryStage::show));
        wormStage.setOnHiding(Util.toEventHandler(Common.getService()::shutdown));
        return wormStage;
    }

    public static Background getBackground() {
        var image = new Image(Util.getResourceAsStream("computer_cropped.png"/*TODO*/), 100, 100, true, true);
        var backImage = new BackgroundImage(image, NO_REPEAT, NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        return new Background(backImage);
    }
}

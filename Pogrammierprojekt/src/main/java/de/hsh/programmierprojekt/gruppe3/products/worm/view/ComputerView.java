package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableComputer;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Computer;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ComputerView extends VBox {
    private static final Image RED = loadImage(Util.getResourceAsStream("computer_red.png"));
    private static final Image YELLOW = loadImage(Util.getResourceAsStream("computer_yellow.png"));
    private static final Image GREEN = loadImage(Util.getResourceAsStream("computer_green.png"));
    private static final ConcurrentMap<ObservableComputer, ComputerView> instances = new ConcurrentHashMap<>();

    @FXML
    private ImageView image;

    @FXML
    private Label label;

    private final ObservableComputer computer;

    private ComputerView(ObservableComputer computer) {
        super();
        this.computer = computer;
        var fxml = new FXMLLoader(Util.getResource("computer.fxml"));
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateImage();
        label.setText(computer.get().getName());
        computer.addListener(this::updateImage);
    }

    public void setSize(double size) {
        var limited = Util.clamp(size, 0, 1);
        image.setFitWidth(100 * limited);
    }

    private void updateImage() {
        Platform.runLater(this::updateImageInternal);
    }

    private void updateImageInternal() {
        var permissionLevel = computer.get().getPermissionLevel();
        switch (permissionLevel) {
            case None:
                image.setImage(RED);
                break;
            case Restricted:
                image.setImage(YELLOW);
                break;
            case User:
            case Root:
                image.setImage(GREEN);
                break;
        }
    }

    public Computer getComputer() {
        return computer.get();
    }

    public ObservableComputer computerProperty() {
        return computer;
    }

    public static ComputerView from(ObservableComputer computer) {
        return instances.computeIfAbsent(computer, ComputerView::new);
    }

    private static Image loadImage(InputStream stream) {
        return new Image(stream, 100, 100, true, true);
    }
}

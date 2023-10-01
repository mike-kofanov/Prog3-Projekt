package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableComputer;
import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableNetwork;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.*;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.StringConverter;

import java.io.IOException;

public class ProgramView extends VBox {
    private static final AudioClip clip;
    private final Worm worm;
    private final ObservableNetwork network;
    private final Program program;

    @FXML
    private Label programName;

    @FXML
    private Button button;

    @FXML
    private ChoiceBox<Vulnerability> vulnerabilities;

    @FXML
    private ChoiceBox<ObservableComputer> targets;

    public ProgramView(Worm worm, ObservableNetwork network, Program program) throws IOException {
        this.worm = worm;
        this.network = network;
        this.program = program;
        var loader = new FXMLLoader(Util.getResource("program.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        network.addListener(this::update);
    }

    private void update() {
        Platform.runLater(this::updateInternal);
    }

    private void updateInternal() {
        var disable = !network.getSelected().isPermittedToRun(new Execution(worm, program, getSelectedTarget(), getSelectedVulnerability()));
        if (button != null)
            button.setDisable(disable);
        if (vulnerabilities != null)
            vulnerabilities.getItems().setAll(network.getSelected().getUsableVulnerabilities());
        if (targets != null)
            targets.getItems().setAll(network.getNeighbours());
    }

    @FXML
    private void initialize() {
        programName.setText(program.getName());
        vulnerabilities.setVisible(program.usesVulnerability());
        targets.setVisible(program.hasTarget());
        vulnerabilities.setConverter(new VulnerabilityConverter());
        targets.setConverter(new StringConverter<ObservableComputer>() {
            @Override
            public String toString(ObservableComputer computer) {
                if (computer == null)
                    return "";
                return computer.get().getName();
            }

            @Override
            public ObservableComputer fromString(String name) {
                return network.getNeighbours().stream().filter(computer -> computer.get().getName().equals(name)).findAny().orElse(null);
            }
        });
        update();
    }

    @FXML
    private void runProgram(ActionEvent event) {
        event.consume();
        var vulnerability = getSelectedVulnerability();
        var target = getSelectedTarget();
        var execution = new Execution(worm, program, target, vulnerability);
        network.selectedProperty().runProgram(execution);
        clip.play();
    }

    private Vulnerability getSelectedVulnerability() {
        if (vulnerabilities.getSelectionModel().isEmpty())
            return null;
        return vulnerabilities.getValue();
    }

    private Computer getSelectedTarget() {
        if (targets.getSelectionModel().isEmpty())
            return null;
        return targets.getValue().get();
    }

    static {
        clip = new AudioClip(Util.getResource("grunz_success.wav").toExternalForm());
    }
}

package de.hsh.programmierprojekt.gruppe3.products.worm.controller;

import de.hsh.programmierprojekt.gruppe3.products.worm.model.NetworkFactory;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Program;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Vulnerability;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Worm;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Common;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.Util;
import de.hsh.programmierprojekt.gruppe3.products.worm.view.MatrixAnimationCanvas;
import de.hsh.programmierprojekt.gruppe3.products.worm.view.NetworkView;
import de.hsh.programmierprojekt.gruppe3.products.worm.view.ProgramView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WormController {
    private Worm worm;
    public ObservableNetwork network;

    @FXML
    public NetworkView netView;
    @FXML
    public MatrixAnimationCanvas backgroundAnimation;
    @FXML
    public VBox programList;

    @FXML
    public void initialize() {
        var vulnerabilities = initVulnerabilities();
        var netFactory = new NetworkFactory();
        netFactory.setVulnerabilities(vulnerabilities);
        var netModel = netFactory.getNewNetwork();
        network = new ObservableNetwork(netModel, netFactory.getStart());
        worm = new Worm(vulnerabilities);
        netView.setNetwork(network);
        try {
            initProgramViews();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    private void initProgramViews() throws IOException {
        for (var program : Program.values()) {
            var programView = new ProgramView(worm, network, program);
            programList.getChildren().add(programView);
        }
    }

    private List<Vulnerability> initVulnerabilities() {
        try (var in = Util.getResourceAsStream("vulnerabilities")) {
            var text = new String(in.readAllBytes()).split(System.lineSeparator());
            var fromFile = Arrays.stream(text).map(name -> new Vulnerability(name, randomValue()));
            var generated = Stream.generate(this::randomVulnerability);
            return Stream.concat(fromFile, generated).limit(40).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Vulnerability randomVulnerability() {
        var name = randomName();
        var value = randomValue();
        return new Vulnerability(name, value);
    }

    private String randomName() {
        return Stream.generate(Common::getRandomNumeralOrLetter).limit(8).map(String::valueOf).collect(Collectors.joining());
    }

    private double randomValue() {
        return Common.random().nextDouble(2);
    }

}

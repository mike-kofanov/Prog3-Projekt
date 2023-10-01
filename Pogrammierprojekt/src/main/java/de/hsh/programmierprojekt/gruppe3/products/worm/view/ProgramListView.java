package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableComputer;
import de.hsh.programmierprojekt.gruppe3.products.worm.controller.WormController;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Program;
import de.hsh.programmierprojekt.gruppe3.products.worm.model.Vulnerability;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class ProgramListView extends VBox {
    private final ObjectProperty<ObservableComputer> selected = new SimpleObjectProperty<>();
    private final ListProperty<Program> availablePrograms = new SimpleListProperty<>();
    private final ListProperty<ObservableComputer> availableTargets = new SimpleListProperty<>();
    private final ListProperty<Vulnerability> knownVulnerabilities = new SimpleListProperty<>();

    public ObservableList<ObservableComputer> getAvailableTargets() {
        return availableTargets.get();
    }

    public ListProperty<ObservableComputer> availableTargetsProperty() {
        return availableTargets;
    }

    public ObservableList<Vulnerability> getKnownVulnerabilities() {
        return knownVulnerabilities.get();
    }

    public ListProperty<Vulnerability> knownVulnerabilitiesProperty() {
        return knownVulnerabilities;
    }

    public ObservableList<Program> getAvailablePrograms() {
        return availablePrograms.get();
    }

    public ListProperty<Program> availableProgramsProperty() {
        return availablePrograms;
    }
}

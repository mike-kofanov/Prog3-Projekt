package de.hsh.programmierprojekt.gruppe3.products.worm.view;

import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableNetwork;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateTrackerBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NetworkView extends Region {
    private final NetworkBinding contentBinding = new NetworkBinding();

    private ObservableNetwork network;

    private void handle(MouseEvent event) {
        if (!(event.getSource() instanceof ComputerView))
            return;
        var computer = (ComputerView) event.getSource();
        network.selectedProperty().set(computer.getComputer());
        System.out.println(computer.getComputer().getName());
    }

    public void setNetwork(ObservableNetwork network) {
        this.network = network;
        contentBinding.setNetwork(this.network);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        if (network == null)
            return;
        var selected = contentBinding.getSelected();
        var neighbours = contentBinding.getNeighbours();
        var bounds = getLayoutBounds();
        selected.setSize(1);
        var centerX = bounds.getCenterX();
        var centerY = bounds.getCenterY();
        selected.relocate(centerX - 50, centerY - 50);
        var radius = Math.min(bounds.getHeight(), bounds.getWidth()) / 3;
        var count = neighbours.size();
        var iterator = neighbours.iterator();
        for (double i = 0; iterator.hasNext(); ++i) {
            var theta = 2 * Math.PI * (i / count);
            var x = centerX + (radius * Math.cos(theta));
            var y = centerY + (radius * Math.sin(theta));
            var node = iterator.next();
            node.relocate(x - 25, y - 25);
            node.setSize(.5);
        }
    }



    private class NetworkBinding extends StateTrackerBase<List<ComputerView>> {
        private ObservableNetwork network;

        public ComputerView getSelected() {
            return selected;
        }

        public Collection<ComputerView> getNeighbours() {
            return neighbours;
        }

        private ComputerView selected;
        private Collection<ComputerView> neighbours;

        @Override
        protected synchronized List<ComputerView> computeValue() {
            if (network == null) {
                getChildren().clear();
                return Collections.emptyList();
            }
            selected = ComputerView.from(network.selectedProperty());
            var computers = network.get().stream()
                    .map(ComputerView::from)
                    .collect(Collectors.toUnmodifiableList());
            neighbours = computers.stream().filter(Predicate.not(selected::equals)).collect(Collectors.toUnmodifiableList());
            computers.forEach(node -> node.setOnMouseClicked(NetworkView.this::handle));
            Platform.runLater(() -> {
                getChildren().setAll(computers);
                setNeedsLayout(true);
            });
            return computers;
        }

        public void setNetwork(ObservableNetwork network) {
            if (this.network != null)
                untrack(this.network);
            this.network = network;
            if (this.network != null) {
                track(this.network);
            }
            computeValue();
        }
    }
}

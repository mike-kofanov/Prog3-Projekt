package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableComputer;
import de.hsh.programmierprojekt.gruppe3.products.worm.controller.ObservableNetwork;
import de.hsh.programmierprojekt.gruppe3.products.worm.util.StateTracker;
import javafx.beans.binding.ObjectBinding;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static de.hsh.programmierprojekt.gruppe3.products.worm.model.PermissionLevel.None;
import static de.hsh.programmierprojekt.gruppe3.products.worm.model.PermissionLevel.Restricted;
import static de.hsh.programmierprojekt.gruppe3.products.worm.model.PermissionLevel.User;
import static de.hsh.programmierprojekt.gruppe3.products.worm.model.PermissionLevel.Root;

public class ComputerTest {

    @Test
    public void testNetwork() {
        var testGen = new TestNetworkFactory();
        var network = testGen.createTestNetwork();
        var computers = testGen.getComputers();
        var observable = new ObservableNetwork(network, network.getComputers().stream().findFirst().orElseThrow());
        var expected = new Computer[] {computers.get(1), computers.get(2), computers.get(3), computers.get(8)};
        assertArrayEquals(expected ,observable.getNeighbours().stream().map(StateTracker::get).toArray());
        observable.selectedProperty().set(computers.get(3));
        expected = new Computer[] {computers.get(0), computers.get(4), computers.get(5), computers.get(6)};
        assertArrayEquals(expected, observable.getNeighbours().stream().map(StateTracker::get).toArray());
        observable.selectedProperty().set(computers.get(6));
        expected = new Computer[] {computers.get(3), computers.get(7), computers.get(8)};
        assertArrayEquals(expected, observable.getNeighbours().stream().map(StateTracker::get).toArray());
    }
}

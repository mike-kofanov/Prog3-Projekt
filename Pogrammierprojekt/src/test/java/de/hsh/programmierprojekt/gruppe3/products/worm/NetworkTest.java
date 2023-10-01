package de.hsh.programmierprojekt.gruppe3.products.worm;

import de.hsh.programmierprojekt.gruppe3.products.worm.model.NetworkFactory;

public class NetworkTest {
    public static void main(String[] args) {
        var network = new NetworkFactory().getNewNetwork();
        System.out.println(network);
    }
}

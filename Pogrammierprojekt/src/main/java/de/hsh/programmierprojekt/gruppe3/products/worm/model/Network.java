package de.hsh.programmierprojekt.gruppe3.products.worm.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Network {

    private final Map<Computer, Collection<Connection>> connections = new LinkedHashMap<>();

    Network() {}

    public Collection<Computer> getComputers() {
        return Collections.unmodifiableCollection(connections.keySet());
    }

    public Collection<Computer> getConnectionsFrom(Computer computer) {
        return safely(computer, this::findNeighbours);
    }

    private List<Computer> findNeighbours(Computer computer) {
        return connections.get(computer).stream().map(Connection::getComputer).collect(Collectors.toList());
    }

    public Collection<Computer> getVisibleNeighbours(Computer computer) {
        return safely(computer, this::findVisibleNeighbours);
    }

    private List<Computer> findVisibleNeighbours(Computer computer) {
        return connections.get(computer).stream().map(Connection::getComputer).filter(Computer::isDiscovered).collect(Collectors.toList());
    }

    public Collection<Computer> getPrivilegedConnectionsFrom(Computer computer) {
        return safely(computer, this::findPrivilegedConnections);
    }

    private List<Computer> findPrivilegedConnections(Computer computer) {
        return connections.get(computer).stream().filter(Connection::isOpenPort).map(Connection::getComputer).collect(Collectors.toList());
    }

    private Collection<Computer> safely(Computer computer, Function<Computer, Collection<Computer>> func) {
        if (computer != null && connections.containsKey(computer))
            return func.apply(computer);
        else
            return Collections.emptyList();
    }

    void add(Computer computer) {
        connections.putIfAbsent(computer, new LinkedHashSet<>());
    }

    void addAll(Collection<Computer> computers) {
        computers.forEach(this::add);
    }

    void addConnection(Computer c1, Computer c2, boolean isOpenPort) {
        if (c1 == null || c2 == null || c1 == c2)
            return;
        add(c1);
        add(c2);
        connections.get(c1).add(new Connection(c2, isOpenPort));
        connections.get(c2).add(new Connection(c1, isOpenPort));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        for (Computer c : connections.keySet()) {
            sb.append(c).append(" is connected to ").append(connections.get(c).size()).append(" computers: ");
            for (Connection conn : connections.get(c)) {
                sb.append(conn.c);
                if (conn.isOpenPort)
                    sb.append(" with ports open");
                sb.append(", ");
            }
            sb.replace(sb.length() - 1, sb.length(), System.lineSeparator());
        }
        return sb.toString();
    }

    protected static class Connection {
        private final Computer c;
        private final boolean isOpenPort;

        protected Connection(Computer c, boolean isOpenPort) {
            if (c == null)
                throw new IllegalArgumentException();
            this.c = c;
            this.isOpenPort = isOpenPort;
        }

        boolean isOpenPort() {
            return isOpenPort;
        }

        Computer getComputer() {
            return c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Connection that = (Connection) o;
            return c.equals(that.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }
}

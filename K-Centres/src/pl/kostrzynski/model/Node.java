package pl.kostrzynski.model;

import java.util.Objects;

public class Node {

    private final Point first;
    private final Point second;
    private double weight;

    public Node(Point first, Point second, double weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public boolean isSmallerOrEqualDistance(final Node that) {
        return this.weight() <= that.weight();
    }

    public Point first() {
        return first;
    }

    public double weight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Double.compare(node.weight, weight) == 0 && first.equals(node.first) && second.equals(node.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, weight);
    }

    @Override
    public String toString() {
        return "Node{" +
                "first=" + first +
                ", second=" + second +
                ", weight=" + weight +
                '}';
    }
}

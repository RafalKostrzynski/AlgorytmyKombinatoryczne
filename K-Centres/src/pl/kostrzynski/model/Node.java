package pl.kostrzynski.model;

import java.util.Objects;

public record Node(Point first, Point second, double weight) {

    public boolean isSmallerOrEqualDistance(final Node that) {
        return this.weight() <= that.weight();
    }
}

package pl.kostrzynski.model;

public record Node(Point first, Point second, double distance) {

    public boolean isSmallerOrEqualDistance(final Node that) {
        return this.distance() <= that.distance();
    }

}

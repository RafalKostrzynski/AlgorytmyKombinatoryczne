package model;

public enum Orientation {

    POSITIVE, NEGATIVE, ZERO;

    public static Orientation calculateOrientation(final Point p1, final Point p2, final Point p3) {

        double orientation = (p2.y() - p1.y()) * (p3.x() - p2.x()) - (p2.x() - p1.x()) * (p3.y() - p2.y());

        return Orientation.getOrientation(orientation);
    }

    private static Orientation getOrientation(final double value) {

        if (value == 0) return ZERO;

        return value > 0 ? POSITIVE : NEGATIVE;
    }
}

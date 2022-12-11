package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;

public final class DistanceBetweenPoints {

    private DistanceBetweenPoints() {
    }

    public static double calculate(final Point a, final Point b) {

        return Math.sqrt(Math.pow(b.y() - a.y(), 2) + Math.pow(a.x() - b.x(), 2) + Math.pow(a.z() - b.z(), 2));
    }
}

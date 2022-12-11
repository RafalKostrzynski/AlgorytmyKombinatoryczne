package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;

public final class DistanceBetweenPoints {

    private DistanceBetweenPoints() {
    }

    public static double calculate(final Point a, final Point b) {

        return Math.hypot(Math.abs(b.y() - a.y()), Math.abs(b.x() - a.x()));
    }
}

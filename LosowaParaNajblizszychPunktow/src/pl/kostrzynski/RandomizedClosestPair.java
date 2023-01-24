package pl.kostrzynski;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointPair;
import pl.kostrzynski.service.DistanceBetweenPoints;

import java.util.*;

public class RandomizedClosestPair {

    private Point first;
    private Point second;

    public PointPair findRandomized(final ArrayList<Point> pointList) {

        Collections.shuffle(pointList);

        return this.find(pointList);
    }

    public PointPair find(ArrayList<Point> pointList) {

        final var points = new ArrayList<>(pointList);

        first = points.get(0);
        second = points.get(1);
        double delta = DistanceBetweenPoints.calculate(first, second);

        Grid grid = new Grid(delta);
        grid.addPoint(first);
        grid.addPoint(second);

        for (int i = 2; i < points.size(); i++) {
            Point pi = points.get(i);
            final double oldDelta = delta;
            delta = grid.minDistToNeighbors(pi, delta);
            if (delta == oldDelta) {
                grid.addPoint(pi);
            } else {
                grid = new Grid(delta);
                grid.addPoints(points.subList(0, i));
            }
        }
        return new PointPair(first, second, delta);
    }

    private class Grid {
        private final double delta;
        private final Map<Point, ArrayList<Point>> pointMap = new HashMap<>();

        public Grid(double delta) {
            this.delta = delta;
        }

        void addPoints(List<Point> points) {
            points.forEach(this::addPoint);
        }

        void addPoint(Point p) {
            int x = (int) (p.x() / delta);
            int y = (int) (p.y() / delta);

            final var point = new Point(x, y);
            if (pointMap.containsKey(point)) {
                pointMap.get(point).add(p);
            } else {
                pointMap.put(point, new ArrayList<>(List.of(p)));
            }
        }

        double minDistToNeighbors(Point p, double minDist) {
            int x = (int) (p.x() / delta);
            int y = (int) (p.y() / delta);

            for (int i = Math.max(0, x - 1); i <= x + 1; i++) {
                for (int j = Math.max(0, y - 1); j <= y + 1; j++) {
                    final var points = pointMap.get(new Point(i, j));
                    if (points == null) continue;
                    for (final var w : points) {
                        if (p.equals(w)) continue;
                        double dist = DistanceBetweenPoints.calculate(p, w);
                        if (dist != 0 && dist < minDist) {
                            minDist = dist;
                            first = p;
                            second = w;
                        }
                    }
                }
            }
            return minDist;
        }

    }
}

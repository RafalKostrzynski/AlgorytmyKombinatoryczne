package pl.kostrzynski;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointPair;
import pl.kostrzynski.service.DistanceBetweenPoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                grid.addPoints(points);
//                grid.addPoint(first);
//                grid.addPoint(second);
            }
        }
        return new PointPair(first, second, delta);
    }

    private class Grid {
        private final double delta;
        private final Map<Point, ArrayList<Point>> pointMap;

        public Grid(double delta) {
            this.delta = delta;
            pointMap = new HashMap<>();
        }

        void addPoints(List<Point>points){
            points.forEach(this::addPoint);
        }

        void addPoint(Point p) {

            final Point index = calculateIndex(p);
            if (pointMap.containsKey(index)) {
                pointMap.get(index).add(p);
            } else {
                pointMap.put(index, new ArrayList<>(List.of(p)));
            }
        }

        private Point calculateIndex(Point p) {
            int xStar = (int) ((int)(p.x() / delta) * p.x());
            int yStar = (int) ((int)(p.y() / delta) * p.y());

            int x = (int) (xStar / delta);
            int y = (int) (yStar / delta);

            return new Point(x, y);
        }

        double minDistToNeighbors(Point p, double minDist) {

            final Point index = calculateIndex(p);
            final int x = (int) index.x();
            final int y = (int) index.y();

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

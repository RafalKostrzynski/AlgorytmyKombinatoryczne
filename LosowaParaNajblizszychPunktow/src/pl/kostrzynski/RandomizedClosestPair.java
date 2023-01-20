package pl.kostrzynski;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointPair;
import pl.kostrzynski.service.DistanceBetweenPoints;

import java.util.ArrayList;
import java.util.Collections;

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
                grid.clear();
                grid = new Grid(delta);
                grid.addPoint(pi);
            }
        }
        return new PointPair(first, second, delta);
    }

    private class Grid {
        private final ArrayList<ArrayList<ArrayList<Point>>> blocks;
        private final double delta;

        public Grid(double delta) {
            this.delta = delta;
            blocks = new ArrayList<>();
        }

        void addPoint(Point p) {
            int x = (int) (p.x() / delta);
            int y = (int) (p.y() / delta);

            if (blocks.size() <= x) {
                for (int i = blocks.size(); i <= x; i++) {
                    blocks.add(new ArrayList<>());
                }
            }
            if (blocks.get(x).size() <= y) {
                for (int i = blocks.get(x).size(); i <= y; i++) {
                    blocks.get(x).add(new ArrayList<>());
                }
            }

            blocks.get(x).get(y).add(p);
        }

        double minDistToNeighbors(Point p, double minDist) {
            int x = (int) (p.x() / delta);
            int y = (int) (p.y() / delta);

            for (int i = Math.max(0, x - 1); i <= Math.min(blocks.size() - 1, x + 1); i++) {
                for (int j = Math.max(0, y - 1); j <= Math.min(blocks.get(i).size() - 1, y + 1); j++) {
                    for (Point q : blocks.get(i).get(j)) {
                        double dist = DistanceBetweenPoints.calculate(p, q);
                        if (dist < minDist) {
                            minDist = dist;
                            first = p;
                            second = q;
                        }
                    }
                }
            }
            return minDist;
        }

        void clear() {
            blocks.clear();
        }

    }
}

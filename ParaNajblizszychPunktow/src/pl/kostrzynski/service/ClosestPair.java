package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointTuple;
import pl.kostrzynski.comperator.PointXComparator;
import pl.kostrzynski.comperator.PointYComparator;

import java.util.List;

public class ClosestPair {

    private Point first = null;
    private Point second = null;

    private double min = Integer.MAX_VALUE;

    public PointTuple findClosestPoints(final List<Point> points) {
        if (points.isEmpty()) {
            System.out.println("No points provided!");
            return new PointTuple(new Point(0, 0), new Point(0, 0));
        }

        final var pointsListSize = points.size();
        if (pointsListSize == 1) return new PointTuple(points.get(0), points.get(0));

        final var xSortedList = points.stream()
                .sorted(new PointXComparator())
                .toArray(Point[]::new);
        final var ySortedList = points.stream()
                .sorted(new PointYComparator())
                .toArray(Point[]::new);

        this.findClosestPoints(xSortedList, ySortedList, new Point[pointsListSize], 0, pointsListSize - 1);

        return new PointTuple(first, second);
    }

    private double findClosestPoints(final Point[] xSortedArray, final Point[] ySortedArray, final Point[] acc,
                                     final int start, final int end) {

        if (end <= start) return Double.POSITIVE_INFINITY;

        final int middle = start + (end - start) / 2;
        final var middlePoint = xSortedArray[middle];

        final var leftDistance = findClosestPoints(xSortedArray, ySortedArray, acc, start, middle);
        final var rightDistance = findClosestPoints(xSortedArray, ySortedArray, acc, middle + 1, end);

        var delta = Math.min(leftDistance, rightDistance);

        merge(ySortedArray, acc, start, middle, end);

        return calculateNewDelta(ySortedArray, acc, start, end, middlePoint, delta);
    }

    private double calculateNewDelta(Point[] ySortedArray, Point[] acc, int start, int end, Point middlePoint, double delta) {
        int numberOfPointsInIntersection = 0;
        for (int i = start; i <= end; i++) {
            if (Math.abs(ySortedArray[i].x() - middlePoint.x()) < delta)
                acc[numberOfPointsInIntersection++] = ySortedArray[i];
        }

        for (int i = 0; i < numberOfPointsInIntersection; i++) {
            for (int j = i + 1; (j < numberOfPointsInIntersection) && (acc[j].y() - acc[i].y() < delta); j++) {
                double distance = DistanceBetweenPoints.calculate(acc[i], acc[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < min) {
                        min = delta;
                        first = acc[i];
                        second = acc[j];
                    }
                }
            }
        }
        return delta;
    }

    private static void merge(Point[] ySortedArray, Point[] acc, int start, int middle, int end) {

        if (end + 1 - start >= 0) System.arraycopy(ySortedArray, start, acc, start, end + 1 - start);

        int i = start, j = middle + 1;
        for (int k = start; k <= end; k++) {
            if (i > middle) ySortedArray[k] = acc[j++];
            else if (j > end) ySortedArray[k] = acc[i++];
            else if (acc[j].x() < acc[i].x()) ySortedArray[k] = acc[j++];
            else ySortedArray[k] = acc[i++];
        }
    }

}

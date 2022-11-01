package pl.kostrzynski;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointTuple;
import pl.kostrzynski.service.ClosestPair;
import pl.kostrzynski.service.DistanceBetweenPoints;
import pl.kostrzynski.service.PointSetCreator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nFinding closest point for a 8x8 Set:");
        execute(PointSetCreator.create8x8Set());

        System.out.println("\nFinding closest point for a Slash Set:");
        execute(PointSetCreator.createSlash());

        System.out.println("\nFinding closest point for a vertical Set:");
        execute(PointSetCreator.createVerticalLine());

        System.out.println("\nFinding closest point for a TestSet with points outside the intersection:");
        execute(PointSetCreator.createTestSet());

        System.out.println("\nFinding closest point for a TestSet with points inside the intersection:");
        execute(PointSetCreator.createTestSetWithValuesInTheIntersection());
    }

    private static void execute(List<Point> points) {
        System.out.println(points);

        final var closestPair = new ClosestPair();
        final var closestPoints = closestPair.findClosestPoints(points);

        System.out.printf("Closest points are: %s %s %n", closestPoints.first(), closestPoints.second());
        final var distanceClosestPointsTestSet = DistanceBetweenPoints.calculate(closestPoints.first(), closestPoints.second());
        System.out.printf("Distance: %s%n%n", distanceClosestPointsTestSet);

        System.out.println("BruteForce finder:");
        double min = bruteForce(points);
        System.out.printf("Distance: %s%n", min);
    }

    private static double bruteForce(List<Point> testSet) {
        var min = Double.MAX_VALUE;
        var pair = new PointTuple(testSet.get(0), testSet.get(1));
        for (int i = 0; i < testSet.size(); i++) {
            for (int j = 0; j < testSet.size(); j++) {
                if (i == j) continue;
                final var curr = DistanceBetweenPoints.calculate(testSet.get(i), testSet.get(j));
                if (min != Math.min(min, curr)) {
                    min = curr;
                    pair = new PointTuple(testSet.get(i), testSet.get(j));
                }
            }
        }
        System.out.println(pair);
        return min;
    }
}

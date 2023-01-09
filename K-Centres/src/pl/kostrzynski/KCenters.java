package pl.kostrzynski;

import pl.kostrzynski.model.Node;
import pl.kostrzynski.model.Point;
import pl.kostrzynski.service.DistanceBetweenPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KCenters {

    public List<Point> find(final List<Point> points, final int k) {

        if (points.size() <= k) return points;

        final var pointsList = new ArrayList<>(points);
        final var firstPoint = pointsList.get(0);
        final var centers = new ArrayList<>(List.of(firstPoint));
        pointsList.remove(firstPoint);

        var distances = getDistanceToEveryPoint(pointsList, centers.get(0));

        for (int i = 0; i < k - 1; i++) {
            int maxIndex = 0;
            double maxDistance = Double.MAX_VALUE;

            for (int j = 0; j < distances.size(); j++) {
                if (distances.get(j) != 0 && maxDistance < distances.get(j)) {
                    maxIndex = j;
                    maxDistance = distances.get(j);
                }
            }

            final var point = pointsList.get(maxIndex);
            centers.add(point);
            pointsList.remove(point);
            distances.remove(maxIndex);
            distances = getDistanceToEveryPoint(pointsList, distances, point);
        }

        return centers;
    }

    public List<Point> find(final Map<Point, ArrayList<Node>> map, final int k) {

        final var pointsList = new ArrayList<>(map.keySet());

        if (pointsList.size() < 2) return pointsList;

        final var amountOfPoints = pointsList.size();
        final List<Point> centers = new ArrayList<>();
        final ArrayList<Node> nodes = new ArrayList<>(
                pointsList.stream()
                        .map(e -> new Node(new Point(0, 0, 0), new Point(0, 0, 0), Double.MAX_VALUE))
                        .toList()
        );

        var maxDistanceIndex = 0;
        for (int i = 0; i < k; i++) {
            centers.add(pointsList.get(maxDistanceIndex));

            for (int j = 0; j < amountOfPoints; j++) {
                final var candidate = map.get(pointsList.get(j))
                        .get(maxDistanceIndex);

                final var node = nodes.get(j);
                nodes.set(j, node.isSmallerOrEqualDistance(candidate) ? node : candidate);
            }
            Node o = nodeWithMaxDistanceToCenter(nodes);
            System.out.println(o);
            o.setWeight(0);
            maxDistanceIndex = nodes.indexOf(o);
        }

        return centers;
    }

    private ArrayList<Double> getDistanceToEveryPoint(final List<Point> pointList, final Point point) {

        return new ArrayList<>(
                pointList.stream()
                        .map(e -> DistanceBetweenPoints.calculate(point, e))
                        .toList()
        );
    }

    private ArrayList<Double> getDistanceToEveryPoint(final List<Point> pointList, final List<Double> distances,
                                                      final Point point) {

        final var newDistances = new ArrayList<Double>();

        for (int i = 0; i < pointList.size(); i++) {
            double calculate = DistanceBetweenPoints.calculate(pointList.get(i), point);
            double min = Math.min(calculate, distances.get(i));
            newDistances.add(min);
        }

        return newDistances;
    }

    private Node nodeWithMaxDistanceToCenter(final ArrayList<Node> nodes) {

        return nodes.stream()
                .reduce((a, b) -> a.isSmallerOrEqualDistance(b) ? b : a)
                .orElseThrow();
    }
}

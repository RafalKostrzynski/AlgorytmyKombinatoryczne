package pl.kostrzynski;

import pl.kostrzynski.model.Node;
import pl.kostrzynski.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KCenters {

    public List<Point> find(final Map<Point, List<Node>> map, final int k) {

        final var pointsList = new ArrayList<>(map.keySet());

        if (pointsList.size() < 2) return pointsList;

        final var amountOfPoints = pointsList.size();
        final List<Point> centers = new ArrayList<>();
        final ArrayList<Node> nodes = new ArrayList<>(
                pointsList.stream()
                        .map(e -> new Node(new Point(0, 0), new Point(0, 0), Double.MAX_VALUE))
                        .toList()
        );


        var maxDistancePoint = 0;
        for (int i = 0; i < k; i++) {
            centers.add(pointsList.get(maxDistancePoint));

            for (int j = 0; j < amountOfPoints; j++) {
                final var node = map.get(pointsList.get(j))
                        .get(maxDistancePoint);

                nodes.set(j, nodes.get(j).isSmallerOrEqualDistance(node) ? nodes.get(j) : node);
            }
            Node o = nodeWithMaxDistanceToCenter(nodes);
            maxDistancePoint = nodes.indexOf(o);
            System.out.println(o);
        }

        return centers;
    }

    private Node nodeWithMaxDistanceToCenter(final ArrayList<Node> nodes) {

        return nodes.stream()
                .reduce((a, b) -> a.isSmallerOrEqualDistance(b) ? b : a)
                .orElseThrow();
    }
}

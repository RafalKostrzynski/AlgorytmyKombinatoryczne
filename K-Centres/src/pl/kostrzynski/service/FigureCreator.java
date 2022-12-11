package pl.kostrzynski.service;

import pl.kostrzynski.model.Node;
import pl.kostrzynski.model.Point;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class FigureCreator {

    private static final Random random = new Random();

    private FigureCreator() {
    }

    public static List<Point> createPolygon() {
        return IntStream.range(0, 100)
                .mapToObj(e -> List.of(
                                new Point(random.nextInt(0, 20), random.nextInt(0, 20)),
                                new Point(random.nextInt(40, 60), random.nextInt(40, 60)),
                                new Point(random.nextInt(80, 100), random.nextInt(80, 100))
                        )
                )
                .flatMap(List::stream)
                .distinct()
                .sorted(new PointXComparator())
                .toList();
    }

    public static Map<Point, List<Node>> createMap(final List<Point> points) {

        return points.stream()
                .collect(Collectors.toMap(
                                point -> point, point -> points.stream()
                                        .map(e -> new Node(
                                                point,
                                                e,
                                                DistanceBetweenPoints.calculate(point, e))
                                        )
                                        .toList()
                        )
                );
    }

}

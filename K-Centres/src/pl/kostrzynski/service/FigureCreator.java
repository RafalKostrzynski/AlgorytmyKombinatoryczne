package pl.kostrzynski.service;

import pl.kostrzynski.model.Node;
import pl.kostrzynski.model.Point;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        return IntStream.range(0, 50)
                .mapToObj(e -> List.of(
                                new Point(random.nextInt(0, 30), random.nextInt(0, 30), random.nextInt(0, 50)),
                                new Point(random.nextInt(40, 70), random.nextInt(40, 70), random.nextInt(0, 50)),
                                new Point(random.nextInt(80, 100), random.nextInt(80, 100), random.nextInt(0, 50)),
                                new Point(random.nextInt(80, 100), random.nextInt(20), random.nextInt(0, 50))
                        )
                )
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public static Map<Point, ArrayList<Node>> createMap(final List<Point> points) {

        final var map = points.stream()
                .collect(Collectors.toMap(
                                point -> point, point -> points.stream()
                                        .map(e -> new Node(
                                                        point,
                                                        e,
                                                        DistanceBetweenPoints.calculate(point, e) + random.nextDouble(10)
                                                )
                                        )
                                        .toList()
                        )
                );

        var sortedMap = new LinkedHashMap<Point, ArrayList<Node>>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(new PointXComparator()))
                .forEachOrdered(e -> sortedMap.put(e.getKey(), new ArrayList<>(e.getValue())));
        return sortedMap;
    }

}

package pl.kostrzynski.service;

import pl.kostrzynski.comperator.PointXComparator;
import pl.kostrzynski.model.Point;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PointListCreator {

    private static final Random random = new Random();

    private PointListCreator() {
    }

    public static List<Point> createRandomPoints() {
        return IntStream.range(0, 1000).mapToObj(e ->
                        new Point(random.nextInt(10000), random.nextInt(10000))
                ).distinct()
                .toList();
    }

    public static List<Point> sortPoints(List<Point> points) {
        return points.stream()
                .sorted(new PointXComparator())
                .toList();
    }
}

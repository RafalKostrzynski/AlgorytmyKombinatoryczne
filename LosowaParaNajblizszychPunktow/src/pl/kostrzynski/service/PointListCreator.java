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

        final var p1 = new Point(3,0);
        final var p2 = new Point(0,2);
        final var p3 = new Point(3,3);
        final var p4 = new Point(1,2);
        final var p5 = new Point(2,4);
        final var p6 = new Point(0,0);
        final var p7 = new Point(3,2);
        final var p8 = new Point(1,3);
        return List.of(p1, p2, p3, p4, p5,p6,p7,p8);

//        return IntStream.range(0, 10).mapToObj(e ->
//                        new Point(random.nextInt(5), random.nextInt(5))
//                ).distinct()
//                .toList();
    }

    public static List<Point> sortPoints(List<Point> points) {
        return points.stream()
                .sorted(new PointXComparator())
                .toList();
    }
}

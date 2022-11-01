package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;

import java.util.List;
import java.util.stream.IntStream;

public final class PointSetCreator {

    private PointSetCreator() {
    }

    public static List<Point> create8x8Set() {
        return IntStream.range(0, 64).mapToObj(e ->
                new Point(e % 8, e / 8)
        ).toList();
    }

    public static List<Point> createSlash() {
        return IntStream.range(0, 64).mapToObj(e ->
                new Point(e, e)
        ).toList();
    }

    public static List<Point> createVerticalLine() {
        return IntStream.range(0, 64).mapToObj(e ->
                new Point(1, e)
        ).toList();
    }

    public static List<Point> createTestSetWithValuesInTheIntersection() {

        final var point1 = new Point(5.0, 31.0);
        final var point2 = new Point(0.0, 16.0);
        final var point3 = new Point(30.0, 3.0);
        final var point4 = new Point(30.5, 35.5);
        final var point5 = new Point(29.5, 36);
        return getPoints(point1, point2, point3, point4, point5);
    }

    public static List<Point> createTestSet() {

        final var point1 = new Point(5.0, 31.0);
        final var point2 = new Point(0.0, 16.0);
        final var point3 = new Point(15.0, 3.0);
        final var point4 = new Point(30.0, 35.0);
        final var point5 = new Point(30.0, 42.0);
        return getPoints(point1, point2, point3, point4, point5);
    }

    private static List<Point> getPoints(Point point1, Point point2, Point point3, Point point4, Point point5) {
        final var point6 = new Point(20.0, 57.0);
        final var point7 = new Point(38.0, 62.0);
        final var point8 = new Point(14.0, 31.0);
        final var point9 = new Point(10.0, 57.0);
        final var point10 = new Point(7.0, 2.0);
        final var point11 = new Point(20.0, 15.0);
        final var point12 = new Point(38.0, 0.0);
        final var point13 = new Point(61.0, 43.0);
        final var point14 = new Point(37.0, 60.0);
        final var point15 = new Point(41.0, 30.0);
        final var point16 = new Point(52.0, 49.0);
        final var point17 = new Point(49.0, 20.0);
        final var point18 = new Point(0.0, 9.0);
        final var point19 = new Point(38.0, 8.0);
        final var point20 = new Point(38.0, 47.0);

        return List.of(point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11,
                point12, point13, point14, point15, point16, point17, point18, point19, point20);
    }
}

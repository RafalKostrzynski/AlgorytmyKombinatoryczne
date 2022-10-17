package pl.kostrzynski;

import java.util.LinkedList;
import java.util.List;

public final class FigureCreator {

    private FigureCreator() {
    }

    public static LinkedList<Point> createHexagon() {
        final var p1 = new Point(2, 1);
        final var p2 = new Point(1, 2);
        final var p3 = new Point(1, 3);
        final var p4 = new Point(2, 4);
        final var p5 = new Point(3, 3);
        final var p6 = new Point(3, 2);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6));
    }

    public static LinkedList<Point> createNWithoutCore() {
        final var p1 = new Point(1, 1);
        final var p2 = new Point(1, 5);
        final var p3 = new Point(3, 5);
        final var p4 = new Point(4, 2);
        final var p5 = new Point(5, 5);
        final var p6 = new Point(5, 1);
        final var p7 = new Point(3, 1);
        final var p8 = new Point(2, 3);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
    }

    public static LinkedList<Point> createNWithCore() {
        final var p1 = new Point(1, 1);
        final var p2 = new Point(1, 5);
        final var p3 = new Point(3, 5);
        final var p4 = new Point(4, 4);
        final var p5 = new Point(5, 5);
        final var p6 = new Point(5, 1);
        final var p7 = new Point(3, 1);
        final var p8 = new Point(2, 2);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
    }

    public static LinkedList<Point> createStar() {
        final var p1 = new Point(9, 1);
        final var p2 = new Point(9, 3);
        final var p3 = new Point(7, 3);
        final var p4 = new Point(9, 3);
        final var p5 = new Point(9, 5);
        final var p6 = new Point(9, 3);
        final var p7 = new Point(11, 3);
        final var p8 = new Point(9, 3);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
    }

    public static LinkedList<Point> createPolygon() {

        final var p1 = new Point(1, 0);
        final var p2 = new Point(1, 6);
        final var p3 = new Point(10, 6);
        final var p4 = new Point(10, 3);
        final var p5 = new Point(8, 3);
        final var p6 = new Point(7, 0);
        final var p7 = new Point(6, 3);
        final var p8 = new Point(5, 3);
        final var p9 = new Point(4, 0);
        final var p10 = new Point(3, 3);
        final var p11 = new Point(2, 3);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
    }
}

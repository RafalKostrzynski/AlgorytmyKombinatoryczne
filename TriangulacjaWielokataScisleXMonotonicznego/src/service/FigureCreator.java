package service;

import model.Point;

import java.util.LinkedList;
import java.util.List;

public final class FigureCreator {

    private FigureCreator() {
    }

    public static LinkedList<Point> createPolygon() {

        final var p1 = new Point(1, 4);
        final var p2 = new Point(3,1);
        final var p3 = new Point(4, 3);
        final var p4 = new Point(9, 2);
        final var p5 = new Point(8, 4);
        final var p6 = new Point(6, 5);
        final var p7 = new Point(5.5, 9);
        final var p8 = new Point(5, 3);
        final var p9 = new Point(3, 7);
        final var p10 = new Point(2, 5);

        return new LinkedList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
    }

}

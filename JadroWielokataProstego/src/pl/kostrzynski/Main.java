package pl.kostrzynski;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("Parametry jądra sześciokąta:");
        final var hexagon = FigureCreator.createHexagon();
        calculate(hexagon);

        System.out.println("\nParametry jądra figury N bez jądra:");
        final var nWithoutCore = FigureCreator.createNWithoutCore();
        calculate(nWithoutCore);

        System.out.println("\nParametry jądra figury N z jądrem:");
        final var createNWithCore = FigureCreator.createNWithCore();
        calculate(createNWithCore);

        System.out.println("\nParametry jądra gwiazdy:");
        final var star = FigureCreator.createStar();
        calculate(star);

        System.out.println("\nParametry jądra wielokąta:");
        final var polygon = FigureCreator.createPolygon();
        calculate(polygon);
    }

    private static void calculate(final LinkedList<Point> figure) {
        final var minMax = getMinMax(figure);

        if (!coreExists(minMax.first(), minMax.second())) {
            System.out.println("Jądro nie istnieje!");
            return;
        }

        final var core = findCorePoints(figure, minMax);
        final var perimeter = calculatePerimeter(core);
        final var area = calculateArea(core);

        System.out.printf("Punkt min: {%s %s}%n", minMax.first().x(), minMax.first().y());
        System.out.printf("Punkt max: {%s %s}%n", minMax.second().x(), minMax.second().y());
        System.out.printf("Punkty jądra: %s %n", core);
        System.out.printf("Obwód jądra: %s%n", perimeter);
        System.out.printf("Pole jądra: %s%n", area);
    }

    private static PointTuple getMinMax(final LinkedList<Point> figure) {
        var min = figure.stream().reduce((a, b) -> a.y() >= b.y() ? a : b).orElse(figure.getFirst());
        var max = figure.getFirst();

        for (int i = 0; i < figure.size(); i++) {
            final var tripplet = findThreePoints(figure, i);
            final var currentPoint = tripplet.p1();
            final var previousPoint = tripplet.p2();
            final var nextPoint = tripplet.p3();

            final var orientation = calculateOrientation(previousPoint, currentPoint, nextPoint);

            if (!orientation.equals(Orientation.NEGATIVE)) {
                continue;
            }

            final Point afterNext = getNextPoint(figure, nextPoint);
            if (isMax(previousPoint, currentPoint, nextPoint, afterNext) && max.y() < currentPoint.y()) {
                max = currentPoint;
            }

            if (isMin(previousPoint, currentPoint, nextPoint, afterNext) && min.y() > currentPoint.y()) {
                min = currentPoint;
            }
        }

        return new PointTuple(min, max);
    }

    private static LinkedList<Point> findCorePoints(final LinkedList<Point> figure, final PointTuple minMax) {
        final var min = minMax.first();
        final var borderPointsMin = findBorderPoints(figure, min);
        final var max = minMax.second();
        final var borderPointsMax = findBorderPoints(figure, max);

        if (borderPointsMin.isEmpty() && borderPointsMax.isEmpty()) {
            return filterCorePoints(figure, max, min);
        }

        final var listOfBorderPoints = new LinkedList<Point>();
        borderPointsMin.ifPresent(pointTuple -> listOfBorderPoints.addAll(List.of(pointTuple.first(), pointTuple.second())));
        borderPointsMax.ifPresent(pointTuple -> {
            listOfBorderPoints.addFirst(pointTuple.first());
            listOfBorderPoints.addLast(pointTuple.second());
        });

        final var iterator = figure.listIterator();
        while (iterator.hasNext()) {
            final var curr = iterator.next();
            final var next = getNextPoint(figure, curr);
            // this nested for is acceptable because the list may max have 4 elements
            final var onLinePoits = listOfBorderPoints.stream()
                    .filter(e -> isOnLine(curr, next, e))
                    .toList();
            // removes already added points for better performance
            onLinePoits.forEach(e -> {
                iterator.add(e);
                listOfBorderPoints.removeFirstOccurrence(e);
            });
        }
        return filterCorePoints(figure, max, min);
    }

    private static LinkedList<Point> filterCorePoints(LinkedList<Point> figure, Point max, Point min) {
        return figure.stream()
                .filter(e -> e.y() >= max.y() && e.y() <= min.y())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static boolean isOnLine(Point curr, Point next, Point e) {
        return calculateDistance(curr, e) + calculateDistance(next, e) == calculateDistance(curr, next);
    }

    private static double calculateDistance(final Point first, final Point second) {
        return Math.hypot(Math.abs(second.y() - first.y()), Math.abs(second.x() - first.x()));
    }

    private static Optional<PointTuple> findBorderPoints(final LinkedList<Point> figure, final Point point) {
        final double b1 = point.y();
        final double slope1 = 0;

        final var intersectionPoints = figure.stream()
                .map(e -> new PointTuple(e, getNextPoint(figure, e)))
                .filter(e -> isBetweenPoints(e, point.y()))
                .map(Main::calculateEquation)
                .map(e -> calculateIntersectionPoint(slope1, b1, e.x(), e.y()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (intersectionPoints.isEmpty()) return Optional.empty();

        final var farLeft = intersectionPoints.stream()
                .reduce((first, second) -> first.x() < second.x() ? first : second)
                .get();

        final var farRight = intersectionPoints.stream()
                .reduce((first, second) -> first.x() > second.x() ? first : second)
                .get();
        return Optional.of(new PointTuple(farLeft, farRight));
    }

    private static double calculateArea(LinkedList<Point> core) {
        final var sum = core.stream()
                .map(curr -> {
                    final var next = getNextPoint(core, curr);
                    return Math.abs(curr.x() * next.y() - curr.y() * next.x());
                }).reduce(Double::sum)
                .orElse(0.0);

        return sum / 2;
    }

    private static double calculatePerimeter(final LinkedList<Point> core) {
        return core.stream().map(curr -> {
                    final var next = getNextPoint(core, curr);
                    return Math.hypot(Math.abs(next.y() - curr.y()), Math.abs(next.x() - curr.x()));
                }).reduce(Double::sum)
                .orElse(0.0);
    }

    private static Orientation calculateOrientation(final Point p1, final Point p2, final Point p3) {

        double orientation = (p2.y() - p1.y()) * (p3.x() - p2.x()) - (p2.x() - p1.x()) * (p3.y() - p2.y());

        return Orientation.getOrientation(orientation);
    }

    private static boolean coreExists(final Point min, final Point max) {
        return min.y() >= max.y();
    }

    private static boolean isMax(final Point prev, final Point curr, final Point next, final Point afterNext) {
        final boolean isPrevSmaller = prev.y() < curr.y();
        if (curr.y() == next.y()) {
            return isPrevSmaller && afterNext.y() < curr.y();
        }
        return isPrevSmaller && next.y() < curr.y();
    }

    private static boolean isMin(final Point prev, final Point curr, final Point next, final Point afterNext) {
        final boolean isPrevGreater = prev.y() > curr.y();
        if (curr.y() == next.y()) {
            return isPrevGreater && afterNext.y() > curr.y();
        }
        return isPrevGreater && next.y() > curr.y();
    }

    private static Point getNextPoint(final LinkedList<Point> figure, final Point point) {
        final int index = figure.indexOf(point);
        final int nextIndex = index + 1;
        return figure.get(nextIndex == figure.size() ? 0 : nextIndex);
    }

    private static boolean isBetweenPoints(final PointTuple points, final double point) {

        final var first = points.first().y();
        final var second = points.second().y();

        return (first < point && second > point) || (first > point && second < point);
    }

    private static Point calculateEquation(final PointTuple points) {
        final var first = points.first();
        final var second = points.second();

        final double divider = second.x() - first.x();

        if (divider == 0) return new Point(first.x(), Double.MIN_VALUE);

        final var slope = (second.y() - first.y()) / divider;
        final var b = first.y() - slope * first.x();
        return new Point(slope, b);
    }

    private static Optional<Point> calculateIntersectionPoint(
            final double slope1, final double b1, final double slope2, final double b2
    ) {

        if (Double.MIN_VALUE == b2) return Optional.of(new Point(slope2, b1));

        final var divider = slope1 - slope2;
        final double x = divider != 0 ? (b2 - b1) / divider : Double.MAX_VALUE;

        if (Double.isNaN(x)) {
            return Optional.empty();
        }
        final double y = slope1 * x + b1;

        return Optional.of(new Point(x, y));
    }

    private static Triplet findThreePoints(final LinkedList<Point> figure, final int index) {
        final var firstPoint = figure.get(index);
        final var iterator = figure.listIterator(index);
        final int prevIndex = iterator.hasPrevious() ? iterator.previousIndex() : figure.size() - 1;
        final var prevPoint = figure.get(prevIndex);
        final var nextPoint = getNextPoint(figure, firstPoint);

        return new Triplet(firstPoint, prevPoint, nextPoint);
    }
}

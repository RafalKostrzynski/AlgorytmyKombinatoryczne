import model.Chain;
import model.Orientation;
import model.Point;
import model.PointTuple;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SweepTriangulation {

    public List<PointTuple> triangulate(final LinkedList<Point> points) {
        if (points.size() < 3) throw new RuntimeException();

        final var stack = new Stack<Point>();
        stack.push(points.get(0));
        stack.push(points.get(1));

        final var edges = new LinkedList<PointTuple>();
        final var pointsSize = points.size();
        for (int i = 2; i < pointsSize - 1; i++) {
            Point curentPoint = points.get(i);
            if (areOnDifferentChains(curentPoint, stack.peek())) {
                final var topOfStack = stack.peek();
                while (stack.size() > 1) {
                    edges.add(new PointTuple(curentPoint, stack.pop()));
                }
                stack.pop();
                stack.push(topOfStack);
            } else {
                final var topOfStack = stack.pop();
                Point lastPopped = null;

                while (!stack.empty() && isOnTheLeft(curentPoint, topOfStack, stack.peek())) {
                    lastPopped = stack.pop();
                    edges.add(new PointTuple(curentPoint, lastPopped));
                }
                lastPopped = lastPopped == null ? topOfStack : lastPopped;
                stack.push(lastPopped);
            }
            stack.push(curentPoint);
        }

        final var lastPoint = points.getLast();
        stack.pop();
        while (!stack.empty() && stack.size() != 1) {
            final var point = stack.pop();
            edges.add(new PointTuple(lastPoint, point));
        }
        return edges;
    }

    private boolean areOnDifferentChains(final Point first, final Point second) {
        return first.chain().equals(Chain.LOWER) && second.chain().equals(Chain.UPPER)
                || first.chain().equals(Chain.UPPER) && second.chain().equals(Chain.LOWER);
    }

    private boolean isOnTheLeft(final Point first, final Point second, final Point third) {
        return Orientation.NEGATIVE.equals(Orientation.calculateOrientation(first, second, third))
                && !areOnDifferentChains(second, third);
    }

}

package pl.kostrzynski;

import pl.kostrzynski.model.Result;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman {

    private static final int START_NODE = 0;
    private final int distanceSize;
    private final double[][] distance;

    public TravelingSalesman(double[][] distance) {
        this.distance = distance;
        this.distanceSize = distance.length;
    }

    public Result findCycle() {

        int state = 1 << START_NODE;
        final List<Integer> cycle = new ArrayList<>();
        final Double[][] memo = new Double[distanceSize][1 << distanceSize];
        final Integer[][] prev = new Integer[distanceSize][1 << distanceSize];
        final var minConst = tsp(START_NODE, state, memo, prev);

        int index = START_NODE;
        while (true) {
            cycle.add(index);
            Integer nextIndex = prev[index][state];
            if (nextIndex == null) break;
            state = state | (1 << nextIndex);
            index = nextIndex;
        }
        cycle.add(START_NODE);

        return new Result(cycle, minConst);
    }

    private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {

        if (state == (1 << distanceSize) - 1) return distance[i][START_NODE];

        if (memo[i][state] != null) return memo[i][state];

        double minCost = Double.POSITIVE_INFINITY;
        int index = -1;
        for (int next = 0; next < distanceSize; next++) {

            if ((state & (1 << next)) != 0) continue;

            int nextState = state | (1 << next);
            double newCost = distance[i][next] + tsp(next, nextState, memo, prev);
            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][state] = index;
        memo[i][state] = minCost;
        return minCost;
    }
}

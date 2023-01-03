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

    public Result calculate() {

        final List<Integer> cycle = new ArrayList<>();
        final Double[][] memo = new Double[distanceSize][1 << distanceSize];
        final Integer[][] prev = new Integer[distanceSize][1 << distanceSize];
        int j = 1;
        final var minConst = findMinPermutation(START_NODE, j, memo, prev);

        int i = START_NODE;
        while (true) {
            cycle.add(i);
            Integer nextIndex = prev[i][j];
            if (nextIndex == null) break;
            j = j | (1 << nextIndex);
            i = nextIndex;
        }
        cycle.add(START_NODE);

        return new Result(cycle, minConst);
    }

    private double findMinPermutation(int i, int acc, Double[][] memo, Integer[][] prev) {

        if (acc == (1 << distanceSize) - 1) return distance[i][START_NODE];

        if (memo[i][acc] != null) return memo[i][acc];

        double minCost = Double.POSITIVE_INFINITY;
        int index = -1;
        for (int next = 0; next < distanceSize; next++) {

            if ((acc & (1 << next)) != 0) continue;

            int nextAcc = acc | (1 << next);
            double newCost = distance[i][next] + findMinPermutation(next, nextAcc, memo, prev);
            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][acc] = index;
        memo[i][acc] = minCost;
        return minCost;
    }
}

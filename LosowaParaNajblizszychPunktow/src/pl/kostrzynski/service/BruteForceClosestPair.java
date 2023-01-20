package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;
import pl.kostrzynski.model.PointPair;

import java.util.List;

public class BruteForceClosestPair {

    public PointPair find(List<Point> testSet) {
        var min = Double.MAX_VALUE;
        var pair = new PointPair(testSet.get(0), testSet.get(1), DistanceBetweenPoints.calculate(testSet.get(0), testSet.get(1)));
        for (int i = 0; i < testSet.size(); i++) {
            for (int j = 0; j < testSet.size(); j++) {
                if (i == j) continue;
                final var curr = DistanceBetweenPoints.calculate(testSet.get(i), testSet.get(j));
                if (min != Math.min(min, curr)) {
                    min = curr;
                    pair = new PointPair(testSet.get(i), testSet.get(j), curr);
                }
            }
        }
        return pair;
    }

}

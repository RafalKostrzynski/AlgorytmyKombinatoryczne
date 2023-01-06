package pl.kostrzynski.model;

import java.util.Arrays;

public record KnapsackInput(int capacity, double epsilon, int[] weights, int[] values) {

    @Override
    public String toString() {
        return "KnapsackInput{" +
                "capacity=" + capacity +
                ", epsilon=" + epsilon +
                ", weights=" + Arrays.toString(weights) +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}

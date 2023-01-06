package pl.kostrzynski.model;

import java.util.Arrays;

public record SubsetSumInput(double epsilon, int[] values, int limit) {

    @Override
    public String toString() {
        return "SubsetSumInput{" +
                "epsilon=" + epsilon +
                ", values=" + Arrays.toString(values) +
                ", limit=" + limit +
                '}';
    }
}

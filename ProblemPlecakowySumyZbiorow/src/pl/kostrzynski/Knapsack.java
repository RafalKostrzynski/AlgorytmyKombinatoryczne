package pl.kostrzynski;

public class Knapsack {

    public int fptas(final int capacity, final double epsilon, final int[] weights, final int[] values) {

        int maxValue = 0;
        for (int value : values) {
            if (value > maxValue) maxValue = value;
        }

        final int valuesSize = values.length;

        final double scaleFactor = ((maxValue * epsilon) / valuesSize);
        if (scaleFactor == 0) return dynamicPrograming(capacity, weights, values);

        final int[] scaledValues = new int[valuesSize];
        for (int i = 0; i < valuesSize; i++) {
            scaledValues[i] = (int) Math.floor(values[i] / scaleFactor);
        }

        final var result = dynamicPrograming(capacity, weights, scaledValues);
        return (int) Math.floor(result * scaleFactor);
    }

    public int dynamicPrograming(final int capacity, final int[] weights, final int[] values) {

        int[] acc = new int[capacity + 1];

        final int size = values.length;
        for (int i = 1; i < size + 1; i++) {
            for (int w = capacity; w >= 0; w--) {

                if (weights[i - 1] <= w) acc[w] = Math.max(acc[w], acc[w - weights[i - 1]] + values[i - 1]);
            }
        }
        return acc[capacity];
    }

}

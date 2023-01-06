package pl.kostrzynski.service;

import pl.kostrzynski.model.KnapsackInput;
import pl.kostrzynski.model.SubsetSumInput;

import java.util.Random;
import java.util.stream.IntStream;

public final class InputCreator {

    private static final Random random = new Random();

    private InputCreator() {
    }

    public static KnapsackInput knapfsack(final int capacity, final double epsilon,
                                          final int numberOfElements, final int bound) {

        final var values = IntStream.range(0, numberOfElements)
                .map(e -> random.nextInt(1, bound / 2))
                .toArray();

        final var weights = IntStream.range(0, numberOfElements)
                .map(e -> random.nextInt(bound / 2, bound))
                .toArray();

        return new KnapsackInput(capacity, epsilon, values, weights);
    }

    public static SubsetSumInput subsetSum(final int numberOfElements, final double epsilon,
                                           final int limit, final int bound) {

        final var values = IntStream.range(0, numberOfElements)
                .map(e -> random.nextInt(1, bound))
                .sorted()
                .toArray();

        return new SubsetSumInput(epsilon, values, limit);
    }
}

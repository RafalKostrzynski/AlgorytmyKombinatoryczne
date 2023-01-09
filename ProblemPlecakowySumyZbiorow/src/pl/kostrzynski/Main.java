package pl.kostrzynski;

import pl.kostrzynski.model.KnapsackInput;
import pl.kostrzynski.model.SubsetSumInput;
import pl.kostrzynski.service.InputCreator;
import pl.kostrzynski.service.Panel;

import java.awt.Point;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        final var numberOfElements = 5;
        final var epsilon = 0.2;
        final var limit = 100;

        final var knapfsackResults = IntStream.range(1, 11)
                .mapToObj(e -> Map.entry(
                        (int) Math.pow(numberOfElements, e),
                        executeKnapfsack(InputCreator.knapfsack(limit * e, epsilon, (int) Math.pow(numberOfElements, e), limit)))
                ).map(e -> new Point(e.getKey() / 1000, (int) (e.getValue() / 1000000)))
                .toList();

        final var subsetResults = IntStream.range(1, 11)
                .mapToObj(e -> Map.entry(
                        (int) Math.pow(numberOfElements, e),
                        executeSubsetSum(InputCreator.subsetSum(limit * e, epsilon, (int) Math.pow(numberOfElements, e), limit)))
                ).map(e -> new Point(e.getKey() / 1000, (int) (e.getValue() / 1000000)))
                .toList();

        Panel.drawPoligon(knapfsackResults, subsetResults);
    }

    private static long executeKnapfsack(final KnapsackInput knapsackInput) {

        final var knapsack = new Knapsack();

        return execute(() -> knapsack.fptas(
                knapsackInput.capacity(),
                knapsackInput.epsilon(),
                knapsackInput.weights(),
                knapsackInput.values())
        );
    }

    private static long executeSubsetSum(final SubsetSumInput subsetSumInput) {

        final var subset = new SubsetSum();

        return execute(() -> subset.fptas(
                subsetSumInput.epsilon(),
                subsetSumInput.values(),
                subsetSumInput.limit())
        );
    }

    private static long execute(final Supplier<Object> supplier) {

        long start = System.nanoTime();
        supplier.get();
        long end = System.nanoTime();
        return end - start;
    }

}

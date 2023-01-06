package pl.kostrzynski;

import pl.kostrzynski.model.KnapsackInput;
import pl.kostrzynski.model.SubsetSumInput;
import pl.kostrzynski.service.InputCreator;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        final var numberOfElements1 = 5;
        final var numberOfElements2 = 6;
        final var numberOfElements3 = 50;
        final var numberOfElements4 = 500;
        final var numberOfElements5 = 5000;
        final var epsilon = 0.2;
        final var bound1 = 1000;
        final var bound2 = 10000;
        final var bound3 = 100;
        final var limit1 = 50;
        final var limit2 = 500;
        final var limit3 = 1000;

        final var knapsackInput1 = InputCreator.knapfsack(limit1, epsilon, numberOfElements1, bound3);
        final var knapsackInput2 = InputCreator.knapfsack(limit1, epsilon, numberOfElements2, bound3);
        final var knapsackInput3 = InputCreator.knapfsack(limit2, epsilon, numberOfElements3, bound1);
        final var knapsackInput4 = InputCreator.knapfsack(limit2, epsilon, numberOfElements4, bound1);
        final var knapsackInput5 = InputCreator.knapfsack(limit3, epsilon, numberOfElements5, bound2);
        final var knapsackInput6 = InputCreator.knapfsack(limit3, epsilon, numberOfElements5, bound2);

        final var subsetSumInput1 = InputCreator.subsetSum(numberOfElements1, epsilon, limit1, bound3);
        final var subsetSumInput2 = InputCreator.subsetSum(numberOfElements2, epsilon, limit1, bound3);
        final var subsetSumInput3 = InputCreator.subsetSum(numberOfElements3, epsilon, limit2, bound1);
        final var subsetSumInput4 = InputCreator.subsetSum(numberOfElements4, epsilon, limit2, bound1);
        final var subsetSumInput5 = InputCreator.subsetSum(numberOfElements5, epsilon, limit3, bound2);
        final var subsetSumInput6 = InputCreator.subsetSum(numberOfElements5, epsilon, limit3, bound2);

        System.out.println();
        executeKnapfsack(knapsackInput1);
        executeKnapfsack(knapsackInput2);
        executeKnapfsack(knapsackInput3);
        executeKnapfsack(knapsackInput4);
        executeKnapfsack(knapsackInput5);
        executeKnapfsack(knapsackInput6);

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println();
        executeSubsetSum(subsetSumInput1);
        executeSubsetSum(subsetSumInput2);
        executeSubsetSum(subsetSumInput3);
        executeSubsetSum(subsetSumInput4);
        executeSubsetSum(subsetSumInput5);
        executeSubsetSum(subsetSumInput6);
    }

    private static void executeKnapfsack(final KnapsackInput knapsackInput) {

        final var knapsack = new Knapsack();

        System.out.printf("Knapsack solved with input: %s%n", knapsackInput);
        execute(() -> knapsack.fptas(
                knapsackInput.capacity(),
                knapsackInput.epsilon(),
                knapsackInput.weights(),
                knapsackInput.values())
        );
    }

    private static void executeSubsetSum(final SubsetSumInput subsetSumInput) {

        final var subset = new SubsetSum();

        System.out.printf("SubsetSum solved with input: %s%n", subsetSumInput);
        execute(() -> subset.fptas(
                subsetSumInput.epsilon(),
                subsetSumInput.values(),
                subsetSumInput.limit())
        );
    }

    private static void execute(final Supplier<Object> supplier) {

        long start = System.nanoTime();
        final var result = supplier.get();
        long end = System.nanoTime();
        long elapsedTime = end - start;

        System.out.printf("""
                Execution time: %s nanoseconds.
                Result: %s.%n
                """, elapsedTime, result);
    }

}

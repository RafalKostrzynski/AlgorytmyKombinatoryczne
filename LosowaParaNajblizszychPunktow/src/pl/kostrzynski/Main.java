package pl.kostrzynski;

import pl.kostrzynski.model.PointPair;
import pl.kostrzynski.model.Result;
import pl.kostrzynski.service.BruteForceClosestPair;
import pl.kostrzynski.service.PointListCreator;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        final var randomPointList = new ArrayList<>(PointListCreator.createRandomPoints());
        final var sortedPointList = new ArrayList<>(PointListCreator.sortPoints(randomPointList));

        final var closestPair = new RandomizedClosestPair();
        final var bruteForce = new BruteForceClosestPair();

        System.out.println();
        System.out.println("Wynik dla tablicy z wstępnie nieposortowanymi wartościami bez shuffle:");
        final var resultUnsortedNonShuffle = execute(() -> closestPair.find(randomPointList));
        System.out.printf("RandomizedClosestPair: %s%n", resultUnsortedNonShuffle);

        System.out.println("Wynik dla tablicy z wstępnie nieposortowanymi wartościami z shuffle:");
        final var listCopy = new ArrayList<>(randomPointList);
        final var resultUnsortedShuffle = execute(() -> closestPair.findRandomized(listCopy));
        System.out.printf("%s%n", resultUnsortedShuffle);

        System.out.printf("BruteForce: %s%n%n", execute(() -> bruteForce.find(randomPointList)));

        System.out.println("Wynik dla tablicy z wstępnie posortowanymi wartościami bez shuffle:");
        final var resultSortedNonShuffle = execute(() -> closestPair.find(sortedPointList));
        System.out.printf("%s%n", resultSortedNonShuffle);

        System.out.println("Wynik dla tablicy z wstępnie posortowanymi wartościami z shuffle:");
        final var resultSortedShuffle = execute(() -> closestPair.findRandomized(sortedPointList));
        System.out.printf("%s%n", resultSortedShuffle);

        System.out.printf("BruteForce: %s%n%n", execute(() -> bruteForce.find(sortedPointList)));
    }

    private static Result execute(final Supplier<PointPair> supplier) {

        final long start = System.nanoTime();
        final var pair = supplier.get();
        final long end = System.nanoTime();
        return new Result(pair, end - start);
    }

}
package pl.kostrzynski;

import pl.kostrzynski.service.FigureCreator;

public class Main {

    public static void main(String[] args) {

//        final var graph = FigureCreator.createGraph1();
        final var graph = FigureCreator.createGraph2();
//        final var graph = FigureCreator.createGraph3();

        final var travelingSalesman = new TravelingSalesman(graph);
        final var cycle = travelingSalesman.findCycle();

        System.out.println(cycle);
    }

}

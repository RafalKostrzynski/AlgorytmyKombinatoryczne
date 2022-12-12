package pl.kostrzynski;

import pl.kostrzynski.service.FigureCreator;
import pl.kostrzynski.service.Panel;

public class Main {

    public static void main(String[] args) {

        final int numberOfKs = 4;

        final var points = FigureCreator.createPolygon();
//        final var map = FigureCreator.createMap(points);

        final var kCenters = new KCenters();
//        final var ks = kCenters.find(map, numberOfKs);
//        Panel.drawPoligon(points, ks);
        final var ks2 = kCenters.find(points, numberOfKs);
        Panel.drawPoligon(points, ks2);
    }

}

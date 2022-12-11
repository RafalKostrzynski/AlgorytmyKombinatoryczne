package pl.kostrzynski;

import pl.kostrzynski.service.FigureCreator;
import pl.kostrzynski.service.Panel;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        final int numberOfKs = 3;

        final var points = FigureCreator.createPolygon();
        final var map = FigureCreator.createMap(points);

        final var kCenters = new KCenters();
        final var ks = kCenters.find(map, numberOfKs);
        Panel.drawPoligon(points, ks);

        int[][] test = new int[points.size()][map.size()];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                test[j][i] = (int) new ArrayList<>(map.entrySet()).get(i).getValue().get(j).distance();
            }
        }
        Test.main(points.size(), test, numberOfKs);
    }

}

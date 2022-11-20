import model.Chain;
import model.Point;
import model.PointTuple;
import service.FigureCreator;
import service.PointXComparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends JPanel {

    private static List<PointTuple> tuples;
    private static List<Point> points;

    public static void main(String[] args) {

        final var sweepTriangulation = new SweepTriangulation();

        final var figure = FigureCreator.createPolygon();
        points = figure;
        final var sortedFigure = figure.stream()
                .sorted(new PointXComparator())
                .collect(Collectors.toCollection(LinkedList::new));

        final int indexOfFarRightPoint = figure.indexOf(sortedFigure.get(sortedFigure.size() - 1));

        final var map = figure.stream()
                .collect(Collectors.partitioningBy(e -> figure.indexOf(e) > indexOfFarRightPoint));
        map.get(Boolean.TRUE).forEach(e -> e.setChain(Chain.UPPER));
        map.get(Boolean.FALSE).forEach(e -> e.setChain(Chain.LOWER));

        tuples = sweepTriangulation.triangulate(sortedFigure);

        drawPoligon();
    }

    private static void drawPoligon() {
        JFrame frame = new JFrame();
        frame.setTitle("Trianglacja wielokąta ściśle x-monotonicznego");
        frame.setSize(500, 500);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.add(new Main());
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        points.forEach(e -> p.addPoint((int) e.x() * 50, (int) e.y() * 50));
        g.drawPolygon(p);
        g.setColor(Color.red);

        tuples.forEach(e -> g.drawLine(
                        (int) e.first().x() * 50,
                        (int) e.first().y() * 50,
                        (int) e.second().x() * 50,
                        (int) e.second().y() * 50
                )
        );
    }

}

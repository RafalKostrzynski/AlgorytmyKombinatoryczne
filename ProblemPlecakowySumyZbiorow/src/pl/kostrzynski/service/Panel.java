package pl.kostrzynski.service;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class Panel extends JPanel {
    private final List<Point> points;
    private final List<Point> ks;

    public Panel(final List<Point> points, final List<Point> ks) {
        this.points = points;
        this.ks = ks;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        points.forEach(e -> g.drawOval(e.x - 2, e.y - 2, 5, 5));
        drawGraph(g, points);
        g.setColor(Color.red);
        ks.forEach(e -> g.drawOval(e.x - 2, e.y - 2, 5, 5));
        drawGraph(g, ks);
    }

    private void drawGraph(Graphics g, List<Point> ks) {
        for (int i = 0; i < ks.size() - 1; i++) {
            final var f = ks.get(i);
            final var s = ks.get(i + 1);
            g.drawLine(f.x, f.y, s.x, s.y);
        }
    }

    public static void drawPoligon(final List<Point> points, final List<Point> ks) {

        JFrame frame = new JFrame();
        frame.setTitle("K-Centers");
        frame.setSize(2000, 2000);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.add(new Panel(points, ks));
        frame.setVisible(true);
    }
}

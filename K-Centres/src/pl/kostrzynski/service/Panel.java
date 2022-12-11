package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
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
        points.forEach(e -> g.drawOval((int) e.x() * 4, (int) e.y() * 4, 1, 1));
        g.setColor(Color.red);
        ks.forEach(e -> g.drawOval((int) e.x() * 4 - 2, (int) e.y() * 4 - 2, 5, 5));
    }

    public static void drawPoligon(final List<Point> points, final List<Point> ks) {

        JFrame frame = new JFrame();
        frame.setTitle("K-Centers");
        frame.setSize(500, 500);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.add(new Panel(points, ks));
        frame.setVisible(true);
    }
}

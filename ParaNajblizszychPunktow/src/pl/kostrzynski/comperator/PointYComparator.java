package pl.kostrzynski.comperator;

import pl.kostrzynski.model.Point;

import java.util.Comparator;

public class PointYComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        if ((p1.y() < p2.y()) || (p1.y() == p2.y() && p1.x() < p2.x())) return -1;
        else if (p1.y() > p2.y()) return 1;
        return 0;
    }

}

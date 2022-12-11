package pl.kostrzynski.service;

import pl.kostrzynski.model.Point;

import java.util.Comparator;

public class PointXComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        if ((p1.x() < p2.x()) || (p1.x() == p2.x() && (p1.y() < p2.y() || p1.z() < p2.z()))) return -1;
        else if (p1.x() == p2.x() && p1.y() == p2.y() && p1.z() == p2.z()) return 0;
        return 1;
    }

}

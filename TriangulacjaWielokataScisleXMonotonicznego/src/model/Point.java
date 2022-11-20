package model;


import java.util.Objects;

public class Point {

    final double x, y;

    private Chain chain;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public Chain chain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && chain == point.chain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, chain);
    }

    @Override
    public String toString() {
        return "service.FigureCreator.Point{" +
                "x=" + x +
                ", y=" + y +
                ", chain=" + chain +
                '}';
    }
}

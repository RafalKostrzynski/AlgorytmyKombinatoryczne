package pl.kostrzynski.service;

public final class FigureCreator {

    private FigureCreator() {
    }

    public static double[][] createGraph1() {

        return new double[][]{
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
    }

    public static double[][] createGraph2() {

        return new double[][]{
                {0, 4, 1, 9},
                {3, 0, 6, 11},
                {4, 1, 0, 2},
                {6, 5, -4, 0}
        };
    }

    public static double[][] createGraph3() {
        int n = 6;
        double[][] distanceMatrix = new double[n][n];
        for (double[] row : distanceMatrix) java.util.Arrays.fill(row, 10000);
        distanceMatrix[1][4] = distanceMatrix[4][1] = 2;
        distanceMatrix[4][2] = distanceMatrix[2][4] = 4;
        distanceMatrix[2][3] = distanceMatrix[3][2] = 6;
        distanceMatrix[3][0] = distanceMatrix[0][3] = 8;
        distanceMatrix[0][5] = distanceMatrix[5][0] = 10;
        distanceMatrix[5][1] = distanceMatrix[1][5] = 12;

        return distanceMatrix;
    }

}

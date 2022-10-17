package pl.kostrzynski;

public enum Orientation {

    POSITIVE, NEGATIVE, ZERO;

    public static Orientation getOrientation(final double value) {

        if (value == 0) return ZERO;

        return value > 0 ? POSITIVE : NEGATIVE;
    }
}

package org.example;


public enum LengthUnit {

    FEET(1.0),         // Base unit
    INCH(1.0 / 12.0),  // 12 inches = 1 foot
    YARD(3.0),
    CM(0.393701 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toFeet(double value) {
        return value * toFeetFactor;
    }
}

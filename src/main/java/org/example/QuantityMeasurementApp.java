package org.example;
import java.util.Locale;

public final class QuantityMeasurementApp {

    private QuantityMeasurementApp() {}

    /* -------------------- API: Demo methods as per your Use Case -------------------- */

    /** Overloaded demo: create from primitives (value, fromUnit, toUnit). */
    public static QuantityLength demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        QuantityLength q = new QuantityLength(value, fromUnit);
        return q.convertTo(toUnit);
    }

    /** Overloaded demo: convert an existing QuantityLength instance. */
    public static QuantityLength demonstrateLengthConversion(QuantityLength q, LengthUnit toUnit) {
        return q.convertTo(toUnit);
    }

    /** Demo: equality of two quantities. */
    public static boolean demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
        return a.equals(b);
    }

    /** Optional: show numeric conversion with rounding. */
    public static double demonstrateNumericConversion(double value, LengthUnit from, LengthUnit to,
                                                      int scale) {
        return QuantityLength.convert(value, from, to, scale, java.math.RoundingMode.HALF_UP);
    }

    /* -------------------- String-friendly helpers (for quick UI/tests) -------------------- */

    public static boolean compare(String v1, String u1, String v2, String u2) {
        double d1 = parseNumber(v1);
        double d2 = parseNumber(v2);
        LengthUnit lu1 = parseUnit(u1);
        LengthUnit lu2 = parseUnit(u2);
        return new QuantityLength(d1, lu1).equals(new QuantityLength(d2, lu2));
    }

    public static double convert(String value, String fromUnit, String toUnit) {
        double v = parseNumber(value);
        LengthUnit from = parseUnit(fromUnit);
        LengthUnit to = parseUnit(toUnit);
        return QuantityLength.convert(v, from, to);
    }

    /* -------------------- Parsing & validation -------------------- */

    private static double parseNumber(String s) {
        if (s == null) throw new IllegalArgumentException("Input value cannot be null");
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Non-numeric input: '" + s + "'", nfe);
        }
    }

    /** Accepts ft/feet/foot, in/inch/inches, yd/yard/yards, cm/centimeter/centimeters. */
    static LengthUnit parseUnit(String unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        switch (unit.trim().toLowerCase(Locale.ROOT)) {
            case "ft": case "feet": case "foot": return LengthUnit.FEET;
            case "in": case "inch": case "inches": return LengthUnit.INCH;
            case "yd": case "yard": case "yards": return LengthUnit.YARD;
            case "cm": case "centimeter": case "centimeters": return LengthUnit.CM;
            default: throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }

    /* -------------------- Quick demo -------------------- */
    public static void main(String[] args) {
        // UC5 conversion examples
        System.out.println(demonstrateLengthConversion(2.0, LengthUnit.YARD, LengthUnit.FEET)); // ~ 6.0 FEET
        System.out.println(demonstrateLengthConversion(new QuantityLength(36.0, LengthUnit.INCH), LengthUnit.YARD)); // ~ 1.0 YARD

        // Using 0.393701 path:
        System.out.println(convert("1", "cm", "in"));  // -> ~0.393701
        System.out.println(convert("30.48", "cm", "ft")); // ~ 1.0 (may be off by tiny fp drift if printed with many decimals)

        // Equality demo (exact)
        System.out.println(compare("1", "yd", "3", "ft"));    // true
        System.out.println(compare("36", "in", "1", "yd"));   // true
    }
}

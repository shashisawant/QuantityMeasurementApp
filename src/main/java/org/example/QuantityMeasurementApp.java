package org.example;
public class QuantityMeasurementApp {

    public static class Feet{
        private final double value;
        public Feet(double value)
        {
            this.value=value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

    }

    public static class Inch{
        private int value;
        public Inch(int value)
        {
            this.value=value;
        }

        public int toInch()
        {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            Inch inch = (Inch) obj;
            return value == inch.value;
        }

    }

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

    public static double demonstrateNumericConversion(double value, LengthUnit from, LengthUnit to,
                                                      int scale) {
        return QuantityLength.convert(value, from, to, scale, java.math.RoundingMode.HALF_UP);
    }
    // Compares two quantities (value + unit)
    public static boolean compare(String val1, String unit1, String val2, String unit2) {

        double v1 = parse(val1);
        double v2 = parse(val2);

        LengthUnit u1 = parseUnit(unit1);
        LengthUnit u2 = parseUnit(unit2);

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        return q1.equals(q2);
    }

    private static double parse(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid numeric input: " + s);
        }
    }

    public static double convert(String value, String fromUnit, String toUnit) {
        double v = parseNumber(value);
        LengthUnit from = parseUnit(fromUnit);
        LengthUnit to = parseUnit(toUnit);
        return QuantityLength.convert(v, from, to);
    }

    private static double parseNumber(String s) {
        if (s == null) throw new IllegalArgumentException("Input value cannot be null");
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Non-numeric input: '" + s + "'", nfe);
        }
    }

    private static LengthUnit parseUnit(String unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.toLowerCase()) {
            case "ft":
            case "feet":
                return LengthUnit.FEET;

            case "in":
            case "inch":
            case "inches":
                return LengthUnit.INCH;


            case "yd":
            case "yard":
            case "yards":
                return LengthUnit.YARD;

            case "cm":
            case "centimeter":
            case "centimeters":
                return LengthUnit.CM;


            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }

    public static QuantityLength add(String v1, String u1, String v2, String u2) {
        double d1 = parseNumber(v1);
        double d2 = parseNumber(v2);
        LengthUnit lu1 = parseUnit(u1);
        LengthUnit lu2 = parseUnit(u2);
        return QuantityLength.add(new QuantityLength(d1, lu1), new QuantityLength(d2, lu2));
    }

    /** Add two (value,unit) pairs; result in explicit target unit. */
    public static QuantityLength add(String v1, String u1, String v2, String u2, String targetUnit) {
        double d1 = parseNumber(v1);
        double d2 = parseNumber(v2);
        LengthUnit lu1 = parseUnit(u1);
        LengthUnit lu2 = parseUnit(u2);
        LengthUnit target = parseUnit(targetUnit);
        return QuantityLength.add(d1, lu1, d2, lu2, target);
    }

    public static void main(String[] args){
/*        boolean result = false;
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);
        result = feet1.equals(feet2);
        System.out.println("Are both mesurement equal? - "+result);

        Inch inch1 = new Inch(5);
        Inch inch2 = new Inch(5);
        result = inch1.equals(inch2);
        System.out.println("Are both mesurement equal? - "+result);


        System.out.println(compare("1.0", "ft", "12.0", "in")); // true
        System.out.println(compare("2.0", "ft", "24.0", "in")); // true
        System.out.println(compare("3.0", "ft", "36.0", "in")); // true
        System.out.println(compare("2.5", "ft", "30.0", "in")); // true
        System.out.println(compare("1.0", "ft", "11.0", "in")); // false



        System.out.println(compare("1", "yd", "3", "ft"));      // true: 1 yd = 3 ft
        System.out.println(compare("36", "in", "1", "yd"));     // true: 36 in = 1 yd
        System.out.println(compare("30.48", "cm", "1", "ft"));  // true if you swap CM to 1/(2.54*12)
        System.out.println(compare("2.54", "cm", "1", "in"));   // true if CM uses 1/(2.54*12)
        System.out.println(compare("100", "cm", "1", "yd"));    // false
        */

        System.out.println(add("1", "ft", "12", "in"));       // -> 2.0 FEET
        System.out.println(add("1", "yd", "3", "ft"));        // -> 2.0 YARD (by default, unit of first operand)
        System.out.println(add("24", "in", "1", "ft", "in")); // -> 36.0 INCH
        System.out.println(add("30.48", "cm", "0", "in", "ft")); // ≈ 1.0 FEET (with 0.393701 path, tiny drift possible)
    }
}

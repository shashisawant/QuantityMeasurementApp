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

    private static LengthUnit parseUnit(String unit) {
        switch (unit.toLowerCase()) {
            case "ft":
            case "feet":
                return LengthUnit.FEET;

            case "in":
            case "inch":
            case "inches":
                return LengthUnit.INCH;

            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
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
        */

    }

}

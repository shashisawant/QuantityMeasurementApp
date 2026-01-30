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

    public static void main(String[] args){
        boolean result = false;
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);
        result = feet1.equals(feet2);
        System.out.println("Are both mesurement equal? - "+result);

        Inch inch1 = new Inch(5);
        Inch inch2 = new Inch(5);
        result = inch1.equals(inch2);
        System.out.println("Are both mesurement equal? - "+result);
    }
}

package org.example;
public class QuantityMeasurementAppFeet {

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
    public static void main(String[] args){
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);
        boolean result = feet1.equals(feet2);
        System.out.println("Are both mesurement equal? - "+result);
    }
}

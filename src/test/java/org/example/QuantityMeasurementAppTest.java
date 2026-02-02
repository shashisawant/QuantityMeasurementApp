package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    //--------------------UC1----------------------//
    @Test
    void testFeetEquality_SameValue(){
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet2 = new  QuantityMeasurementApp.Feet(5.0);
        assertEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_DifferentValue(){
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet2 = new  QuantityMeasurementApp.Feet(5.6);
        assertNotEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        assertFalse(feet1.equals(null), "A value must not be equal to null");
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);

        assertTrue(feet1.equals(feet1), "Object must be equal to itself (reflexivity)");
    }
        @Test
        void testFeetEquality_NonNumericString() {
            assertThrows(IllegalArgumentException.class, () -> parseFeet("abc"));
        }

        @Test
        void testFeet_NumericString() {
            QuantityMeasurementApp.Feet parsed = parseFeet("  1.0 ");
            assertEquals(new  QuantityMeasurementApp.Feet(1.0), parsed);
        }

    QuantityMeasurementApp.Feet parseFeet(String input) {
        try {
            double v = Double.parseDouble(input.trim());
            QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(v);

            return feet1;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Non-numeric feet input: " + input, ex);
        }
    }



    //--------------------UC2----------------------//
    @Test
    void testInchEquality_SameValue(){
        QuantityMeasurementApp.Inch inch1 = new  QuantityMeasurementApp.Inch(5);
        QuantityMeasurementApp.Inch inch2 = new  QuantityMeasurementApp.Inch(5);
        assertEquals(inch1, inch1);
    }

    @Test
    void testInchEquality_DifferentValue(){
        QuantityMeasurementApp.Inch inch1 = new  QuantityMeasurementApp.Inch(5);
        QuantityMeasurementApp.Inch inch2 = new  QuantityMeasurementApp.Inch(6);
        assertNotEquals(inch1, inch2);
    }

    @Test
    void testInchEquality_NullComparison() {
        QuantityMeasurementApp.Inch inch1 = new  QuantityMeasurementApp.Inch(5);
        assertFalse(inch1.equals(null), "A value must not be equal to null");
    }

    @Test
    void testInchEquality_SameReference() {
        QuantityMeasurementApp.Inch inch1 = new  QuantityMeasurementApp.Inch(5);

        assertTrue(inch1.equals(inch1), "Object must be equal to itself (reflexivity)");
    }
    @Test
    void testInchEquality_NonNumericString() {
        assertThrows(IllegalArgumentException.class, () -> parseInch("abc"));
    }

    @Test
    void testInch_NumericString() {
        QuantityMeasurementApp.Inch parsed = parseInch("  1 ");
        assertEquals(new  QuantityMeasurementApp.Inch(1), parsed);
    }

    QuantityMeasurementApp.Inch parseInch(String input) {
        try {
            int v = Integer.parseInt(input.trim());
            QuantityMeasurementApp.Inch inch1 = new  QuantityMeasurementApp.Inch(v);

            return inch1;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Non-numeric inch input: " + input, ex);
        }
    }


    //--------------------UC3----------------------//

     @Test
        void testEquality_FeetToFeet_SameValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

            assertEquals(q1, q2, "Feet: identical values should be equal");
        }

        // ---------- 2. Inch-to-Inch Same Value ----------
        @Test
        void testEquality_InchToInch_SameValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

            assertEquals(q1, q2, "Inches: identical values should be equal");
        }

        // ---------- 3. Null Comparison ----------
        @Test
        void testEquality_NullComparison() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

            assertNotEquals(q1, null, "Comparing to null must return false");
        }

        // ---------- 4. Inch to Feet Cross-Unit Equality ----------
        @Test
        void testEquality_InchToFeet_EquivalentValue() {
            QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH); // equals 1 FT
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

            assertEquals(q1, q2, "12 inches should equal 1 foot");
        }

        // ---------- 5. Feet-to-Feet Different Values ----------
        @Test
        void testEquality_FeetToFeet_DifferentValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

            assertNotEquals(q1, q2, "1 ft should not equal 2 ft");
        }

        // ---------- 6. Inch-to-Inch Different Values ----------
        @Test
        void testEquality_InchToInch_DifferentValue() {
            QuantityLength q1 = new QuantityLength(10.0, LengthUnit.INCH);
            QuantityLength q2 = new QuantityLength(20.0, LengthUnit.INCH);

            assertNotEquals(q1, q2, "10 inches should not equal 20 inches");
        }

        // ---------- 7. Invalid Unit Handling ----------
        @Test
        void testEquality_InvalidUnit() {
            assertThrows(IllegalArgumentException.class, () ->
                    QuantityMeasurementApp.compare("10", "meter", "10", "ft"));
        }

        // ---------- 8. Unit Null Handling ----------
        @Test
        void testEquality_NullUnit() {
            assertThrows(IllegalArgumentException.class, () ->
                    QuantityMeasurementApp.compare("10", null, "10", "ft"));
        }

        // ---------- 9. Same Reference ----------
        @Test
        void testEquality_SameReference() {
            QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

            assertEquals(q, q, "Object must always equal itself");
        }

        // ---------- 10. Type Safety (Non-QuantityLength Object) ----------
        @Test
        void testEquality_NonQuantityObject() {
            QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

            assertNotEquals(q, "string", "Quantity should not equal an unrelated object");
        }


}
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

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




}
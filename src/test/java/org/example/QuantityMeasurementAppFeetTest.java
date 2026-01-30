package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppFeetTest {

    @Test
    void givenSameFeetValue_whenCompared_shouldReturnTrue(){
        QuantityMeasurementAppFeet.Feet feet1 = new  QuantityMeasurementAppFeet.Feet(5.0);
        QuantityMeasurementAppFeet.Feet feet2 = new  QuantityMeasurementAppFeet.Feet(5.0);
        assertEquals(feet1, feet2);
    }

    @Test
    void givenSameFeetValue_whenCompared_shouldReturnFalse(){
        QuantityMeasurementAppFeet.Feet feet1 = new  QuantityMeasurementAppFeet.Feet(5.0);
        QuantityMeasurementAppFeet.Feet feet2 = new  QuantityMeasurementAppFeet.Feet(5.6);
        assertNotEquals(feet1, feet2);
    }



    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementAppFeet.Feet feet1 = new  QuantityMeasurementAppFeet.Feet(5.0);
        assertFalse(feet1.equals(null), "A value must not be equal to null");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementAppFeet.Feet feet1 = new  QuantityMeasurementAppFeet.Feet(5.0);

        assertTrue(feet1.equals(feet1), "Object must be equal to itself (reflexivity)");
    }
        @Test
        void shouldRejectNonNumericString() {
            assertThrows(IllegalArgumentException.class, () -> parseFeet("abc"));
        }

        @Test
        void shouldParseNumericString() {
            QuantityMeasurementAppFeet.Feet parsed = parseFeet("  1.0 ");
            assertEquals(new  QuantityMeasurementAppFeet.Feet(1.0), parsed);
        }

    QuantityMeasurementAppFeet.Feet parseFeet(String input) {
        try {
            double v = Double.parseDouble(input.trim());
            QuantityMeasurementAppFeet.Feet feet1 = new  QuantityMeasurementAppFeet.Feet(v);

            return feet1;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Non-numeric feet input: " + input, ex);
        }
    }
}
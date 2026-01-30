package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppFeetTest {

    @Test
    void givenSameFeetValue_whenCompared_shouldReturnTrue(){
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet2 = new  QuantityMeasurementApp.Feet(5.0);
        assertEquals(feet1, feet2);
    }

    @Test
    void givenSameFeetValue_whenCompared_shouldReturnFalse(){
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet2 = new  QuantityMeasurementApp.Feet(5.6);
        assertNotEquals(feet1, feet2);
    }



    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);
        assertFalse(feet1.equals(null), "A value must not be equal to null");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet feet1 = new  QuantityMeasurementApp.Feet(5.0);

        assertTrue(feet1.equals(feet1), "Object must be equal to itself (reflexivity)");
    }
        @Test
        void shouldRejectNonNumericString() {
            assertThrows(IllegalArgumentException.class, () -> parseFeet("abc"));
        }

        @Test
        void shouldParseNumericString() {
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
}
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

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
}
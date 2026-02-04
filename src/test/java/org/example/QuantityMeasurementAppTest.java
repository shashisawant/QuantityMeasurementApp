package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // Small tolerance for floating comparisons (esp. CM using 0.393701)
    private static final double EPS = 1e-9;

    // 1. testConversion_FeetToInches()
    @Test
    @DisplayName("1) Feet → Inches: 1 ft = 12 in")
    void testConversion_FeetToInches() {
        double actual = QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, actual, EPS);
    }

    // 2. testConversion_InchesToFeet()
    @Test
    @DisplayName("2) Inches → Feet: 24 in = 2 ft")
    void testConversion_InchesToFeet() {
        double actual = QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(2.0, actual, EPS);
    }

    // 3. testConversion_YardsToInches()
    @Test
    @DisplayName("3) Yards → Inches: 1 yd = 36 in")
    void testConversion_YardsToInches() {
        double actual = QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH);
        assertEquals(36.0, actual, EPS);
    }

    // 4. testConversion_InchesToYards()
    @Test
    @DisplayName("4) Inches → Yards: 72 in = 2 yd")
    void testConversion_InchesToYards() {
        double actual = QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD);
        assertEquals(2.0, actual, EPS);
    }

    // 5. testConversion_CentimetersToInches()
    @Test
    @DisplayName("5) Centimeters → Inches: 2.54 cm ≈ 1 in (within epsilon when CM=0.393701)")
    void testConversion_CentimetersToInches() {
        // With CM = 0.393701 inches, 2.54 cm * 0.393701 ≈ 0.99999954...
        double actual = QuantityLength.convert(2.54, LengthUnit.CM, LengthUnit.INCH);
        assertEquals(1.0, actual, 1e-6); // relaxed epsilon to accommodate rounding
    }

    // 6. testConversion_FeetToYard()
    @Test
    @DisplayName("6) Feet → Yards: 6 ft = 2 yd")
    void testConversion_FeetToYard() {
        double actual = QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD);
        assertEquals(2.0, actual, EPS);
    }

    // 7. testConversion_RoundTrip_PreserveValue()
    @Test
    @DisplayName("7) Round-trip preserves value within tolerance: A→B→A ≈ original")
    void testConversion_RoundTrip_PreserveValue() {
        // Yard ↔ Inch
        double v1 = 3.75; // arbitrary
        double yToI = QuantityLength.convert(v1, LengthUnit.YARD, LengthUnit.INCH);
        double iToY = QuantityLength.convert(yToI, LengthUnit.INCH, LengthUnit.YARD);
        assertEquals(v1, iToY, EPS);
        // CM ↔ Inch (use slightly looser epsilon due to 0.393701)
        double v2 = 123.456;
        double cToI = QuantityLength.convert(v2, LengthUnit.CM, LengthUnit.INCH);
        double iToC = QuantityLength.convert(cToI, LengthUnit.INCH, LengthUnit.CM);
        assertEquals(v2, iToC, 1e-6);
    }

    // 8. testConversion_ZeroValue()
    @Test
    @DisplayName("8) Zero value: 0 ft → in = 0")
    void testConversion_ZeroValue() {
        double actual = QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.0, actual, EPS);
    }

    // 9. testConversion_NegativeValue()
    @Test
    @DisplayName("9) Negative value: -1 ft → in = -12 (behavior defined)")
    void testConversion_NegativeValue() {
        double actual = QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(-12.0, actual, EPS);
    }

    // 10. testConversion_InvalidUnit_Throws()
    @Test
    @DisplayName("10) Invalid unit: null source/target should throw IllegalArgumentException")
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }

    // 11. testConversion_NaNOrInfinite_Throws()
    @Test
    @DisplayName("11) NaN/Infinite input should throw")
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }

    // 12. testConversion_PrecisionTolerance()
    @Test
    @DisplayName("12) Precision tolerance: compare using small epsilon")
    void testConversion_PrecisionTolerance() {
        // Example: 30.48 cm should be ~ 1 ft (with 0.393701 in/cm, tiny drift possible)
        double feet = QuantityLength.convert(30.48, LengthUnit.CM, LengthUnit.FEET);
        assertEquals(1.0, feet, 1e-6);
    }
}
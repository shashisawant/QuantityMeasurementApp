package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
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


        //-----------------------------UC4----------------------

        // 1. testEquality_YardToYard_SameValue()
        @Test
        @DisplayName("1) Yard-to-Yard same value: 1 yd == 1 yd")
        void testEquality_YardToYard_SameValue() {
            assertTrue(QuantityMeasurementApp.compare("1.0", "YARDS", "1.0", "YARD"));
        }

        // 2. testEquality_YardToYard_DifferentValue()
        @Test
        @DisplayName("2) Yard-to-Yard different value: 1 yd != 2 yd")
        void testEquality_YardToYard_DifferentValue() {
            assertFalse(QuantityMeasurementApp.compare("1.0", "YARD", "2.0", "YARDS"));
        }

        // 3. testEquality_YardToFeet_EquivalentValue()
        @Test
        @DisplayName("3) Yard-to-Feet equivalent: 1 yd == 3 ft")
        void testEquality_YardToFeet_EquivalentValue() {
            assertTrue(QuantityMeasurementApp.compare("1.0", "YARDS", "3.0", "FEET"));
        }

        // 4. testEquality_FeetToYard_EquivalentValue()
        @Test
        @DisplayName("4) Feet-to-Yard equivalent: 3 ft == 1 yd")
        void testEquality_FeetToYard_EquivalentValue() {
            assertTrue(QuantityMeasurementApp.compare("3.0", "FEET", "1.0", "YARDS"));
        }

        // 5. testEquality_YardToInches_EquivalentValue()
        @Test
        @DisplayName("5) Yard-to-Inches equivalent: 1 yd == 36 in")
        void testEquality_YardToInches_EquivalentValue() {
            assertTrue(QuantityMeasurementApp.compare("1.0", "YARDS", "36.0", "INCHES"));
        }

        // 6. testEquality_InchesToYard_EquivalentValue()
        @Test
        @DisplayName("6) Inches-to-Yard equivalent: 36 in == 1 yd")
        void testEquality_InchesToYard_EquivalentValue() {
            assertTrue(QuantityMeasurementApp.compare("36.0", "INCHES", "1.0", "YARDS"));
        }

        // 7. testEquality_YardToFeet_NonEquivalentValue()
        @Test
        @DisplayName("7) Yard-to-Feet non-equivalent: 1 yd != 2 ft")
        void testEquality_YardToFeet_NonEquivalentValue() {
            assertFalse(QuantityMeasurementApp.compare("1.0", "YARDS", "2.0", "FEET"));
        }

        // 8. testEquality_centimetersToInches_EquivalentValue()
        @Test
        @DisplayName("8) CM-to-Inches (per chosen definition): 1 cm == 0.393701 in")
        void testEquality_centimetersToInches_EquivalentValue() {
            // Using your chosen cm factor (0.393701 in/cm) -> this should be TRUE.
            assertTrue(QuantityMeasurementApp.compare("1.0", "CENTIMETERS", "0.393701", "INCHES"));
        }

        // 9. testEquality_centimetersToFeet_NonEquivalentValue()
        @Test
        @DisplayName("9) CM-to-Feet non-equivalent: 1 cm != 1 ft")
        void testEquality_centimetersToFeet_NonEquivalentValue() {
            assertFalse(QuantityMeasurementApp.compare("1.0", "CENTIMETERS", "1.0", "FEET"));
        }

        // 10. testEquality_MultiUnit_TransitiveProperty()
        @Test
        @DisplayName("10) Transitive property: (1 yd == 3 ft) && (3 ft == 36 in) => (1 yd == 36 in)")
        void testEquality_MultiUnit_TransitiveProperty() {
            assertTrue(QuantityMeasurementApp.compare("1.0", "YARDS", "3.0", "FEET"));   // A == B
            assertTrue(QuantityMeasurementApp.compare("3.0", "FEET", "36.0", "INCHES")); // B == C
            assertTrue(QuantityMeasurementApp.compare("1.0", "YARDS", "36.0", "INCHES")); // A == C
        }

        // 11. testEquality_YardWithNullUnit()
        @Test
        @DisplayName("11) Null unit handling: should throw IllegalArgumentException")
        void testEquality_YardWithNullUnit() {
            assertThrows(IllegalArgumentException.class,
                    () -> QuantityMeasurementApp.compare("1.0", null, "1.0", "YARD"));
            assertThrows(IllegalArgumentException.class,
                    () -> QuantityMeasurementApp.compare("1.0", "YARD", "1.0", null));
        }


    // 12. testEquality_YardSameReference()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("12) Yard same reference: object equals itself")
    void testEquality_YardSameReference() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        org.junit.jupiter.api.Assertions.assertEquals(yard, yard); // reflexive property
    }

    // 13. testEquality_YardNullComparison()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("13) Yard null comparison: yard != null")
    void testEquality_YardNullComparison() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        org.junit.jupiter.api.Assertions.assertNotEquals(yard, null);
    }

    // 14. testEquality_CentimetersWithNullUnit()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("14) CM with null unit: compare should throw")
    void testEquality_CentimetersWithNullUnit() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.compare("1.0", null, "0.393701", "INCHES"));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.compare("1.0", "CENTIMETERS", "0.393701", null));
    }

    // 15. testEquality_CentimetersSameReference()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("15) CM same reference: object equals itself")
    void testEquality_CentimetersSameReference() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CM);
        org.junit.jupiter.api.Assertions.assertEquals(cm, cm); // reflexive property
    }

    // 16. testEquality_CentimetersNullComparison()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("16) CM null comparison: cm != null")
    void testEquality_CentimetersNullComparison() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CM);
        org.junit.jupiter.api.Assertions.assertNotEquals(cm, null);
    }

    // 17. testEquality_AllUnits_ComplexScenario()
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("17) All-units complex scenario: 2 yd == 6 ft == 72 in")
    void testEquality_AllUnits_ComplexScenario() {
        // 2 yards == 6 feet
        org.junit.jupiter.api.Assertions.assertTrue(
                QuantityMeasurementApp.compare("2.0", "YARDS", "6.0", "FEET"));

        // 6 feet == 72 inches
        org.junit.jupiter.api.Assertions.assertTrue(
                QuantityMeasurementApp.compare("6.0", "FEET", "72.0", "INCHES"));

        // Therefore: 2 yards == 72 inches
        org.junit.jupiter.api.Assertions.assertTrue(
                QuantityMeasurementApp.compare("2.0", "YARDS", "72.0", "INCHES"));
    }


    //-----------------------------UC5-------------------------
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

    //-------------------------UC6-------------------


    private static final double CM_EPS = 1e-5;   // or 2e-6 if you want tighter

    // 1. testAddition_SameUnit_FeetPlusFeet()
    @Test
    @DisplayName("1) Same unit: 1 ft + 2 ft = 3 ft")
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(2.0, LengthUnit.FEET));
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(3.0, r.value(), EPS);
    }

    // 2. testAddition_SameUnit_InchPlusInch()
    @Test
    @DisplayName("2) Same unit: 6 in + 6 in = 12 in")
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength r = new QuantityLength(6.0, LengthUnit.INCH)
                .add(new QuantityLength(6.0, LengthUnit.INCH));
        assertEquals(LengthUnit.INCH, r.unit());
        assertEquals(12.0, r.value(), EPS);
    }

    // 3. testAddition_CrossUnit_InFeetPlusInches()
    @Test
    @DisplayName("3) Cross-unit: 1 ft + 12 in = 2 ft (result in feet)")
    void testAddition_CrossUnit_InFeetPlusInches() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCH)); // default result unit = feet
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(2.0, r.value(), EPS);
    }

    // 4. testAddition_CrossUnit_InchPlusFeet()
    @Test
    @DisplayName("4) Cross-unit: 12 in + 1 ft = 24 in (result in inches)")
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength r = new QuantityLength(12.0, LengthUnit.INCH)
                .add(new QuantityLength(1.0, LengthUnit.FEET)); // default result unit = inches
        assertEquals(LengthUnit.INCH, r.unit());
        assertEquals(24.0, r.value(), EPS);
    }

    // 5. testAddition_CrossUnit_YardPlusFeet()
    @Test
    @DisplayName("5) Cross-unit: 1 yd + 3 ft = 2 yd (result in yards)")
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.YARD)
                .add(new QuantityLength(3.0, LengthUnit.FEET)); // result unit = yards
        assertEquals(LengthUnit.YARD, r.unit());
        assertEquals(2.0, r.value(), EPS);
    }

    // 6. testAddition_CrossUnit_CentimeterPlusInch()
    @Test
    @DisplayName("6) Cross-unit: 2.54 cm + 1 in ≈ 5.08 cm (within epsilon, CM=0.393701)")
    void testAddition_CrossUnit_CentimeterPlusInch() {
        // Default result unit = first operand (CM)
        QuantityLength r = new QuantityLength(2.54, LengthUnit.CM)
                .add(new QuantityLength(1.0, LengthUnit.INCH));
        assertEquals(LengthUnit.CM, r.unit());
        assertEquals(5.08, r.value(), CM_EPS);
    }

    // 7. testAddition_Commutativity()
    @Test
    @DisplayName("7) Commutativity: a+b == b+a (when compared in a common unit)")
    void testAddition_Commutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength ab = a.add(b);                       // result in FEET
        QuantityLength ba = b.add(a).convertTo(LengthUnit.FEET); // convert result to FEET

        assertEquals(ab.value(), ba.value(), EPS);
        assertEquals(LengthUnit.FEET, ab.unit());
        assertEquals(2.0, ab.value(), EPS);
    }

    // 8. testAddition_WithZero()
    @Test
    @DisplayName("8) Identity: 5 ft + 0 in = 5 ft")
    void testAddition_WithZero() {
        QuantityLength r = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCH));
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(5.0, r.value(), EPS);
    }

    // 9. testAddition_NegativeValues()
    @Test
    @DisplayName("9) Negative: 5 ft + (-2 ft) = 3 ft")
    void testAddition_NegativeValues() {
        QuantityLength r = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET));
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(3.0, r.value(), EPS);
    }

    // 10. testAddition_NullSecondOperand()
    @Test
    @DisplayName("10) Null second operand should throw IllegalArgumentException")
    void testAddition_NullSecondOperand() {
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> first.add(null));
    }

    // 11. testAddition_LargeValues()
    @Test
    @DisplayName("11) Large values: 1e6 ft + 1e6 ft = 2e6 ft")
    void testAddition_LargeValues() {
        QuantityLength r = new QuantityLength(1_000_000.0, LengthUnit.FEET)
                .add(new QuantityLength(1_000_000.0, LengthUnit.FEET));
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(2_000_000.0, r.value(), EPS);
    }

    // 12. testAddition_PrecisionEpsilon()
    @Test
    @DisplayName("12) Precision: 0.1 ft + 0.2 ft ≈ 0.3 ft (within epsilon)")
    void testAddition_PrecisionEpsilon() {
        QuantityLength r = new QuantityLength(0.1, LengthUnit.FEET)
                .add(new QuantityLength(0.2, LengthUnit.FEET));
        assertEquals(LengthUnit.FEET, r.unit());
        assertEquals(0.3, r.value(), 1e-9); // allow tiny fp drift
    }

}
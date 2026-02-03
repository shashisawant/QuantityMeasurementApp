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

}
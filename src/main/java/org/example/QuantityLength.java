package org.example;
import java.util.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        validateFinite(value);
        validateUnit(unit);
        this.unit = Objects.requireNonNull(unit, "unit must not be null");
        this.value = value;

    }

    public double value() { return value; }
    public LengthUnit unit() { return unit; }


    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("other quantity must not be null");
        // Normalize to base (feet), add, convert back to this.unit
        double sumFeet = this.unit.toBase(this.value) + other.unit.toBase(other.value);
        double sumInThisUnit = this.unit.fromBase(sumFeet);
        return new QuantityLength(sumInThisUnit, this.unit);
    }

    /** Add another quantity and return the result in targetUnit. */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("other quantity must not be null");
        if (targetUnit == null) throw new IllegalArgumentException("targetUnit must not be null");
        double sumFeet = this.unit.toBase(this.value) + other.unit.toBase(other.value);
        double sumInTarget = targetUnit.fromBase(sumFeet);
        return new QuantityLength(sumInTarget, targetUnit);
    }

    /** Add with rounding controls (scale, rounding mode) in targetUnit. */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit, int scale, RoundingMode rounding) {
        QuantityLength raw = this.add(other, targetUnit);
        double rounded = round(raw.value, scale, rounding);
        return new QuantityLength(rounded, targetUnit);
    }

    /* ===================== UC6: Addition (static) ===================== */

    /** Add two quantities; result in unit of the first operand. */
    public static QuantityLength add(QuantityLength a, QuantityLength b) {
        if (a == null || b == null) throw new IllegalArgumentException("quantities must not be null");
        return a.add(b);
    }


    public static QuantityLength add(QuantityLength length1, QuantityLength length2, LengthUnit targetUnit) {
        Objects.requireNonNull(length1, "length1 must not be null");
        Objects.requireNonNull(length2, "length2 must not be null");
        validateUnit(targetUnit);

        // Validate values are finite (not NaN / not Infinite)
        validateFinite(length1.value, "length1.value");
        validateFinite(length2.value, "length2.value");

        // 1) Convert both to base unit (feet)
        double l1InFeet = length1.unit.toBase(length1.value);
        double l2InFeet = length2.unit.toBase(length2.value);

        // 2) Add in base unit
        double sumInFeet = l1InFeet + l2InFeet;

        // 3) Convert to explicit target unit
        double sumInTarget = targetUnit.fromBase(sumInFeet);

        // 4) Return a NEW object (immutability)
        return new QuantityLength(sumInTarget, targetUnit);
    }

    /**
     * Overload supporting raw values + units, as allowed by UC7 preconditions.
     */
    public static QuantityLength add(double value1, LengthUnit unit1,
                                     double value2, LengthUnit unit2,
                                     LengthUnit targetUnit) {
        validateUnit(unit1);
        validateUnit(unit2);
        validateUnit(targetUnit);
        validateFinite(value1, "value1");
        validateFinite(value2, "value2");

        double l1InFeet = unit1.toBase(value1);
        double l2InFeet = unit2.toBase(value2);
        double sumInFeet = l1InFeet + l2InFeet;

        return new QuantityLength(targetUnit.fromBase(sumInFeet), targetUnit);
    }

    /** Add raw values with units; return numeric result in targetUnit (no object). */
    public static double addToNumeric(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit targetUnit) {
        return add(v1, u1, v2, u2, targetUnit).value;
    }

    /** Add with rounding to numeric. */
    public static double addToNumeric(double v1, LengthUnit u1, double v2, LengthUnit u2,
                                      LengthUnit targetUnit, int scale, RoundingMode rounding) {
        QuantityLength raw = add(v1, u1, v2, u2, targetUnit);
        return round(raw.value, scale, rounding);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "targetUnit must not be null");
        double feet = unit.toBase(value);
        double targetVal = targetUnit.fromBase(feet);
        return new QuantityLength(targetVal, targetUnit);
    }

    /** Overload with rounding (optional). */
    public QuantityLength convertTo(LengthUnit targetUnit, int scale, RoundingMode rounding) {
        QuantityLength raw = convertTo(targetUnit);
        double rounded = round(raw.value, scale, rounding);
        return new QuantityLength(rounded, targetUnit);
    }
    public static double convert(double value, LengthUnit from, LengthUnit to) {
        validateFinite(value);

        if (from == null) {
            throw new IllegalArgumentException("from unit must not be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("to unit must not be null");
        }
        double feet = from.toBase(value);
        return to.fromBase(feet);
    }

    /** Static convert with rounding controls. */
    public static double convert(double value, LengthUnit from, LengthUnit to,
                                 int scale, RoundingMode rounding) {
        double result = convert(value, from, to);
        return round(result, scale, rounding);
    }




    private double toBaseUnit() {   // Base unit = Feet
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }


    @Override
    public String toString() {
        return value + " " + unit.name();
    }
    private static void validateFinite(double v) {
        if (!Double.isFinite(v)) {
            throw new IllegalArgumentException("Value must be finite (non-NaN, non-infinite): " + v);
        }
    }
    private static void validateFinite(double v, String name) {
        if (Double.isNaN(v) || Double.isInfinite(v)) {
            throw new IllegalArgumentException(name + " must be a finite number.");
        }
    }
    private static void validateUnit(LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be non-null and a valid LengthUnit.");
        }
    }

    /** Factory with input validation (immutability preserved). */
    public static QuantityLength of(double value, LengthUnit unit) {
        validateUnit(unit);
        validateFinite(value, "value");
        return new QuantityLength(value, unit);
    }

    private static double round(double v, int scale, RoundingMode mode) {
        return new BigDecimal(Double.toString(v)).setScale(scale, mode).doubleValue();
    }
}

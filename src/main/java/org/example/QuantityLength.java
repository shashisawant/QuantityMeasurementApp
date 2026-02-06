package org.example;
import java.util.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        validateFinite(value);
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

    /** Add two quantities; result in targetUnit. */
    public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
        if (a == null || b == null) throw new IllegalArgumentException("quantities must not be null");
        return a.add(b, targetUnit);
    }

    /** Add raw values with units; result in targetUnit. */
    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit targetUnit) {
        validateFinite(v1);
        validateFinite(v2);
        if (u1 == null) throw new IllegalArgumentException("u1 must not be null");
        if (u2 == null) throw new IllegalArgumentException("u2 must not be null");
        if (targetUnit == null) throw new IllegalArgumentException("targetUnit must not be null");
        double sumFeet = u1.toBase(v1) + u2.toBase(v2);
        double result = targetUnit.fromBase(sumFeet);
        return new QuantityLength(result, targetUnit);
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

    private static double round(double v, int scale, RoundingMode mode) {
        return new BigDecimal(Double.toString(v)).setScale(scale, mode).doubleValue();
    }
}

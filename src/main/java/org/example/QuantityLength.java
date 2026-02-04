package org.example;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Immutable length quantity represented as (value, unit).
 * - Equality compares normalized values in base unit (feet) using exact Double.compare (no epsilon).
 * - Conversion returns a NEW QuantityLength (immutability).
 */
public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        validateFinite(value);
        this.unit = Objects.requireNonNull(unit, "unit must not be null");
        this.value = value;
    }

    public double value() { return value; }
    public LengthUnit unit() { return unit; }

    /** Instance conversion: returns a NEW QuantityLength in the target unit. */
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

    /* -------------------- Static helpers (overloaded API) -------------------- */

    /** Static convert for raw value + from/to units. */
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

    /* -------------------- Equality/Hash/ToString overrides -------------------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) o;
        // Normalize both to base (feet) and use exact double comparison (per your earlier tests)
        double a = this.unit.toBase(this.value);
        double b = other.unit.toBase(other.value);
        return Double.compare(a, b) == 0;
    }

    @Override
    public int hashCode() {
        // Hash normalized value to be consistent with equals()
        double normalized = unit.toBase(value);
        return Double.hashCode(normalized);
    }

    @Override
    public String toString() {
        return value + " " + unit.name();
    }

    /* -------------------- Private helpers -------------------- */

    private static void validateFinite(double v) {
        if (!Double.isFinite(v)) {
            throw new IllegalArgumentException("Value must be finite (non-NaN, non-infinite): " + v);
        }
    }

    private static double round(double v, int scale, RoundingMode mode) {
        return new BigDecimal(Double.toString(v)).setScale(scale, mode).doubleValue();
    }
}

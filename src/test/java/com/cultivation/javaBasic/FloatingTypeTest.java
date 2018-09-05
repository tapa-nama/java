package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.jupiter.api.Assertions.*;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void could_convert_or_not() {
        final byte byter = 1;
        final short shorter = 1;
        final int integer = 1;
        final long longer = 1L;
        final float floater = 1.0f;
        final double doubler = 1.0d;

        float floater2 = 1L;
        double doubler2 = 1L;







        //implicit
        assertEquals(shorter, byter);
        assertEquals(integer, byter);
        assertEquals(longer, byter);
        assertEquals(floater, byter);
        assertEquals(doubler, byter);


        //explicit
        assertEquals(byter, (byte) integer);

        assertEquals(longer, (long) doubler);
        assertEquals(integer, (int) doubler);
        assertEquals(floater, (float) doubler);
        assertEquals(floater,  doubler);


        assertEquals(integer, (int) floater);

        assertEquals(byter,(byte) shorter);
        assertEquals(byter, shorter);

        assertEquals(floater,longer);
        assertEquals(doubler,longer);



    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        //System.out.print(1d / 0d);
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(NEGATIVE_INFINITY));
        assertFalse(isNan(POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return (realNumber != realNumber);
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return (realNumber == POSITIVE_INFINITY) || (realNumber == NEGATIVE_INFINITY);

    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}

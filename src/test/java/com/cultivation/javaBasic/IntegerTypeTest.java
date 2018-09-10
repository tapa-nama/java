package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Integer.toBinaryString;
import static org.junit.jupiter.api.Assertions.*;

class IntegerTypeTest {

    @Test
    void should_get_range_of_primitive_int_type() {
        final int maximum = 0x7fffffff;
        final int minimum = 0x80000000;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final int maximumSymbol = Integer.MAX_VALUE;
        final int minimumSymbol = Integer.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);

        assertEquals(Long.MIN_VALUE, 0x8000_0000_0000_0000L);

    }

    @Test
    void should_be_negative() {
        for (int i = Integer.MIN_VALUE; i < 0; i++) {
            assertEquals(1, i >>> 31);
        }

        int num1 = 1;
        for (int j = 0; j < 31; j++) {
            assertTrue((num1 << 1) > 0);
        }

        assertTrue(0x80000000 < 0);
        assertEquals(0xF0000000, (0xE0000000 >> 1));
    }


    @Test
    void should_get_range_of_primitive_short_type() {
        final short maximum = 32767;
        final short minimum = -32768;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final short maximumSymbol = Short.MAX_VALUE;
        final short minimumSymbol = Short.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);

        assertEquals(maximum, 0x7fff);
        assertEquals((short) 0x8000, Short.MIN_VALUE);
        assertTrue((-0x80000000) < 0);
        assertTrue((-0x7FFFFFFF) < 0);
        assertTrue((-0xFFFFFFFF) > 0);


        assertEquals(0x80000000, -0x80000000);
        assertNotEquals(0x1, -0x1);

    }

    @Test
    void should_get_range_of_primitive_long_type() {
        final long maximum = 0x7fffffffffffffffL;
        final long minimum = -0x8000000000000000L;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final long maximumSymbol = Long.MAX_VALUE;
        final long minimumSymbol = Long.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_get_range_of_primitive_byte_type() {
        final byte maximum = 127;
        final byte minimum = -128;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final byte maximumSymbol = Byte.MAX_VALUE;
        final byte minimumSymbol = Byte.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_overflow_silently() {
        int theNumberWillOverflow = Integer.MAX_VALUE;
        ++theNumberWillOverflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MIN_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillOverflow);
//        System.out.println(theNumberWillOverflow);
//
//        System.out.println(theNumberWillOverflow + 2);
//        System.out.print(theNumberWillOverflow + theNumberWillOverflow);

    }

    @Test
    void should_underflow_silently() {
        int theNumberWillUnderflow = Integer.MIN_VALUE;
        --theNumberWillUnderflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MAX_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillUnderflow);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void should_throw_exception_when_overflow() {
        int theNumberWillOverflow = Integer.MAX_VALUE;

        assertThrows(ArithmeticException.class, () -> add(theNumberWillOverflow, 1));
    }

    @Test
    void just_prevent_lazy_implementation() {
        assertEquals(3, add(1, 2));
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    void should_take_care_of_number_type_when_doing_calculation() {
        final double result1 = 2 / 3 * 5;
        final double result2 = 2 * 5 / 3;

        // TODO: please modify the following lines to pass the test
        // <!--start
        final double expectedResult1 = 0.0;
        final double expectedResult2 = 3.0;
        // --end-->

        assertEquals(expectedResult1, result1, +1.0E-05);
        assertEquals(expectedResult2, result2, +1.0E-05);
    }

    @Test
    void should_truncate_number_when_casting() {
        final int integer = 0x0123_4567;
        final short smallerInteger = (short) integer;

        // TODO: please modify the following lines to pass the test
        // <!--start
        final short expected = 0x4567;
        // --end-->

        assertEquals(expected, smallerInteger);
    }

    @Test
    void should_reserve_signed_digit() {
        final int integer = 0x1123_8000;
        final short shorter = (short) integer;

        assertEquals(-0x8000, shorter);
    }

    @Test
    void should_increment() {
        int integer = 3;

        int result = integer++;

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 3;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }

    @Test
    void should_increment_2() {
        int integer = 3;

        int result = ++integer;

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 4;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }

    private int add(int left, int right) {
        // TODO: Please implement the method. Adding two numbers.
        // The method should throw ArithmeticException if overflow or underflow happens.
        return Math.addExact(left, right);
    }

    /*
     * The coach should ask the following questions to make the candidates be focused on the number representations:
     *
     * - How many bytes needed to store a(n) int/long/short/byte.
     * - How many bits are there in a(n) int/long/short/byte.
     * - Why integer number over/underflow is dangerous?
     * - What is `final` variable?
     * - Can Java use uninitialized variable?
     * - Among all the integer types. Which one can be implicitly convert to another.
     * - What is the resulting type for the operation (+ - * / %) of two `short` variable?
     * - When two values are combined with a binary operator both operands are converted to a common type before
     *   the operation is carried out. Do you know the conversion rules?
     *   * If either of the operands is of type double, the other one will be converted to a double.
     *   * Otherwise, if either of the operands is of type float, the other one will be converted to a float.
     *   * Otherwise, if either of the operands is of type long, the other one will be converted to a long.
     *   * Otherwise, both operands will be converted to an int.
     */
}

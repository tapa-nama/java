package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = this::instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = LambdaTest::staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = ArrayList::new;
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = new StringFunc() {
            @Override
            public String getString() {
                System.out.print(value.getValue());
                return "The length of captured value is: " + value.getValue().length();
            }
        };

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }


    @Test
        //lambda表达式capture的变量的可用范围从被定义的范围扩大到被使用的范围
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

//    @Test
//    void should_not_assign_lambda_to_object() {
//        Object obj = () -> "Hello";
//    }


    @Test
    void should_get_int() {
        IntSupplier lambda = () -> 1;

        assertEquals(1, lambda.getAsInt());
    }

    @Test
    void should_get_char() {
        CharSupplier lambdaChar = () -> 'a';

        assertEquals('a', lambdaChar.getAsChar());
    }

    @Test
    void should_return_the_same_number_with_parameter() {
        IntFunction lambdaIntFunction = (n) -> n;
        assertEquals(1, lambdaIntFunction.apply(1));
    }

    @Test
    void should_return_sum_of_two_numbers() {
        IntBiFunction sumLambda = (a, b) -> a + b;
        assertEquals(2, sumLambda.apply(1, 1));
    }

    @Test
    void should_swap_the_first_and_second_number_of_array() {
        int[] ints1 = {1};
        int[] ints2 = {1, 2};
        int[] ints3 = {1, 2, 3};

        ArrayFunction arrayLambda = (int[] array) -> {
            if (array.length == 1)
                return array;
            else if (array.length == 2)
                return new int[]{array[1], array[0]};
            else
                return new int[]{array[1], array[0], array[2]};
        };

        assertArrayEquals(new int[]{1}, arrayLambda.swap(ints1));
        assertArrayEquals(new int[]{2, 1}, arrayLambda.swap(ints2));
        assertArrayEquals(new int[]{2, 1, 3}, arrayLambda.swap(ints3));

    }

    @Test
    void should_return_the_sum_of_all_elements_of_array() {
        int[] ints1 = {1};
        int[] ints2 = {1, 2};
        int[] ints3 = {1, 2, 3, 4, 5};

        SummerFunction arraySummer = (int[] array) -> {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum += array[i];
            }
            return sum;

        };
        assertEquals(1, arraySummer.sum(ints1));
        assertEquals(3, arraySummer.sum(ints2));
        assertEquals(15, arraySummer.sum(ints3));


    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */

package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};

 /*       //int[]不能传入范型
        final int[] numbers = {1, 2};
        getLast(numbers);编译出错
        要传Integer[]*/

        System.out.print(getLast(new Integer[]{1,2}));

        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getMiddle(words);
        final String last = getLast(words);
        // --end-->

        assertEquals("Good", middle);
        assertEquals("Morning", last);
    }

    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});
        String minimumString = min(new String[]{"hello", "world"});

        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
        assertEquals("hello", minimumString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");



        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @Test
    void should_erasure_generic_type_at_runtime_without_bounds() throws NoSuchFieldException {
        Generic<String> generic = new Generic<>();

        assertEquals(Object.class, generic.getClass().getField("type").getType());
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
//        System.out.print(managerPair.getFirst());

        Pair rawPair = managerPair;
        System.out.println(rawPair.getClass());
        rawPair.setFirst(new Employee());
        System.out.print(rawPair.getClass());


        boolean willThrow = false;
        try {
            Manager first = (Manager) rawPair.getFirst();
//            System.out.print(managerPair.getFirst());
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }

    private static <T> T getMiddle(T[] args) {
        return args[args.length / 2];
    }

    private static <T> T getLast(T[] args) {
        return args[args.length - 1];
    }


    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    private static <T extends Comparable<T>> T min(T[] values) {
        if (values == null || values.length == 0) return null;
        T minimum = values[0];
        for (T value : values) {
            minimum = minimum.compareTo(value) > 0 ? value : minimum;
        }
        return minimum;
    }
    // --end-->

  /*  private static Integer identity(Integer obj) {
       return obj;
    }

    private static <T extends Integer> T identity(T t) {
        return t;
    }*/




    private static <T extends MyInteger> void min2(T[] args) {

    }
    @Test
    void should_subclass_can_extends_interface() {


    }



    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    private static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    // TODO: You can add additional method within the range if you like
    // <--start
    private static <T> void swapHelper(Pair<T> pair) {
        T temp = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(temp);
    }




    // --end-->
}

/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?   编译报错
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 */
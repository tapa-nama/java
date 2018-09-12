package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.DistinctIterable;
import com.cultivation.javaBasic.showYourIntelligence.Sequence;
import com.cultivation.javaBasic.showYourIntelligence.YottabytesNumber;
import com.cultivation.javaBasic.util.RandomCharacterIterable;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsTest {
    @Test
    void should_go_through_an_iterator() {
        ArrayList<String> collection = new ArrayList<>();
        collection.add("Hello");
        collection.add("World");
        collection.add("!");
        Iterator<String> iterator = collection.iterator();

        assertIterableEquals(Arrays.asList("Hello", "World", "!"), createList(iterator));
    }

    @SuppressWarnings({"unused", "UnnecessaryLocalVariable"})
    private static List<String> createList(Iterator<String> iterator) {
        List<String> list = new ArrayList<>();

        // TODO: you could ONLY use `Iterator.hasNext` and `Iterator.next` API to copy items to a `List`. No `for` is
        // allowed.
        // <--start
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        // --end-->

        return list;
    }

    @Test
    void should_create_a_sequence_without_putting_all_items_into_memory() {
        Sequence sequence = new Sequence(4, 10);
        assertIterableEquals(Arrays.asList(4, 5, 6, 7, 8, 9), sequence);
    }

    @Test
    void should_predict_linked_list_operation_result() {
        LinkedList<String> staff = new LinkedList<>();

        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");

        ListIterator<String> iterator = staff.listIterator();
        iterator.next();
        iterator.add("Juliet");
        iterator.previous();
        iterator.remove();


        // TODO: please modify the following code to pass the test
        // <--start
        final List<String> expected = Arrays.asList("Amy", "Bob", "Carl");
        // --end-->

        assertIterableEquals(expected, staff);
    }

    @Test
    void should_generate_distinct_sequence_on_the_fly() {
        // NOTE: This test may execute for a while. But it is okay if your impl is correct.
        final int oneGagaChars = 1024 * 1024 * 1024;
        RandomCharacterIterable characters = new RandomCharacterIterable(
                oneGagaChars,
                new Character[]{'a', 'b'});

        List<Character> distinct = new DistinctIterable<>(characters).toList();
        distinct.sort(Character::compareTo);

        assertIterableEquals(Arrays.asList('a', 'b'), distinct);
    }

    @Test
    void should_reflects_back_to_original_list_for_sub_range() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            integers.add(i);
        }

        List<Integer> subList = integers.subList(3, 10);
        subList.clear();

        // TODO: please modify the following code to pass the test
        // <--start
        final List<Integer> expected = Arrays.asList(0, 1, 2, 10, 11);
        // --end-->

        assertIterableEquals(expected, integers);
    }

    @Test
    void should_change_the_value_of_Integer_when_change_its_element() {
        Integer test = new Integer(1);
        Integer temp = test;
        test++;
        assertEquals(2, test.intValue());
///        assertEquals(temp, test);
        assertSame(test, test++);
        assertTrue(!test.equals(temp));
    }

    @Test
    void should_get_the_correct_number_of_yottabyte_numbers() {
        final long RANGE = 1024 * 1024 * 1024 * 1024L;

        YottabytesNumber yottabytesNumber = new YottabytesNumber();
        int number = yottabytesNumber.getYottaNum(1, RANGE);
        assertEquals(15, number);
    }
}

/*
 * - Can you expect the order returned when iterating over a `HashSet<T>`?  不保证有序
 * - What is an `ArrayList`, `LinkedList`, `ArrayDeque`, `HashSet`, `TreeSet`, `EnumSet`, `LinkedHashSet`,
 *   `PriorityQueue`, `HashMap`, `TreeMap`, `EnumMap`, `LinkedHashMap`
 * - What if an collection is modified while an iterator is still iterating?
 * - Can you add or remove items to the list that is returned by `Array.asList` or `Collections.nCopies`?
 * - What are the differences between HashMap and HashSet?
 * - What is size(), and what capacity?
 */

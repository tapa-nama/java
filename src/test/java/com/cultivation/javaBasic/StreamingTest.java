package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                    words,
                    wordStream.collect(toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
//        Stream<String> wordStream = Stream.of(words);
        // --end-->
        {
            assertArrayEquals(
                    words,
                    wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }

    @Test
    void should_stream_can_be_modified() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};
        Stream<String> originalStream = Arrays.stream(words);

//        final boolean isClosed[] = {false};
        final boolean isClosed = true;
//        originalStream.onClose(() -> isClosed[0] = true);


        Stream<String> modifiedStream = originalStream.filter(w -> w.length() > 4);
//        assertTrue(isClosed[0]);
        originalStream.close();
        originalStream.onClose(() -> System.out.print(isClosed + "2"));


//        originalStream.close();
//        originalStream.onClose(() -> System.out.print(isClosed + "3"));


        assertNotSame(modifiedStream, originalStream);


    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
//        Stream<String> infiniteEchos = Stream.generate(() -> "Echo");
        Stream<String> infiniteEchos = Stream.iterate(("Echo"), str -> str);

        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_do_the_iterate_method_when_skip_first() {
        int[] count = {0};
        Stream infiniteNumber = Stream.iterate(0, n -> {
                    count[0]++;
                    return count[0];
                }
        );
        Optional other = infiniteNumber.skip(10000).findFirst();

        assertEquals(10000, count[0]);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, n -> n + 1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(w -> w.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(String::toUpperCase);
        // --end-->
        {
            assertArrayEquals(
                    new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                    filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                    new AnimeCharacter[]{
                            new AnimeCharacter("Naruto"),
                            new AnimeCharacter("Kisuke"),
                            new AnimeCharacter("Tomoya")
                    },
                    actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> streamMap = Stream.of("Hello", "World", "!").map(StreamingTest::letters);
        Stream<Character> streamFlatMap = streamMap.flatMap(item -> item);

        assertArrayEquals(new Character[]{'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd', '!'},
                streamFlatMap.toArray());

    }

    @Test
    void should_flatten_stream_of_streams2() {
        Character[] characters = Stream.of("an", "apple").map(StreamingTest::letters)
                .flatMap(item -> item).toArray(Character[]::new);
        assertArrayEquals(new Character[]{'a', 'n', 'a', 'p', 'p', 'l', 'e'}, characters);

    }


    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);

        // --end-->
        {
            assertArrayEquals(
                    new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_run_0_times_iterate_when_limit_to_10() {
        int count[] = {0};
        Stream number = Stream.iterate(0, n -> {
            count[0]++;
            return count[0];
        });
        Stream other = number.limit(10);
        assertEquals(0, count[0]);

        Object[] objects = other.toArray();
        assertEquals(9, count[0]);


    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                    letters("HelloWorld").toArray(Character[]::new),
                    concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_concat_sorted() {
        Stream<Character> firstStream = Stream.of('o', 'i', 'r');
        Stream<Character> secondStream = Stream.of('s', 'e', 'c');

        Stream<Character> sortedConcatStream = Stream.concat(firstStream.sorted(), secondStream.sorted());
        assertArrayEquals(letters("iorces").toArray(Character[]::new),
                sortedConcatStream.toArray(Character[]::new));
//        assertArrayEquals(letters("ceiors").toArray(Character[]::new),
//                sortedConcatStream.toArray(Character[]::new));
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                    new Character[]{'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                    characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .filter(v -> v % 2 == 0)
                .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {
        });

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_peek_method_change_the_element_of_stream() {
        ValueHolder<Integer>[] holders = new ValueHolder[1];
        holders[0] = new ValueHolder<>();
        holders[0].setValue(0);
        Stream.of(holders).peek(v -> holders[0].setValue(2)).forEach(i -> {
        });

        assertEquals(2, holders[0].getValue().intValue());

    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> empty.orElseThrow(IllegalStateException::new);
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        //ifPresent
        Consumer<Optional<String>> optionalConsumer = input -> input.ifPresent(x -> result.add(x.toUpperCase()));

        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = input ->
                input.map(item -> result.add(item.toUpperCase()));

        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 4)
                .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 1)
                .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = yieldOptionalStream -> {
            Optional<YieldOptional> first = yieldOptionalStream.findFirst();
            return first.flatMap(YieldOptional::get);
        };
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        int[] count = {0, 0, 0};
        Stream<String> stringStream = Stream.of("str1", "str2", "str3", "str4", "str5", "str6");
        ArrayList<String> res = stringStream.parallel().collect(() -> {
                    count[0]++;
                    return new ArrayList<>();
                },
                (strings, ele) -> {
                    count[1]++;
                    strings.add(ele);
                },
                (strings, ele) -> {
                    strings.addAll(ele);
                    count[2]++;
                });
        assertEquals(ArrayList.class, res.getClass());
        assertEquals(Arrays.asList("str1", "str2", "str3", "str4", "str5", "str6"), res);

        assertEquals(6, count[0]);
        assertEquals(res.size(), count[1]);
        assertEquals(5, count[0] - 1);


    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = stream.collect(HashMap::new,
                (myMap, ele) -> myMap.put(ele.getKey(), ele.getValue()),
                HashMap::putAll);
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = stream.collect(HashMap::new,
                (myMap, ele) -> {
                    String key = ele.getKey();
                    Integer value = ele.getValue();
                    if (myMap.containsKey(key)) {
                        myMap.get(key).add(value);
                    } else {
                        myMap.put(key, new ArrayList<>());
                        myMap.get(key).add(value);
                    }
                },
                (res, helper) -> {
                    helper.forEach((key, value) -> {
                        if (res.containsKey(key)) {
                            List<Integer> list = res.get(key);
                            list.addAll(value);
                            res.put(key, list);
                        } else {
                            res.put(key, value);
                        }
                    });

                });
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @Test
    void should_collect_to_group_by_the_same_remainder() {
        Stream<Integer> stream = IntStream.range(0, 10).boxed();
        HashMap<Integer, List<Integer>> map = stream.collect(Collector.of(HashMap::new,
                (myMap, ele) -> {
                    Integer key = ele % 3;
                    if (myMap.containsKey(key)) {
                        List<Integer> list = myMap.get(key);
                        list.add(ele);
                        myMap.put(key, list);
                    } else {
                        myMap.put(key, new ArrayList<>());
                        myMap.get(key).add(ele);
                    }

                },
                (result, helperMap) -> {
                    helperMap.forEach((key, value) -> {
                        if (result.containsKey(key)) {
                            List<Integer> list = result.get(key);
                            list.addAll(value);
                            result.put(key, list);
                        } else {
                            result.put(key, value);
                        }
                    });
                    return result;
                }
        ));

        assertIterableEquals(Arrays.asList(0, 3, 6, 9), map.get(0));
        assertIterableEquals(Arrays.asList(1, 4, 7), map.get(1));
        assertIterableEquals(Arrays.asList(2, 5, 8), map.get(2));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = stream.collect(groupingBy(KeyValuePair::getKey,
//                Collector.of()
                mapping(KeyValuePair::getValue,
                        toList())));

        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @Test
    void should_collect_to_group_continued_by_collector_of_method() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)

        ).parallel();

        Map<String, List<Integer>> map = stream.collect(groupingBy(KeyValuePair::getKey,
                Collector.of(ArrayList::new,
                        (result, ele) -> {
                            result.add(ele.getValue());
                        },
                        (result, helper) -> {
                            result.addAll(helper);
                            return result;
                        }
                )));

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = stream.collect(groupingBy(KeyValuePair::getKey,
                counting()));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = stream.collect(groupingBy(KeyValuePair::getKey,
                summingInt(KeyValuePair::getValue)));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
                .iterate(1, i -> i + 1)
                .limit(100)
                .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = numbers.stream().reduce((sum, item) -> sum + item);
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = words.stream().reduce(0,
                (length, item) -> length + item.length(),
                (a, b) -> a + b);
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */
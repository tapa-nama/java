package com.cultivation.javaBasicExtended.reverseString;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input.length() == 0) {
            return new String[0];
        }

        String[] strings = input.split(" ");
        String[] res = new String[strings.length];
        for (int i = strings.length - 1, j = 0; i >= 0; i--) {
            res[j++] = strings[i];
        }
        return res;
        // --end-->
    }
}

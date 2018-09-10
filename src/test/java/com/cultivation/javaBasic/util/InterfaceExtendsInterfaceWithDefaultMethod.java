package com.cultivation.javaBasic.util;

import java.util.Arrays;

public interface  InterfaceExtendsInterfaceWithDefaultMethod extends InterfaceWithDefaultMethod {
    @Override
    default String getTheTruthOfTheUniverse() {
        return "Food";
    }
}

package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement stack = stackTraceElements[1];
        return stack.getClassName() + "." + stack.getMethodName();
        // --end-->
    }
}

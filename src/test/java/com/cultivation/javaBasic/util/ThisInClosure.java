package com.cultivation.javaBasic.util;

public class ThisInClosure {
    @Override
    public String toString() {
        return "ThisInClosure";
    }

    @SuppressWarnings("Convert2MethodRef")
    public StringFunc getLambda() {
        return new StringFunc() {
            @Override
            public String getString() {
                return ThisInClosure.this.toString();
            }
        };
    }
}

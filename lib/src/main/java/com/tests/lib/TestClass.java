package com.tests.lib;

public class TestClass {
    IListener listener;

    public void setListener(IListener listener) {
        this.listener = listener;
        this.listener.fire();
    }
}

package com.tests.lib;

public class MyClass {

    public static void main(String[] args) {
        add("hello", 1, 2, 3, 5, 6);
    }

    public static void add(String b, int... a) {
        int total = 0;
        for (int i = 0; i < a.length; i++) {
            total += a[i];
        }
        System.out.println(total);
    }

}
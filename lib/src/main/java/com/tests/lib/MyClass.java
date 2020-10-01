package com.tests.lib;

public class MyClass {

    public static void main(String[] args) {

        try {
            int a = 4;
            System.out.println(a);
            Omar omar = null;
            if (omar != null) {
                omar.age = 50;
                System.out.println(omar.age);
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }

}
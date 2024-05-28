package com.tceweb.project_reflection.refl;

public class Transformator {

    public <I, O> transform(I input) throws ClassNotFoundException {
        Class<?> source = input.getClass();
        Class<?> target = source.forName(source.getClass() + "DTO");

    }

}

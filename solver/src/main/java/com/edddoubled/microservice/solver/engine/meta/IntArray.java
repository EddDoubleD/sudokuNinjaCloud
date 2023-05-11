package com.edddoubled.microservice.solver.engine.meta;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IntArray {
    int[] values;

    public IntArray(int... values) {
        Arrays.sort(values);
        this.values = values;
    }


    @Override
    public boolean equals(Object that) {
        return (that instanceof IntArray) && Arrays.equals(values, ((IntArray) that).values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}

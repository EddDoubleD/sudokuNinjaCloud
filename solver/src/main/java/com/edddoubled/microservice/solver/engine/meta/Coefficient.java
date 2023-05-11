package com.edddoubled.microservice.solver.engine.meta;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true)
public class Coefficient {
    int index, c;

    public Coefficient(int index, int c) {
        this.index = index;
        this.c = c;
    }
}

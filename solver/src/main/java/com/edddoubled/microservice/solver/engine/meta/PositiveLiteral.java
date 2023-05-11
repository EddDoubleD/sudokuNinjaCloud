package com.edddoubled.microservice.solver.engine.meta;

import lombok.Getter;

@Getter
public class PositiveLiteral {

    public final int ijx;
    public final boolean present;

    public PositiveLiteral(int a, boolean present) {
        this.ijx = a;
        this.present = present;
    }
}

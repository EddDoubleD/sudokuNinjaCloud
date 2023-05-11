package com.edddoubled.microservice.solver.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SolutionMetadata {
    /**
     * hash code from the input string
     */
    String hash;

    /**
     * decision matrix
     */
    String solution;

    /**
     * decision operation history
     */
    String log;
}

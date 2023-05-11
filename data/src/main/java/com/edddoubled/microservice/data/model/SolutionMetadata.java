package com.edddoubled.microservice.data.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Document
public class SolutionMetadata {
    /**
     * hash code from the input string
     */
    @Id
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

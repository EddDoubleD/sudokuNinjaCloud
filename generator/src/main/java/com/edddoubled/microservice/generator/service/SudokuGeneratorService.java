package com.edddoubled.microservice.generator.service;

import com.edddoubled.microservice.generator.generator.SudokuGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SudokuGeneratorService implements Generator {

    public String generate(boolean beautiful) {
        SudokuGenerator generator = new SudokuGenerator();
        return generator.fillValues().printSudoku(beautiful);

    }

    @Override
    public String generate(int missingNumbers, boolean beautiful) {
        SudokuGenerator generator = new SudokuGenerator(missingNumbers);
        return generator.fillValues().printSudoku(beautiful);
    }
}

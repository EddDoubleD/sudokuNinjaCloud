package com.edddoubled.microservice.solver.service;

import com.edddoubled.microservice.solver.engine.SudokuSolver;
import com.edddoubled.microservice.solver.engine.meta.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Slf4j
public class SudokuSolverService implements Solver {

    @Override
    public String solve(String sudoku) {
        SudokuSolver solver = SudokuSolver.of(sudoku)
                .withPrecision(1e-5)
                .withLogging(true)
                .build();

        // printing out the answer
        final StringBuilder out = new StringBuilder(171);
        long starTime = System.currentTimeMillis();
        Field result = solver.solve();
        log.info("{} ms - decision", System.currentTimeMillis() - starTime);

        Arrays.stream(result.rows()).forEach(row -> {
            Arrays.stream(row.cells()).forEach(cell -> {
                out.append(' ');
                out.append(cell.value());
            });
            out.append('\n');
        });

        return out.toString();
    }
}

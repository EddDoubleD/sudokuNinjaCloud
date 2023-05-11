package com.edddoubled.microservice.generator.generator;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SudokuGenerator {
    /**
     * Number of columns/rows.
     */
    static int size = 9;

    /**
     * Square dimension
     */
    static int SRN = 3;

    /**
     * Array to store Sudoku values
     */
    int[][] sudoku = new int[size][size];

    /**
     * Number of missing Sudoku values
     */
    int missingNumbers;


    // Constructor
    public SudokuGenerator() {
        this.missingNumbers = (int) Math.floor((Math.random() * 40 + 1));
        log.info("Missed {}", missingNumbers);
    }

    // Constructor
    public SudokuGenerator(int missingNumbers) {
        this.missingNumbers = missingNumbers;
    }


    /**
     * Sudoku Generator
     */
    public SudokuGenerator fillValues() {
        // Fill the diagonal with SRN x SRN matrices
        // for diagonal box, start coordinates -> i==j
        IntStream.iterate(0, i -> i < size, i -> i + SRN).forEach(i -> fillBox(i, i));

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        removeKDigits();
        return this;
    }


    /**
     * Returns false if given 3 x 3 block contains num.
     */
    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        return IntStream
                .range(0, SRN)
                .noneMatch(i ->
                        IntStream.range(0, SRN).anyMatch(j -> sudoku[rowStart + i][colStart + j] == num));
    }

    // Fill a 3 x 3 matrix.
    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(size);
                }
                while (!unUsedInBox(row, col, num));

                sudoku[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    private boolean checkIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    // check in the row for existence
    private boolean unUsedInRow(int i, int num) {
        return IntStream.range(0, size).noneMatch(j -> sudoku[i][j] == num);
    }

    // check in the row for existence
    private boolean unUsedInCol(int j, int num) {
        return IntStream.range(0, size).noneMatch(i -> sudoku[i][j] == num);
    }

    //
    // matrix

    /**
     * A recursive function to fill remaining
     */
    private boolean fillRemaining(int i, int j) {
        if (j >= size && i < size - 1) {
            i = i + 1;
            j = 0;
        }

        if (i >= size && j >= size) {
            return true;
        }

        if (i < SRN) {
            if (j < SRN) {
                j = SRN;
            }
        } else if (i < size - SRN) {
            if (j == (i / SRN) * SRN) {
                j = j + SRN;
            }
        } else {
            if (j == size - SRN) {
                i = i + 1;
                j = 0;
                if (i >= size) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= size; num++) {
            if (checkIfSafe(i, j, num)) {
                sudoku[i][j] = num;
                if (fillRemaining(i, j + 1)) {
                    return true;
                }

                sudoku[i][j] = 0;
            }
        }

        return false;
    }

    // Remove the K no. of digits to
    // complete game
    private void removeKDigits() {
        int count = missingNumbers;
        while (count != 0) {
            int cellId = randomGenerator(size * size) - 1;

            // System.out.println(cellId);
            // extract coordinates i and j
            int i = (cellId / size);
            int j = cellId % 9;
            if (j != 0) {
                j = j - 1;
            }

            // System.out.println(i+" "+j);
            if (sudoku[i][j] != 0) {
                count--;
                sudoku[i][j] = 0;
            }
        }
    }

    // Print sudoku
    public String printSudoku(boolean beautiful) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.append(sudoku[i][j]);
                if (beautiful) {
                    builder.append(" ");
                }
            }

            if (beautiful) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}



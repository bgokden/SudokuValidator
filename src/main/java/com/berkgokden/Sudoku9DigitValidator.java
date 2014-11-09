package com.berkgokden;

import java.util.concurrent.Callable;

/**
 * Created by berkgokden on 11/9/14.
 */
public class Sudoku9DigitValidator implements Callable<Boolean> {
    private String sudoku9Digits;
    private Sudoku9x9BlockValidator sudoku9x9BlockValidator;
    public Sudoku9DigitValidator(String sudoku9Digits, Sudoku9x9BlockValidator sudoku9x9BlockValidator) {
        this.sudoku9Digits = sudoku9Digits;
        this.sudoku9x9BlockValidator = sudoku9x9BlockValidator;
    }
    @Override
    public Boolean call() throws Exception {
        if (this.validate(this.sudoku9Digits) == false) {
            sudoku9x9BlockValidator.setCheckFalse();
            return false;
        }
        return true;
    }

    public boolean validate(String series) {
        int[] checkList = new int[9];
        for (int i = 0; i < series.length(); i++) {
            // there is simple trick to distract a char from another char to get its digits value
            checkList[series.charAt(i) - '1' ] = 1;
        }
        int sum = 0;
        for (int i = 0; i < checkList.length; i++) {
            sum += checkList[i];
        }
        return (sum == 9);

    }
}

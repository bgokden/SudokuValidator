package com.berkgokden.Impl;

import java.util.concurrent.Callable;

/**
 * Created by berkgokden on 11/9/14.
 */
public class Sudoku9DigitValidator{

    public static boolean validate(String series) {
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

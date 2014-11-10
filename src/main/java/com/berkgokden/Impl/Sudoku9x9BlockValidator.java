package com.berkgokden.Impl;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by berkgokden on 11/9/14.
 */
public class Sudoku9x9BlockValidator implements Callable<Boolean> {
    private static final Logger logger = Logger.getLogger(Sudoku9x9BlockValidator.class);
    private String sudoku9x9Block;
    public Sudoku9x9BlockValidator(String sudoku9x9Block) {
        this.sudoku9x9Block = sudoku9x9Block;
    }

    @Override
    public Boolean call() throws Exception {
       return this.validate(this.sudoku9x9Block);
    }

    public static boolean validate(String block) {
        long lStartTime = System.nanoTime();

        StringBuilder stringBuilder = new StringBuilder();

        //smaller blocks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int offset = (i*27+j*3);
                stringBuilder.setLength(0);
                stringBuilder.append(block.substring(offset, offset+3));
                stringBuilder.append(block.substring(offset+9, offset+12));
                stringBuilder.append(block.substring(offset+18, offset+21));
                if (!Sudoku9DigitValidator.validate(stringBuilder.toString())) {
                    long lEndTime = System.nanoTime();;
                    long difference = lEndTime - lStartTime;
                    //logger.debug("Elapsed milliseconds: " + difference/1000000);
                    return false;
                }
            }
        }

        //rows
        for (int i = 0; i < 9; i++) {
            if (!Sudoku9DigitValidator.validate(block.substring(i*9,i*9+9))) {
                long lEndTime = System.nanoTime();;
                long difference = lEndTime - lStartTime;
                //logger.debug("Elapsed milliseconds: " + difference/1000000);
                return false;
            }

        }

        //columns
        for (int i = 0; i < 9; i++) {
            stringBuilder.setLength(0);
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(block.charAt(i+j*9));
            }

            if (!Sudoku9DigitValidator.validate(stringBuilder.toString())) {
                long lEndTime = System.nanoTime();;
                long difference = lEndTime - lStartTime;
                //logger.debug("Elapsed milliseconds: " + difference/1000000);
                return false;
            }

        }



        long lEndTime = System.nanoTime();;
        long difference = lEndTime - lStartTime;
        //logger.debug("Elapsed milliseconds: " + difference/1000000);
        return true;
    }

}

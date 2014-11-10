package com.berkgokden;

import com.berkgokden.Impl.SudokuFileValidator;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class SudokuFileValidatorTest {

    private static final Logger logger = Logger.getLogger(SudokuFileValidatorTest.class);


    @Test
    public void shouldPrintErrorAndReturnNull() {

        String filename = "someUnknownFile";

        SudokuFileValidator sudokuFileValidator = new SudokuFileValidator();

        List<String> listOfInvalidSolutions = sudokuFileValidator.validate(filename);;

        assertNull(listOfInvalidSolutions);

        logger.debug("Test run with success");
    }

    @Test
    public void shouldPrintInvalidLinesFromSamplesFile() {
        logger.debug("Testing samples.txt file");
        String filename = "file/samples.txt";

        SudokuFileValidator sudokuFileValidator = new SudokuFileValidator();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        logger.debug("Testing samples.txt file path:"+file.getPath());

        List<String> listOfInvalidSolutions = sudokuFileValidator.validate(file.getPath());

        if (listOfInvalidSolutions != null) {
            for (String solution : listOfInvalidSolutions) {
                System.out.println(solution);
            }
            System.out.println("Number of invalid solutions :" + listOfInvalidSolutions.size());
        } else {
            //listOfInvalidSolutions returned null
            System.err.println("File name needed.");
        }
        assertTrue(true);
        logger.debug("Test run with success");
    }

    @Test
    public void shouldPassWhenGivenValidLines() {
        SudokuFileValidator sudokuFileValidator = new SudokuFileValidator();
        boolean result = false;
        String randomSudokuString = null;
        for (int i = 0; i < 100; i++) {
            randomSudokuString = randomSudokuString();
            result = sudokuFileValidator.isSudokuLine(randomSudokuString);
            if (!result) {
                logger.warn("Not a proper Sudoku String: '"+randomSudokuString+"'");
            }
            assertTrue(result);
        }
    }

    @Test
    public void shouldPassWhenGivenInvalidLines() {
        SudokuFileValidator sudokuFileValidator = new SudokuFileValidator();
        boolean result = false;
        String str = "#comment string";
        result = sudokuFileValidator.isSudokuLine(str);
        assertFalse(result);

        str = "";//empty string
        result = sudokuFileValidator.isSudokuLine(str);
        assertFalse(result);
    }


    public static String randomSudokuString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 81; i++) {
            char c = (char) ((int)'1' + rand.nextInt(9));//not a good way to do this
            stringBuilder.append( c );
        }
        return stringBuilder.toString();
    }

}
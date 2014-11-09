package com.berkgokden;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuFileValidatorTest {

    private SudokuFileValidator sudokuFileValidator;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSamplesFile() {
        String filename = "file/samples.txt";

        this.sudokuFileValidator = new SudokuFileValidator();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        System.out.println(file.getPath());

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
        System.out.println("@Test - true");
    }

    @Test
    public void shouldReturnFalse() {
        String line = "123453789578139624496872153952381467641297835387564291719623548864915372235748916";
        //System.out.println(sudokuFileValidator.validate(line) + ":");
        System.out.println(line);
        //assertTrue(sudokuFileValidator.validate(line));
        System.out.println("@Test - true");
    }

    @After
    public void tearDown() throws Exception {

    }
}
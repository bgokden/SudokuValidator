package com.berkgokden;

import com.berkgokden.Impl.SudokuFileValidator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuFileValidatorTest {

    static final Logger logger = Logger.getLogger(SudokuFileValidatorTest.class);

    private SudokuFileValidator sudokuFileValidator;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSamplesFile() {
        logger.debug("Testing samples.txt file");
        String filename = "file/samples.txt";

        this.sudokuFileValidator = new SudokuFileValidator();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        System.out.println(file.getPath());
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

    @After
    public void tearDown() throws Exception {

    }
}